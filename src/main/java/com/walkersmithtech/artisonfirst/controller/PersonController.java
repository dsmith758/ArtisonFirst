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
import com.walkersmithtech.artisonfirst.data.model.Person;
import com.walkersmithtech.artisonfirst.data.model.dto.PersonDto;
import com.walkersmithtech.artisonfirst.service.ServiceException;
import com.walkersmithtech.artisonfirst.service.impl.PersonServiceImpl;

@RestController
public class PersonController extends BaseController
{
	@Autowired
	private PersonServiceImpl personService;

	@RequestMapping( method = RequestMethod.POST, value = "/persons" )
	public ResponseEntity<PersonDto> createPerson( HttpServletRequest requestContext, @RequestBody PersonDto model )
	{
		try
		{
			model = ( PersonDto ) validateSession( requestContext, model );
			Person person = personService.createPerson( model.getPerson() );
			model.setPerson( person );
			return new ResponseEntity<PersonDto>( model, HttpStatus.CREATED );
		}
		catch ( ServiceException ex )
		{
			model = ( PersonDto ) setErrorCode( model, ex );
			return new ResponseEntity<PersonDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.PUT, value = "/persons/{uid}" )
	public ResponseEntity<PersonDto> updatePerson( HttpServletRequest requestContext, @RequestBody PersonDto model, @PathVariable String uid )
	{
		try
		{
			if ( model.getPerson().getUid().equals( uid ) )
			{
				model = ( PersonDto ) validateSession( requestContext, model );
				Person person = personService.updatePerson( model.getPerson() );
				model.setPerson( person );
				return new ResponseEntity<PersonDto>( model, HttpStatus.OK );
			}
			throw ErrorCode.SYSTEM_BAD_REQUEST.exception;
		}
		catch ( ServiceException ex )
		{
			model = ( PersonDto ) setErrorCode( model, ex );
			return new ResponseEntity<PersonDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.DELETE, value = "/persons/{uid}" )
	public ResponseEntity<PersonDto> deletePerson( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		PersonDto auth = new PersonDto();
		try
		{
			auth = ( PersonDto ) validateSession( requestContext, auth, sessionId, token );
			personService.deletePerson( uid );
			return new ResponseEntity<PersonDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( PersonDto ) setErrorCode( auth, ex );
			return new ResponseEntity<PersonDto>( auth, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.GET, value = "/persons/{uid}" )
	public ResponseEntity<PersonDto> getPersonByUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		PersonDto auth = new PersonDto();
		try
		{
			auth = ( PersonDto ) validateSession( requestContext, auth, sessionId, token );
			Person person = personService.getPersonByUid( uid );
			auth.setPerson( person );
			return new ResponseEntity<PersonDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( PersonDto ) setErrorCode( auth, ex );
			return new ResponseEntity<PersonDto>( auth, ex.getHttpStatus() );
		}
	}

}
