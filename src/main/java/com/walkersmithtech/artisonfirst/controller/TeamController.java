package com.walkersmithtech.artisonfirst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walkersmithtech.artisonfirst.data.model.Team;
import com.walkersmithtech.artisonfirst.service.impl.TeamServiceImpl;

@RestController
public class TeamController
{
	@Autowired
	private TeamServiceImpl service;

	@RequestMapping( method = RequestMethod.POST, value = "/teams" )
	public ResponseEntity< Team > create( @RequestBody Team model )
	{
		model = service.createModel( model );
		return new ResponseEntity< Team >( model, HttpStatus.OK );
	}

	@RequestMapping( method = RequestMethod.PUT, value = "/teams/{uid}" )
	public ResponseEntity< Team > update( @RequestBody Team model, @RequestBody String uid )
	{
		model = service.updateModel( model );
		return new ResponseEntity< Team >( model, HttpStatus.OK );
	}

	@RequestMapping( method = RequestMethod.DELETE, value = "/teams/{uid}" )
	public ResponseEntity< ? > delete( @RequestBody String uid )
	{
		boolean status = service.deleteModel( uid );
		String message;
		if ( status )
		{
			message = "OK";
			return new ResponseEntity< >( message, HttpStatus.OK );
		}
		message = "Record Not Found";
		return new ResponseEntity< >( message, HttpStatus.NOT_FOUND );
	}

	@RequestMapping( method = RequestMethod.GET, value = "/teams/{uid}" )
	public ResponseEntity< Team > getByUid( @RequestBody String uid )
	{
		Team model = service.getModelByUid( uid );
		return new ResponseEntity< Team >( model, HttpStatus.OK );
	}

	@RequestMapping( method = RequestMethod.GET, value = "/teams" )
	public List< Team > getAll()
	{
		List< Team > models = service.getAllModels();
		return models;
	}

}
