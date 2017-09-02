package com.walkersmithtech.artisonfirst.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.entity.UserAccount;
import com.walkersmithtech.artisonfirst.data.entity.UserSession;
import com.walkersmithtech.artisonfirst.data.model.Account;
import com.walkersmithtech.artisonfirst.data.model.BaseDto;
import com.walkersmithtech.artisonfirst.data.model.dto.AuthenticationDto;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.repository.UserAccountRepository;
import com.walkersmithtech.artisonfirst.data.repository.UserSessionRepository;
import com.walkersmithtech.artisonfirst.util.DateUtil;
import com.walkersmithtech.artisonfirst.util.Encryptor;
import com.walkersmithtech.artisonfirst.util.JsonUtil;

@Service
public class AuthenticationService
{
	@Autowired
	private UserAccountRepository userRepo;

	@Autowired
	private UserSessionRepository sessionRepo;

	@Autowired
	private CompanyService companyService;

	@Value( "${artisonfirst.key}" )
	private String key;

	@Value( "${artisonfirst.expiration}" )
	private long expiration;

	public Account login( BaseDto auth ) throws ServiceException
	{
		Account account = auth.getAccount();
		String loginName = account.getLoginName();
		String password = account.getPassword();
		String ipAddress = auth.getIpAddress();
		String companyUid = auth.getAccount().getCompanyUid();

		account.setAuthenticated( false );
		if ( loginName == null || password == null || ipAddress == null )
		{
			throw ErrorCode.AUTH_MISSING_DATA.exception;
		}

		UserAccount user = userRepo.findByLoginName( loginName );
		if ( user == null )
		{
			throw ErrorCode.AUTH_INVALID_CREDENTIALS.exception;
		}

		if ( !user.getPassword().equals( password ) )
		{
			throw ErrorCode.AUTH_INVALID_CREDENTIALS.exception;
		}

		Company company = null;
		if ( companyUid == null )
		{
			company = companyService.getCompanyByPersonUid( user.getPersonUid() );
		}
		else
		{
			company = companyService.getCompanyByUid( companyUid );
		}

		if ( company != null )
		{
			account = createOrUpdateSession( auth, user, company );
		}
		return account;
	}

	private Account createOrUpdateSession( BaseDto auth, UserAccount user, Company company )
	{
		Account account = auth.getAccount();
		account.setDisplayName( user.getDisplayName() );
		account.setLoginName( user.getLoginName() );
		account.setPersonUid( user.getPersonUid() );
		account.setCompanyUid( company != null ? company.getUid() : "" );
		account.setAuthenticated( true );

		String ipAddress = auth.getIpAddress();
		List<UserSession> sessions = sessionRepo.findByIpAddress( ipAddress );
		if ( sessions != null && sessions.size() > 0 )
		{
			for ( UserSession session : sessions )
			{
				try
				{
					if ( isMatchingSession( auth, session ) )
					{
						account.setSessionId( session.getSessionId() );
						account.setToken( session.getToken() );
						return account;
					}
				}
				catch ( ServiceException e )
				{
					e.printStackTrace();
				}
			}
		}

		account = createSession( account, ipAddress );
		return account;
	}

	private boolean isMatchingSession( BaseDto auth, UserSession session ) throws ServiceException
	{
		String salt = session.getSalt();
		String token = session.getToken();
		AuthenticationDto authObject = decryptToken( salt, token );
		return matches( authObject, auth );
	}

	public Account validateSession( BaseDto auth ) throws ServiceException
	{
		Account account = auth.getAccount();
		account.setAuthenticated( false );
		UserSession session = checkAndRetrieveSession( account, auth.getIpAddress() );

		String salt = session.getSalt();
		String token = session.getToken();
		AuthenticationDto authObject = decryptToken( salt, token );
		if ( !matches( authObject, auth ) )
		{
			return account;
		}

		String loginName = authObject.getLoginName();
		if ( session.getNeverExpires().intValue() == 0 )
		{
			// check expiration here...
			String personUid = authObject.getPersonUid();
			String ipAddress = authObject.getIpAddress();
			session = initSession( session, personUid, loginName, ipAddress );
		}
		account = buildUserProfile( loginName, session.getSessionId(), session.getToken() );
		account.setAuthenticated( true );
		return account;
	}

	private boolean matches( AuthenticationDto authObject, BaseDto auth )
	{
		Account account = auth.getAccount();
		String loginName = authObject.getLoginName();
		String personUid = authObject.getPersonUid();
		String sessionId = authObject.getSessionId();
		String ipAddress = authObject.getIpAddress();

		if ( !sessionId.equals( account.getSessionId() ) )
		{
			return false;
		}

		if ( !loginName.equals( account.getLoginName() ) )
		{
			return false;
		}

		if ( !personUid.equals( account.getPersonUid() ) )
		{
			return false;
		}

		if ( !ipAddress.equals( auth.getIpAddress() ) )
		{
			return false;
		}

		return true;
	}

