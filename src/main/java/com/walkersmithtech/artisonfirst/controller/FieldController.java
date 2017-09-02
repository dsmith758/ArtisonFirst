package com.walkersmithtech.artisonfirst.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkersmithtech.artisonfirst.data.model.dto.FieldDto;

@RestController
public class FieldController extends BaseController
{
	@RequestMapping( method = RequestMethod.POST, value = "/fields" )
	public ResponseEntity<FieldDto> create( HttpServletRequest requestContext, @RequestBody FieldDto model )
	{
		return null;
	}
	
	@RequestMapping( method = RequestMethod.PUT, value = "/fields" )
	public ResponseEntity<FieldDto> update( HttpServletRequest requestContext, @RequestBody FieldDto model )
	{
		return null;
	}
	
	@RequestMapping( method = RequestMethod.DELETE, value = "/fields" )
	public ResponseEntity<FieldDto> delete( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		return null;
	}
	
	@RequestMapping( method = RequestMethod.GET, value = "/fields/{uid}" )
	public ResponseEntity<FieldDto> getByUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		return null;
	}
	
	@RequestMapping( method = RequestMethod.GET, value = "/fields/global" )
	public ResponseEntity<FieldDto> getGlobalFields( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		return null;
	}
	
	@RequestMapping( method = RequestMethod.GET, value = "/companies/{uid}/fields" )
	public ResponseEntity<FieldDto> getCompanyFields( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		return null;
	}
	

}
