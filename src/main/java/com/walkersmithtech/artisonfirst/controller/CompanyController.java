package com.walkersmithtech.artisonfirst.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.data.model.Company;
import com.walkersmithtech.artisonfirst.data.model.dto.OrganizationDto;
import com.walkersmithtech.artisonfirst.service.ServiceException;
import com.walkersmithtech.artisonfirst.service.impl.CompanyServiceImpl;

@RestController
public class CompanyController extends BaseController
{
	@Autowired
	private CompanyServiceImpl service;

	@RequestMapping( method = RequestMethod.POST, value = "/companies" )
	public ResponseEntity<OrganizationDto> create( HttpServletRequest requestContext, @RequestBody OrganizationDto model )
	{
		try
		{
			model = ( OrganizationDto ) validateSession( requestContext, model );
			model = service.createOrganization( model );
			return new ResponseEntity<OrganizationDto>( model, HttpStatus.CREATED );
		}
		catch ( ServiceException ex )
		{
			model = ( OrganizationDto ) setErrorCode( model, ex );
			return new ResponseEntity<OrganizationDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.PUT, value = "/companies/{uid}" )
	public ResponseEntity<OrganizationDto> update( HttpServletRequest requestContext, @RequestBody OrganizationDto model, @RequestBody String uid )
	{
		try
		{
			if ( model.getCompany().getUid().equals( uid ) )
			{
				model = ( OrganizationDto ) validateSession( requestContext, model );
				model = service.updateOrganization( model );
				return new ResponseEntity<OrganizationDto>( model, HttpStatus.CREATED );
			}
			throw ErrorCode.SYSTEM_BAD_REQUEST.exception;
		}
		catch ( ServiceException ex )
		{
			model = ( OrganizationDto ) setErrorCode( model, ex );
			return new ResponseEntity<OrganizationDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.DELETE, value = "/companies/{uid}" )
	public ResponseEntity<OrganizationDto> delete( HttpServletRequest requestContext, @PathVariable String uid, @RequestHeader( value = "session-id" ) String sessionId, @RequestHeader( value = "user-token" ) String token )
	{
		OrganizationDto auth = new OrganizationDto();
		try
		{
			auth = ( OrganizationDto ) validateSession( requestContext, auth, sessionId, token );
			service.deleteModel( uid );
			return new ResponseEntity<OrganizationDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( OrganizationDto ) setErrorCode( auth, ex );
			return new ResponseEntity<OrganizationDto>( auth, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.GET, value = "/companies/{uid}" )
	public ResponseEntity<OrganizationDto> getByUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestHeader( value = "session-id" ) String sessionId, @RequestHeader( value = "user-token" ) String token )
	{
		OrganizationDto auth = new OrganizationDto();
		try
		{
			auth = ( OrganizationDto ) validateSession( requestContext, auth, sessionId, token );
			Company company = service.getCompanyByUid( uid );
			auth.setCompany( company );
			return new ResponseEntity<OrganizationDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( OrganizationDto ) setErrorCode( auth, ex );
			return new ResponseEntity<OrganizationDto>( auth, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.GET, value = "/persons/{uid}/companies" )
	public ResponseEntity<OrganizationDto> getCompanyByPersonUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestHeader( value = "session-id" ) String sessionId, @RequestHeader( value = "user-token" ) String token )
	{
		OrganizationDto auth = new OrganizationDto();
		try
		{
			auth = ( OrganizationDto ) validateSession( requestContext, auth, sessionId, token );
			auth = service.getOrganizationByPersonUid( auth );
			return new ResponseEntity<OrganizationDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( OrganizationDto ) setErrorCode( auth, ex );
			return new ResponseEntity<OrganizationDto>( auth, ex.getHttpStatus() );
		}
	}

}
