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

import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.core.builder.ProductBuilder;
import com.walkersmithtech.artisonfirst.core.service.ProductService;
import com.walkersmithtech.artisonfirst.data.model.dto.CompanyProductDto;
import com.walkersmithtech.artisonfirst.data.model.dto.ProductDto;

@RestController
public class ProductController extends BaseController
{
	@Autowired
	private ProductBuilder builder;

	@Autowired
	private ProductService service;

	@RequestMapping( method = RequestMethod.POST, value = "/products" )
	public ResponseEntity<ProductDto> createProduct( HttpServletRequest requestContext, @RequestBody ProductDto model )
	{
		try
		{
			model = ( ProductDto ) validateSession( requestContext, model );
			model = builder.createProduct( model );
			return new ResponseEntity<ProductDto>( model, HttpStatus.CREATED );
		}
		catch ( ServiceException ex )
		{
			model = ( ProductDto ) setErrorCode( model, ex );
			return new ResponseEntity<ProductDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.PUT, value = "/products/{uid}" )
	public ResponseEntity<ProductDto> updateProduct( HttpServletRequest requestContext, @RequestBody ProductDto model, @PathVariable String uid )
	{
		try
		{
			model = ( ProductDto ) validateSession( requestContext, model );
			model = builder.updateProduct( model );
			return new ResponseEntity<ProductDto>( model, HttpStatus.CREATED );
		}
		catch ( ServiceException ex )
		{
			model = ( ProductDto ) setErrorCode( model, ex );
			return new ResponseEntity<ProductDto>( model, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.DELETE, value = "/products/{uid}" )
	public ResponseEntity<ProductDto> deleteProduct( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		ProductDto auth = new ProductDto();
		try
		{
			auth = ( ProductDto ) validateSession( requestContext, auth, sessionId, token );
			service.deleteModel( uid );
			return new ResponseEntity<ProductDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( ProductDto ) setErrorCode( auth, ex );
			return new ResponseEntity<ProductDto>( auth, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.GET, value = "/products/{uid}" )
	public ResponseEntity<ProductDto> getProductByUid( HttpServletRequest requestContext, @PathVariable String uid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		ProductDto auth = new ProductDto();
		try
		{
			auth = ( ProductDto ) validateSession( requestContext, auth, sessionId, token );
			auth = builder.getProductByUid( uid, auth );
			return new ResponseEntity<ProductDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( ProductDto ) setErrorCode( auth, ex );
			return new ResponseEntity<ProductDto>( auth, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.GET, value = "/companies/{companyUid}/products/{productUid}" )
	public ResponseEntity<ProductDto> getCompanyProduct( HttpServletRequest requestContext, @PathVariable String companyUid, @PathVariable String productUid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		ProductDto auth = new ProductDto();
		try
		{
			auth = ( ProductDto ) validateSession( requestContext, auth, sessionId, token );
			auth = builder.getCompanyProduct( productUid, companyUid, auth );
			return new ResponseEntity<ProductDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( ProductDto ) setErrorCode( auth, ex );
			return new ResponseEntity<ProductDto>( auth, ex.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.GET, value = "/companies/{companyUid}/products" )
	public ResponseEntity<CompanyProductDto> getCompanyProducts( HttpServletRequest requestContext, @PathVariable String companyUid, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token )
	{
		CompanyProductDto auth = new CompanyProductDto();
		try
		{
			auth = ( CompanyProductDto ) validateSession( requestContext, auth, sessionId, token );
			auth = builder.getCompanyProducts( companyUid, auth );
			return new ResponseEntity<CompanyProductDto>( auth, HttpStatus.OK );
		}
		catch ( ServiceException ex )
		{
			auth = ( CompanyProductDto ) setErrorCode( auth, ex );
			return new ResponseEntity<CompanyProductDto>( auth, ex.getHttpStatus() );
		}
	}

}
