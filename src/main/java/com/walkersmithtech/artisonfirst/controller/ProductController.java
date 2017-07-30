package com.walkersmithtech.artisonfirst.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkersmithtech.artisonfirst.data.model.dto.ProductDto;

@RestController
public class ProductController extends BaseController
{
	@RequestMapping( method = RequestMethod.POST, value = "/products" )
	public ResponseEntity<ProductDto> createProduct( HttpServletRequest requestContext, @RequestBody ProductDto model )
	{
		return null;
	}
	
	@RequestMapping( method = RequestMethod.PUT, value = "/products/{udi}" )
	public ResponseEntity<ProductDto> updateProduct( HttpServletRequest requestContext, @RequestBody ProductDto model, @PathVariable String uid )
	{
		return null;
	}
	
	@RequestMapping( method = RequestMethod.DELETE, value = "/products/{uid}" )
	public ResponseEntity<ProductDto> deleteProduct( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		return null;
	}
	
	@RequestMapping( method = RequestMethod.GET, value = "/products/{uid}" )
	public ResponseEntity<ProductDto> getProductByUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		return null;
	}

}
