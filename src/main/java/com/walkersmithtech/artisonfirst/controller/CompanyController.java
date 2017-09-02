package com.walkersmithtech.artisonfirst.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.core.builder.CompanyBuilder;
import com.walkersmithtech.artisonfirst.core.service.CompanyService;
import com.walkersmithtech.artisonfirst.data.model.dto.CompanyDto;

@RestController
public class CompanyController extends BaseController
{
	@Autowired
	private CompanyBuilder builder;
	
	@Autowired
	private CompanyService service;

	@RequestMapping( method = RequestMethod.POST, value = "/companies" )
	public ResponseEntity<CompanyDto> create( HttpServletRequest requestContext, @RequestBody CompanyDto model )
	{
		try
		{
			model = ( CompanyDto ) validateSession( requestContext, model );
			model = builder.createOrganization( model );
			return new ResponseEntity<CompanyDto>( model, HttpStatus.CREATED );
		}
		catch ( ServiceException ex )
		{
			model = ( CompanyDto ) setErrorCode( model, ex );
			return new ResponseEntity<CompanyDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.PUT, value = "/companies/{uid}" )
	public ResponseEntity<CompanyDto> update( HttpServletRequest requestContext, @PathVariable String uid, @RequestBody CompanyDto model )
	{
		try
		{
			if ( model.getCompany().getUid().equals( uid ) )
			{
				model = ( CompanyDto ) validateSession( requestContext, model );
				model = builder.updateOrganization( model );
				return new ResponseEntity<CompanyDto>( model, HttpStatus.CREATED );
			}
			throw ErrorCode.SYSTEM_BAD_REQUEST.exception;
		}
		catch ( ServiceException ex )
		{
			model = ( CompanyDto ) setErrorCode( model, ex );
			return new ResponseEntity<CompanyDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.DELETE, value = "/companies/{uid}" )
	public ResponseEntity<CompanyDto> delete( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		CompanyDto auth = new CompanyDto();
		try
		{
			auth = ( CompanyDto ) validateSession( requestContext, auth, sessionId, token );
			service.deleteModel( uid );
			return new ResponseEntity<CompanyDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( CompanyDto ) setErrorCode( auth, ex );
			return new ResponseEntity<CompanyDto>( auth, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.GET, value = "/companies/{uid}" )
	public ResponseEntity<CompanyDto> getByUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		CompanyDto auth = new CompanyDto();
		try
		{
			auth = ( CompanyDto ) validateSession( requestContext, auth, sessionId, token );
			auth = builder.getOrganizationByCompanyUid( uid, auth );
			return new ResponseEntity<CompanyDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( CompanyDto ) setErrorCode( auth, ex );
			return new ResponseEntity<CompanyDto>( auth, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.GET, value = "/persons/{uid}/companies" )
	public ResponseEntity<CompanyDto> getCompanyByPersonUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		CompanyDto auth = new CompanyDto();
		try
		{
			auth = ( CompanyDto ) validateSession( requestContext, auth, sessionId, token );
			auth = builder.getOrganizationByPersonUid( auth.getAccount().getPersonUid(), auth );
			return new ResponseEntity<CompanyDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( CompanyDto ) setErrorCode( auth, ex );
			return new ResponseEntity<CompanyDto>( auth, ex.getHttpStatus() );
		}
	}

}
