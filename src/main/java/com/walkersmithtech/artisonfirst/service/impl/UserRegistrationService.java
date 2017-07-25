package com.walkersmithtech.artisonfirst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.data.entity.UserAccount;
import com.walkersmithtech.artisonfirst.data.model.Company;
import com.walkersmithtech.artisonfirst.data.model.Location;
import com.walkersmithtech.artisonfirst.data.model.Person;
import com.walkersmithtech.artisonfirst.data.model.dto.AccountDto;
import com.walkersmithtech.artisonfirst.data.model.dto.RegistrationDto;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyLocation;
import com.walkersmithtech.artisonfirst.data.model.relation.PersonCompany;
import com.walkersmithtech.artisonfirst.data.repository.UserAccountRepository;
import com.walkersmithtech.artisonfirst.service.ServiceException;
import com.walkersmithtech.artisonfirst.util.DateUtil;

@Service
public class UserRegistrationService
{

	@Autowired
	private UserAccountRepository accountRepo;

	@Autowired
	private PersonServiceImpl personService;

	@Autowired
	private CompanyServiceImpl companyService;

	@Autowired
	private PersonCompanyServiceImpl personCompanyService;

	@Autowired
	private LocationServiceImpl locationService;

	@Autowired
	private CompanyLocationServiceImpl companyLocationService;

	public RegistrationDto registerUser( RegistrationDto registration ) throws ServiceException
	{
		validateRegistration( registration );

		Person person = createPerson( registration.getPerson() );
		registration.setPerson( person );
		registration.getAccount().setPersonUid( person.getUid() );
		registration = createCompany( registration );
		AccountDto account = createAccount( registration.getAccount() );
		registration.setAccount( account );

		return registration;
	}

	private void validateRegistration( RegistrationDto registration ) throws ServiceException
	{
		if ( registration == null )
		{
			throw ErrorCode.REGISTRATION_MISSING.exception;
		}

		if ( registration.getPerson() == null )
		{
			throw ErrorCode.REGISTRATION_USERINFO_MISSING.exception;
		}

		if ( registration.getPerson().getFirstName() == null )
		{
			throw ErrorCode.REGISTRATION_USER_FIRSNAME_MISSING.exception;
		}

		if ( registration.getPerson().getLastName() == null )
		{
			throw ErrorCode.REGISTRATION_USER_LASTNAME_MISSING.exception;
		}

		if ( registration.getAccount() == null )
		{
			throw ErrorCode.REGISTRATION_MISSING.exception;
		}

		if ( registration.getAccount().getLoginName() == null )
		{
			throw ErrorCode.REGISTRATION_USERNAME_MISSING.exception;
		}

		if ( registration.getAccount().getPassword() == null )
		{
			throw ErrorCode.REGISTRATION_PASSWORD_MISSING.exception;
		}

		if ( registration.getAccount().getDisplayName() == null )
		{
			throw ErrorCode.REGISTRATION_DISPLAYNAME_MISSING.exception;
		}

		if ( registration.getCompany() == null )
		{
			throw ErrorCode.REGISTRATION_ORGNAIZTION_MISSING.exception;
		}

		if ( registration.getCompany().getCompanyName() == null )
		{
			throw ErrorCode.REGISTRATION_ORGNAIZTION_NAME_MISSING.exception;
		}

		UserAccount account = accountRepo.findByLoginName( registration.getAccount().getLoginName() );
		if ( account != null )
		{
			throw ErrorCode.REGISTRATION_ACCOUNT_EXISTS.exception;
		}
	}

	private Person createPerson( Person person ) throws ServiceException
	{
		person = personService.createModel( person );
		return person;
	}

	private AccountDto createAccount( AccountDto account )
	{
		UserAccount entity = new UserAccount();
		entity.setActive( 1 );
		entity.setDisplayName( account.getDisplayName() );
		entity.setLoginName( account.getLoginName() );
		entity.setPassword( account.getPassword() );
		entity.setPersonUid( account.getPersonUid() );
		entity.setCreatedOn( DateUtil.getCurrentDate() );
		entity.setUpdatedOn( DateUtil.getCurrentDate() );
		entity.setConfirmationCode( DateUtil.generateUuid() );
		entity = accountRepo.save( entity );
		account.setConfirmationCode( entity.getConfirmationCode() );
		account.setPersonUid( entity.getPersonUid() );
		return account;
	}

	private RegistrationDto createCompany( RegistrationDto registration )
	{
		Company company = registration.getCompany();
		company = companyService.createModel( company );
		PersonCompany personCompany = new PersonCompany();
		personCompany.setSourceUid( registration.getPerson().getUid() );
		personCompany.setTargetUid( company.getUid() );
		personCompany = personCompanyService.createModel( personCompany );
		registration.setCompany( company );
		registration = createCompanyAddresses( registration );
		return registration;
	}

	private RegistrationDto createCompanyAddresses( RegistrationDto registration )
	{
		Company company = registration.getCompany();
		List<Location> addresses = registration.getAddressInfo();
		if ( addresses != null && addresses.size() > 0 )
		{
			CompanyLocation companyLocation;
			for ( Location address : addresses )
			{
				if ( isValidAddress( address ) )
				{
					address = locationService.createModel( address );
					companyLocation = new CompanyLocation();
					companyLocation.setSourceUid( company.getUid() );
					companyLocation.setTargetUid( address.getUid() );
					companyLocationService.createModel( companyLocation );
				}
			}
		}
		return registration;
	}
	
	private boolean isValidAddress( Location address )
	{
		if ( address.getAddress1() == null || address.getAddress1().isEmpty() )
		{
			return false;
		}
		
		if ( address.getCity() == null || address.getCity().isEmpty() )
		{
			return false;
		}
		
		return true;
	}
}
