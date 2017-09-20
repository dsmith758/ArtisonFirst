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

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.core.builder.ContactBuilder;
import com.walkersmithtech.artisonfirst.data.model.dto.ContactDto;
import com.walkersmithtech.artisonfirst.data.model.dto.PersonContactsDto;

public class ContactController extends BaseController
{
	@Autowired
	private ContactBuilder builder;
	
	@RequestMapping( method = RequestMethod.POST, value = "/contacts" )
	public ResponseEntity<ContactDto> create( HttpServletRequest requestContext, @RequestBody ContactDto model )
	{
		try
		{
			model = ( ContactDto ) validateSession( requestContext, model );
			model = builder.createContact( model );
			return new ResponseEntity<ContactDto>( model, HttpStatus.CREATED );
		}
		catch ( ServiceException ex )
		{
			model = ( ContactDto ) setErrorCode( model, ex );
			return new ResponseEntity<ContactDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.PUT, value = "/contacts/{uid}" )
	public ResponseEntity<ContactDto> update( HttpServletRequest requestContext, @PathVariable String uid, @RequestBody ContactDto model )
	{
		try
		{
			if ( model.getContact().getUid().equals( uid ) )
			{
				model = ( ContactDto ) validateSession( requestContext, model );
				model = builder.updateContact( model );
				return new ResponseEntity<ContactDto>( model, HttpStatus.CREATED );
			}
			throw ErrorCode.SYSTEM_BAD_REQUEST.exception;
		}
		catch ( ServiceException ex )
		{
			model = ( ContactDto ) setErrorCode( model, ex );
			return new ResponseEntity<ContactDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.DELETE, value = "/contacts/{uid}" )
	public ResponseEntity<ContactDto> delete( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		ContactDto auth = new ContactDto();
		try
		{
			auth = ( ContactDto ) validateSession( requestContext, auth, sessionId, token );
			builder.deleteContact( uid );
			return new ResponseEntity<ContactDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( ContactDto ) setErrorCode( auth, ex );
			return new ResponseEntity<ContactDto>( auth, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.GET, value = "/contacts/{uid}" )
	public ResponseEntity<ContactDto> getByUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		ContactDto auth = new ContactDto();
		try
		{
			auth = ( ContactDto ) validateSession( requestContext, auth, sessionId, token );
			auth.getAccount().setCompanyUid( uid );
			auth  = builder.getContactByUid( auth, uid );
			return new ResponseEntity<ContactDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( ContactDto ) setErrorCode( auth, ex );
			return new ResponseEntity<ContactDto>( auth, ex.getHttpStatus() );
		}
	}
	
	@RequestMapping( method = RequestMethod.GET, value = "/persons/{uid}/contacts" )
	public ResponseEntity<PersonContactsDto> getByPersonUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		ContactDto auth = new ContactDto();
		try
		{
			auth = ( ContactDto ) validateSession( requestContext, auth, sessionId, token );
			PersonContactsDto organizations  = builder.getPersonContacts( auth );
			return new ResponseEntity<PersonContactsDto>( organizations, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			PersonContactsDto list = new PersonContactsDto();
			list = ( PersonContactsDto ) setErrorCode( list, ex );
			return new ResponseEntity<PersonContactsDto>( list, HttpStatus.OK );
		}
	}
}
