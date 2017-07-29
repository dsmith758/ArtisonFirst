package com.walkersmithtech.artisonfirst.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.data.entity.UserAccount;
import com.walkersmithtech.artisonfirst.data.entity.UserSession;
import com.walkersmithtech.artisonfirst.data.model.dto.AccountDto;
import com.walkersmithtech.artisonfirst.data.model.dto.BaseDto;
import com.walkersmithtech.artisonfirst.data.repository.UserAccountRepository;
import com.walkersmithtech.artisonfirst.data.repository.UserSessionRepository;
import com.walkersmithtech.artisonfirst.service.ServiceException;
import com.walkersmithtech.artisonfirst.util.DateUtil;
import com.walkersmithtech.artisonfirst.util.Encryptor;

@Service
public class AuthenticationServiceImpl
{
	@Autowired
	private UserAccountRepository userRepo;

	@Autowired
	private UserSessionRepository sessionRepo;

	@Value( "${artisonfirst.key}" )
	private String key;

	public AccountDto login( BaseDto auth ) throws ServiceException
	{
		AccountDto account = auth.getAccount();
		String loginName = account.getLoginName();
		String password = account.getPassword();
		String ipAddress = auth.getIpAddress();

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

		account = createOrUpdateSession( auth, user );
		return account;
	}

	private AccountDto createOrUpdateSession( BaseDto auth, UserAccount user )
	{
		AccountDto account = auth.getAccount();
		account.setDisplayName( user.getDisplayName() );
		account.setLoginName( user.getLoginName() );
		account.setPersonUid( user.getPersonUid() );
		account.setAuthenticated( true );

		String ipAddress = auth.getIpAddress();
		List<UserSession> sessions = sessionRepo.findByIpAddress( ipAddress );
		if ( sessions != null && sessions.size() > 0 )
		{
			for ( UserSession session : sessions )
			{
				if ( isMatchingSession( auth, session ) )
				{
					// session = resetSession( session, user.getLoginName(),
					// user.getPersonUid(), auth.getIpAddress() );
					account.setSessionId( session.getSessionId() );
					account.setToken( session.getToken() );
					return account;
				}
			}
		}

		account = createSession( account, ipAddress );
		return account;
	}

	private boolean isMatchingSession( BaseDto auth, UserSession session )
	{
		AccountDto account = auth.getAccount();
		String salt = session.getSalt();
		String ipAddress = session.getIpAddress();
		String token = session.getToken();

		if ( !ipAddress.equals( auth.getIpAddress() ) )
		{
			return false;
		}

		String[] contextArray = decryptToken( salt, token );
		if ( contextArray == null )
		{
			return false;
		}

		String loginName = contextArray[ 0 ];
		String personUid = contextArray[ 1 ];

		if ( !loginName.equals( account.getLoginName() ) )
		{
			return false;
		}

		if ( !personUid.equals( account.getPersonUid() ) )
		{
			return false;
		}

		return true;
	}

	public AccountDto invalidateSession( BaseDto auth ) throws ServiceException
	{
		AccountDto account = auth.getAccount();
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

	public AccountDto validateSession( BaseDto auth ) throws ServiceException
	{
		AccountDto account = auth.getAccount();
		account.setAuthenticated( false );
		UserSession session = validateAndRetrieveSession( account, auth.getIpAddress() );

		String salt = session.getSalt();
		String ipAddress = session.getIpAddress();
		String token = session.getToken();

		String[] contextArray = decryptToken( salt, token );
		if ( contextArray == null )
		{
			throw ErrorCode.AUTH_INVALID_SESSION.exception;
		}

		String personUid = contextArray[ 1 ];
		String loginName = contextArray[ 0 ];

		// session = resetSession( session, personUid, loginName, ipAddress );
		account = buildUserProfile( loginName, session.getSessionId(), session.getToken() );
		account.setAuthenticated( true );
		return account;
	}

	private String[] decryptToken( String salt, String token )
	{
		String userContext = Encryptor.decrypt( key, salt, token );
		String[] contextArray = userContext.split( "[|]" );
		if ( contextArray.length < 4 )
		{
			return null;
		}
		return contextArray;
	}

	private UserSession validateAndRetrieveSession( AccountDto account, String ipAddress ) throws ServiceException
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

	private UserSession resetSession( UserSession session, String userName, String personUid, String ipAddress )
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

	public AccountDto getProfileFromSession( String sessionId ) throws ServiceException
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

		AccountDto account = buildUserProfile( contextArray[ 0 ], sessionId, token );
		account.setAuthenticated( true );
		return account;
	}

	private AccountDto buildUserProfile( String loginName, String sessionId, String token ) throws ServiceException
	{
		UserAccount user = userRepo.findByLoginName( loginName );
		if ( user == null )
		{
			throw ErrorCode.AUTH_INVALID_CREDENTIALS.exception;
		}

		AccountDto account = new AccountDto();
		account.setDisplayName( user.getDisplayName() );
		account.setLoginName( user.getLoginName() );
		account.setPersonUid( user.getPersonUid() );
		account.setSessionId( sessionId );
		account.setToken( token );
		return account;

	}

	private AccountDto createSession( AccountDto account, String ipAddress )
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

	private String createToken( String userName, String personUid, String sessionId, String salt, String ipAddress )
	{
		String data = userName + "|" + personUid + "|" + sessionId + "|" + ipAddress;
		String token = Encryptor.encrypt( key, salt, data );
		return token;
	}
}