	public Account invalidateSession( BaseDto auth ) throws ServiceException
	{
		Account account = auth.getAccount();
		account.setAuthenticated( false );
		if ( account.getSessionId() != null && !account.getSessionId().isEmpty() )
		{
			UserSession session = sessionRepo.findBySessionId( account.getSessionId() );
			if ( session != null )
			{
				if ( session.getNeverExpires().intValue() == 0 )
				{
					sessionRepo.delete( session );
				}
			}
		}
		return account;
	}

	private UserSession checkAndRetrieveSession( Account account, String ipAddress ) throws ServiceException
	{
		String sessionId = account.getSessionId();
		if ( sessionId == null )
		{
			throw ErrorCode.AUTH_INVALID_SESSION.exception;
		}

		String token = account.getToken();
		if ( token == null )
		{
			return null;
		}

		UserSession session = sessionRepo.findBySessionId( sessionId );
		if ( session == null )
		{
			throw ErrorCode.AUTH_INVALID_SESSION.exception;
		}

		if ( !ipAddress.equals( session.getIpAddress() ) )
		{
			throw ErrorCode.AUTH_INVALID_SESSION.exception;
		}

		if ( !token.equals( session.getToken() ) )
		{
			throw ErrorCode.AUTH_INVALID_SESSION.exception;
		}

		return session;
	}

	private UserSession initSession( UserSession session, String userName, String personUid, String ipAddress )
	{
		String salt = DateUtil.generateIV();
		Date currentDate = DateUtil.getCurrentDate();

		String sessionId = session.getSessionId();
		String token = createToken( userName, personUid, sessionId, salt, ipAddress );

		session.setLastTouchedOn( currentDate );
		session.setToken( token );
		session.setSalt( salt );
		session = sessionRepo.save( session );
		return session;
	}

	public Account getProfileFromSession( String sessionId ) throws ServiceException
	{
		if ( sessionId == null )
		{
			return null;
		}

		UserSession session = sessionRepo.findBySessionId( sessionId );
		if ( session == null )
		{
			return null;
		}

		String salt = session.getSalt();
		String token = session.getToken();
		String userContext = Encryptor.decrypt( key, salt, token );
		String[] contextArray = userContext.split( "[|]" );
		if ( contextArray.length < 4 )
		{
			return null;
		}

		Account account = buildUserProfile( contextArray[ 0 ], sessionId, token );
		account.setAuthenticated( true );
		return account;
	}

	private Account buildUserProfile( String loginName, String sessionId, String token ) throws ServiceException
	{
		UserAccount user = userRepo.findByLoginName( loginName );
		if ( user == null )
		{
			throw ErrorCode.AUTH_INVALID_CREDENTIALS.exception;
		}

		Account account = new Account();
		account.setDisplayName( user.getDisplayName() );
		account.setLoginName( user.getLoginName() );
		account.setPersonUid( user.getPersonUid() );
		account.setSessionId( sessionId );
		account.setToken( token );
		return account;

	}

	private Account createSession( Account account, String ipAddress )
	{
		String sessionId = DateUtil.generateUuid();
		String salt = DateUtil.generateIV();
		String loginName = account.getLoginName();
		String personUid = account.getPersonUid();
		String token = createToken( loginName, personUid, sessionId, salt, ipAddress );

		Date currentDate = DateUtil.getCurrentDate();
		UserSession session = new UserSession();
		session.setInitializedOn( currentDate );
		session.setLastTouchedOn( currentDate );
		session.setSalt( salt );
		session.setSessionId( sessionId );
		session.setToken( token );
		session.setIpAddress( ipAddress.toString() );
		sessionRepo.save( session );

		account.setSessionId( sessionId );
		account.setToken( token );
		return account;
	}

	private AuthenticationDto decryptToken( String salt, String token ) throws ServiceException
	{
		try
		{
			String authData = Encryptor.decrypt( key, salt, token );
			AuthenticationDto authObject = ( AuthenticationDto ) JsonUtil.createModelFromJson( authData, AuthenticationDto.class );
			return authObject;
		}
		catch ( Exception ex )
		{
			ServiceException se = ErrorCode.SYSTEM_ERROR.exception;
			se.initCause( ex );
			throw se;
		}
	}

	private String createToken( String loginName, String personUid, String sessionId, String salt, String ipAddress )
	{
		Date expireOn = new Date( System.currentTimeMillis() + expiration );
		AuthenticationDto authObject = new AuthenticationDto();
		authObject.setLoginName( loginName );
		authObject.setPersonUid( personUid );
		authObject.setSessionId( sessionId );
		authObject.setIpAddress( ipAddress );
		authObject.setExpiresOn( expireOn );
		String data = JsonUtil.createJsonFromModel( authObject );
		String token = Encryptor.encrypt( key, salt, data );
		return token;
	}
}
