package com.walkersmithtech.artisonfirst.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.data.model.dto.FileDto;
import com.walkersmithtech.artisonfirst.data.model.dto.ImageDto;
import com.walkersmithtech.artisonfirst.service.ServiceException;
import com.walkersmithtech.artisonfirst.service.impl.CompanyServiceImpl;
import com.walkersmithtech.artisonfirst.service.impl.FileManagerSerivceImpl;
import com.walkersmithtech.artisonfirst.service.impl.PersonImageServiceImpl;

public class FileController extends BaseController
{
	@Autowired
	private FileManagerSerivceImpl service;
	
	@Autowired
	private PersonImageServiceImpl personImageService;
	
	@Autowired
	private CompanyServiceImpl companyService;
	
	@RequestMapping( method = RequestMethod.POST, value = "/persons/{uid}/images" )
	public ResponseEntity<ImageDto> importPersonImage( HttpServletRequest requestContext, @RequestHeader( value = "session-id" ) String sessionId, @RequestHeader( value = "user-token" ) String token, @PathVariable String uid, @RequestParam( "file" ) MultipartFile file )
	{
		ImageDto auth = new ImageDto();
		if ( !file.isEmpty() )
		{
			try
			{
				auth = ( ImageDto ) validateSession( requestContext, auth, sessionId, token );
				auth.setFileName( file.getOriginalFilename() );
				auth = personImageService.addAndSetAsProfileImage( auth, uid, file.getBytes() );
				return new ResponseEntity<ImageDto>( auth, HttpStatus.OK );
			}
			catch ( ServiceException ex )
			{
				auth = ( ImageDto ) setErrorCode( auth, ex );
				return new ResponseEntity<ImageDto>( auth, ex.getHttpStatus() );
			}
			catch ( IOException e )
			{
				ServiceException ex = ErrorCode.SYSTEM_ERROR.exception;
				ex.initCause( e );
				auth = ( ImageDto ) setErrorCode( auth, ex );
				return new ResponseEntity<ImageDto>( auth, ex.getHttpStatus() );
			}
		}
		else
		{
			auth = ( ImageDto ) setErrorCode( auth, ErrorCode.FILE_MISSING.exception );
			return new ResponseEntity<ImageDto>( auth, ErrorCode.FILE_MISSING.exception.getHttpStatus() );
		}
	}
	
	@RequestMapping( method = RequestMethod.POST, value = "/companies/{uid}/logos" )
	public ResponseEntity<ImageDto> importCompanyLogo( HttpServletRequest requestContext, @RequestHeader( value = "session-id" ) String sessionId, @RequestHeader( value = "user-token" ) String token, @PathVariable String uid, @RequestParam( "file" ) MultipartFile file )
	{
		ImageDto auth = new ImageDto();
		if ( !file.isEmpty() )
		{
			try
			{
				auth = ( ImageDto ) validateSession( requestContext, auth, sessionId, token );
				auth.setFileName( file.getOriginalFilename() );
				auth = companyService.addAndSetCompanyLogo( auth, uid, file.getBytes() );
				return new ResponseEntity<ImageDto>( auth, HttpStatus.OK );
			}
			catch ( ServiceException ex )
			{
				auth = ( ImageDto ) setErrorCode( auth, ex );
				return new ResponseEntity<ImageDto>( auth, ex.getHttpStatus() );
			}
			catch ( IOException e )
			{
				ServiceException ex = ErrorCode.SYSTEM_ERROR.exception;
				ex.initCause( e );
				auth = ( ImageDto ) setErrorCode( auth, ex );
				return new ResponseEntity<ImageDto>( auth, ex.getHttpStatus() );
			}
		}
		else
		{
			auth = ( ImageDto ) setErrorCode( auth, ErrorCode.FILE_MISSING.exception );
			return new ResponseEntity<ImageDto>( auth, ErrorCode.FILE_MISSING.exception.getHttpStatus() );
		}
	}
	
	@RequestMapping( method = RequestMethod.POST, value = "/images" )
	public ResponseEntity<ImageDto> importImage( HttpServletRequest requestContext, @RequestHeader( value = "session-id" ) String sessionId, @RequestHeader( value = "user-token" ) String token, @RequestParam( "file" ) MultipartFile file )
	{
		ImageDto auth = new ImageDto();
		if ( !file.isEmpty() )
		{
			try
			{
				auth = ( ImageDto ) validateSession( requestContext, auth, sessionId, token );
				auth.setFileName( file.getOriginalFilename() );
				auth = ( ImageDto ) service.createFileRecord( file.getBytes(), auth );
				return new ResponseEntity<ImageDto>( auth, HttpStatus.OK );
			}
			catch ( ServiceException ex )
			{
				auth = ( ImageDto ) setErrorCode( auth, ex );
				return new ResponseEntity<ImageDto>( auth, ex.getHttpStatus() );
			}
			catch ( IOException e )
			{
				ServiceException ex = ErrorCode.SYSTEM_ERROR.exception;
				ex.initCause( e );
				auth = ( ImageDto ) setErrorCode( auth, ex );
				return new ResponseEntity<ImageDto>( auth, ex.getHttpStatus() );
			}
		}
		else
		{
			auth = ( ImageDto ) setErrorCode( auth, ErrorCode.FILE_MISSING.exception );
			return new ResponseEntity<ImageDto>( auth, ErrorCode.FILE_MISSING.exception.getHttpStatus() );
		}
	}
	
	@RequestMapping( method = RequestMethod.POST, value = "/files" )
	public ResponseEntity<FileDto> importFile( HttpServletRequest requestContext, @RequestHeader( value = "session-id" ) String sessionId, @RequestHeader( value = "user-token" ) String token, @RequestParam( "file" ) MultipartFile file )
	{
		FileDto auth = new FileDto();
		if ( !file.isEmpty() )
		{
			try
			{
				auth = ( FileDto ) validateSession( requestContext, auth, sessionId, token );
				auth.setFileName( file.getOriginalFilename() );
				auth = ( FileDto ) service.createFileRecord( file.getBytes(), auth );
				return new ResponseEntity<FileDto>( auth, HttpStatus.OK );
			}
			catch ( ServiceException ex )
			{
				auth = ( FileDto ) setErrorCode( auth, ex );
				return new ResponseEntity<FileDto>( auth, ex.getHttpStatus() );
			}
			catch ( IOException e )
			{
				ServiceException ex = ErrorCode.SYSTEM_ERROR.exception;
				ex.initCause( e );
				auth = ( FileDto ) setErrorCode( auth, ex );
				return new ResponseEntity<FileDto>( auth, ex.getHttpStatus() );
			}
		}
		else
		{
			auth = ( FileDto ) setErrorCode( auth, ErrorCode.FILE_MISSING.exception );
			return new ResponseEntity<FileDto>( auth, ErrorCode.FILE_MISSING.exception.getHttpStatus() );
		}
	}
}
