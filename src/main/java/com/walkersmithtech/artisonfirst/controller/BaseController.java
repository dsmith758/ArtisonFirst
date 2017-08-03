package com.walkersmithtech.artisonfirst.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.walkersmithtech.artisonfirst.component.ServiceException;
import com.walkersmithtech.artisonfirst.component.service.AuthenticationService;
import com.walkersmithtech.artisonfirst.data.model.Account;
import com.walkersmithtech.artisonfirst.data.model.BaseDto;

public abstract class BaseController
{
	@Autowired
	private AuthenticationService authorizationService;

	protected BaseDto validateLogin( HttpServletRequest requestContext, BaseDto auth ) throws ServiceException
	{
		auth.setIpAddress( requestContext.getRemoteAddr() );
		Account account = authorizationService.login( auth );
		auth.setAccount( account );
		return auth;
	}

	protected BaseDto validateSession( HttpServletRequest requestContext, BaseDto auth ) throws ServiceException
	{
		auth.setIpAddress( requestContext.getRemoteAddr() );
		return validateSession( auth );
	}
	
	protected BaseDto validateSession( HttpServletRequest requestContext, BaseDto auth,  String sessionId, String token ) throws ServiceException
	{
		Account account = new Account();
		account.setSessionId( sessionId );
		account.setToken( token );
		auth.setAccount( account );
		auth = validateSession( requestContext, auth );
		return auth;
	}

	protected BaseDto validateSession( BaseDto auth ) throws ServiceException
	{
		Account account = authorizationService.validateSession( auth );
		auth.setAccount( account );
		return auth;
	}

	protected Account getUserProfileFromSession( String sessionId ) throws ServiceException
	{
		Account account = authorizationService.getProfileFromSession( sessionId );
		return account;
	}

	protected BaseDto invalidateSession( BaseDto auth ) throws ServiceException
	{
		Account account = authorizationService.invalidateSession( auth );
		auth.setAccount( account );
		return auth;
	}

	protected BaseDto setErrorCode( BaseDto auth, ServiceException exception )
	{
		auth.setErrorCode( exception.getErrorCode() );
		auth.setErrorMessage( exception.getErrorMessage() );
		return auth;
	}

}
