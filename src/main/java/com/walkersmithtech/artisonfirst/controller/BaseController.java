package com.walkersmithtech.artisonfirst.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.walkersmithtech.artisonfirst.data.model.dto.AccountDto;
import com.walkersmithtech.artisonfirst.data.model.dto.BaseDto;
import com.walkersmithtech.artisonfirst.service.ServiceException;
import com.walkersmithtech.artisonfirst.service.impl.AuthenticationServiceImpl;

public abstract class BaseController
{
	@Autowired
	private AuthenticationServiceImpl authorizationService;

	protected BaseDto validateLogin( HttpServletRequest requestContext, BaseDto auth ) throws ServiceException
	{
		auth.setIpAddress( requestContext.getRemoteAddr() );
		AccountDto account = authorizationService.login( auth );
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
		AccountDto account = new AccountDto();
		account.setSessionId( sessionId );
		account.setToken( token );
		auth.setAccount( account );
		auth = validateSession( requestContext, auth );
		return auth;
	}

	protected BaseDto validateSession( BaseDto auth ) throws ServiceException
	{
		AccountDto account = authorizationService.validateSession( auth );
		auth.setAccount( account );
		return auth;
	}

	protected AccountDto getUserProfileFromSession( String sessionId ) throws ServiceException
	{
		AccountDto account = authorizationService.getProfileFromSession( sessionId );
		return account;
	}

	protected BaseDto invalidateSession( BaseDto auth ) throws ServiceException
	{
		AccountDto account = authorizationService.invalidateSession( auth );
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
