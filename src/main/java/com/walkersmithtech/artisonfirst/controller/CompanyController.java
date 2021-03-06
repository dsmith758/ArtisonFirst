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
import com.walkersmithtech.artisonfirst.core.builder.OrganizationBuilder;
import com.walkersmithtech.artisonfirst.data.model.dto.OrganizationDto;
import com.walkersmithtech.artisonfirst.data.model.dto.PrincipalOrganizationsDto;

@RestController
public class CompanyController extends BaseController
{
	@Autowired
	private OrganizationBuilder builder;
	
	@RequestMapping( method = RequestMethod.POST, value = "/companies" )
	public ResponseEntity<OrganizationDto> create( HttpServletRequest requestContext, @RequestBody OrganizationDto model )
	{
		try
		{
			model = ( OrganizationDto ) validateSession( requestContext, model );
			model = builder.createOrganization( model );
			return new ResponseEntity<OrganizationDto>( model, HttpStatus.CREATED );
		}
		catch ( ServiceException ex )
		{
			model = ( OrganizationDto ) setErrorCode( model, ex );
			return new ResponseEntity<OrganizationDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.PUT, value = "/companies/{uid}" )
	public ResponseEntity<OrganizationDto> update( HttpServletRequest requestContext, @PathVariable String uid, @RequestBody OrganizationDto model )
	{
		try
		{
			if ( model.getCompany().getUid().equals( uid ) )
			{
				model = ( OrganizationDto ) validateSession( requestContext, model );
				model = builder.updateOrganization( model );
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
	public ResponseEntity<OrganizationDto> delete( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		OrganizationDto auth = new OrganizationDto();
		try
		{
			auth = ( OrganizationDto ) validateSession( requestContext, auth, sessionId, token );
			builder.deleteOrganization( uid );
			return new ResponseEntity<OrganizationDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( OrganizationDto ) setErrorCode( auth, ex );
			return new ResponseEntity<OrganizationDto>( auth, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.GET, value = "/companies/{uid}" )
	public ResponseEntity<OrganizationDto> getByUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		OrganizationDto auth = new OrganizationDto();
		try
		{
			auth = ( OrganizationDto ) validateSession( requestContext, auth, sessionId, token );
			auth.getAccount().setCompanyUid( uid );
			auth  = builder.getOrganizationByCompanyUid( auth );
			return new ResponseEntity<OrganizationDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( OrganizationDto ) setErrorCode( auth, ex );
			return new ResponseEntity<OrganizationDto>( auth, ex.getHttpStatus() );
		}
	}
	
	@RequestMapping( method = RequestMethod.GET, value = "/persons/{uid}/companies" )
	public ResponseEntity<PrincipalOrganizationsDto> getCompaniesByPersonUId( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		OrganizationDto auth = new OrganizationDto();
		try
		{
			auth = ( OrganizationDto ) validateSession( requestContext, auth, sessionId, token );
			PrincipalOrganizationsDto organizations  = builder.getOrganizationsByPersonUid( auth );
			return new ResponseEntity<PrincipalOrganizationsDto>( organizations, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			PrincipalOrganizationsDto list = new PrincipalOrganizationsDto();
			list = ( PrincipalOrganizationsDto ) setErrorCode( list, ex );
			return new ResponseEntity<PrincipalOrganizationsDto>( list, HttpStatus.OK );
		}
	}

	@RequestMapping( method = RequestMethod.GET, value = "/persons/{uid}/default-company" )
	public ResponseEntity<OrganizationDto> getDetaultCompanyByPersonUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		OrganizationDto auth = new OrganizationDto();
		try
		{
			auth = ( OrganizationDto ) validateSession( requestContext, auth, sessionId, token );
			auth = builder.getDefaultOrganizationByPersonUid( auth );
			return new ResponseEntity<OrganizationDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( OrganizationDto ) setErrorCode( auth, ex );
			return new ResponseEntity<OrganizationDto>( auth, ex.getHttpStatus() );
		}
	}

}
