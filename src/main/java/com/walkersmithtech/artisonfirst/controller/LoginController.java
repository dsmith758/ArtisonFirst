package com.walkersmithtech.artisonfirst.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walkersmithtech.artisonfirst.component.ServiceException;
import com.walkersmithtech.artisonfirst.component.service.UserRegistrationService;
import com.walkersmithtech.artisonfirst.data.model.dto.LoginDto;
import com.walkersmithtech.artisonfirst.data.model.dto.RegistrationDto;

@RestController
public class LoginController extends BaseController
{

	@Autowired
	private UserRegistrationService registrationService;
	
	@RequestMapping( method = RequestMethod.POST, value = "/login" )
	public ResponseEntity<LoginDto> login( HttpServletRequest requestContext, @RequestBody LoginDto model )
	{
		try
		{
			model = ( LoginDto ) validateLogin( requestContext, model );
			return new ResponseEntity<LoginDto>( model, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			model = ( LoginDto ) setErrorCode( model, ex );
			return new ResponseEntity<LoginDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.POST, value = "/isAuthenticated" )
	public ResponseEntity<LoginDto> isAuthenticated( HttpServletRequest requestContext,  @RequestBody LoginDto model )
	{
		try
		{
			model = ( LoginDto ) validateSession( requestContext, model );
			return new ResponseEntity<LoginDto>( model, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			model = ( LoginDto ) setErrorCode( model, ex );
			return new ResponseEntity<LoginDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.POST, value = "/logout" )
	public ResponseEntity<LoginDto> logout( @RequestBody LoginDto model )
	{
		try
		{
			model = ( LoginDto ) invalidateSession( model );
			return new ResponseEntity<LoginDto>( model, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			model = ( LoginDto ) setErrorCode( model, ex );
			return new ResponseEntity<LoginDto>( model, ex.getHttpStatus() );
		}
	}
	
	@RequestMapping( method = RequestMethod.POST, value = "/register" )
	ResponseEntity<RegistrationDto> registerUser( @RequestBody RegistrationDto model )
	{
		try
		{
			model = registrationService.registerUser( model );
			return new ResponseEntity<RegistrationDto>( model, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			model = ( RegistrationDto ) setErrorCode( model, ex );
			return new ResponseEntity<RegistrationDto>( model, ex.getHttpStatus() );
		}
		
	}
	
	@RequestMapping( method = RequestMethod.GET, value = "/ping" )
	public ResponseEntity<String> ping( )
	{
		return new ResponseEntity<String>( "Ping accepted", HttpStatus.OK );
	}

}
