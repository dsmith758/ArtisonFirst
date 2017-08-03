package com.walkersmithtech.artisonfirst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walkersmithtech.artisonfirst.component.service.LocationService;
import com.walkersmithtech.artisonfirst.data.model.object.Location;

@RestController
public class LocationController
{
	
	@Autowired
	private LocationService service;
	
	@RequestMapping( method = RequestMethod.POST, value = "/locations" )
	public ResponseEntity< Location > create( @RequestBody Location model )
	{
		model = service.createModel( model );
		return new ResponseEntity< Location >( model, HttpStatus.OK );
	}
	
	@RequestMapping( method = RequestMethod.PUT, value = "/locations/{uid}" )
	public ResponseEntity< Location > update( @RequestBody Location model, @RequestBody String uid )
	{
		model = service.updateModel( model );
		return new ResponseEntity< Location >( model, HttpStatus.OK );
	}

	@RequestMapping( method = RequestMethod.DELETE, value = "/locations/{uid}" )
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
	
	@RequestMapping( method = RequestMethod.GET, value = "/locations/{uid}" )
	public ResponseEntity< Location > getByUid( @RequestBody String uid )
	{
		Location model = service.getModelByUid( uid );
		return new ResponseEntity< Location >( model, HttpStatus.OK );
	}
	
	@RequestMapping( method = RequestMethod.GET, value = "/locations" )
	public List< Location > getAll()
	{
		List< Location > models = service.getAllModels();
		return models;
	}
	
}
