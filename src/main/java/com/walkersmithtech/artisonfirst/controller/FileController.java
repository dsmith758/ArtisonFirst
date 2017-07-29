package com.walkersmithtech.artisonfirst.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.data.model.dto.CompanyDto;
import com.walkersmithtech.artisonfirst.data.model.dto.FileDto;
import com.walkersmithtech.artisonfirst.data.model.dto.ImageDto;
import com.walkersmithtech.artisonfirst.data.model.dto.PersonDto;
import com.walkersmithtech.artisonfirst.service.ServiceException;
import com.walkersmithtech.artisonfirst.service.impl.CompanyImageServiceImpl;
import com.walkersmithtech.artisonfirst.service.impl.FileManagerSerivceImpl;
import com.walkersmithtech.artisonfirst.service.impl.PersonImageServiceImpl;

@Controller
public class FileController extends BaseController
{
	@Autowired
	private FileManagerSerivceImpl service;

	@Autowired
	private PersonImageServiceImpl personImageService;

	@Autowired
	private CompanyImageServiceImpl companyImageService;

	@RequestMapping( method = RequestMethod.POST, value = "/persons/{uid}/images", headers = ("content-type=multipart/*"))
	public ResponseEntity<PersonDto> importPersonImage( HttpServletRequest requestContext, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token, @PathVariable String uid, @RequestParam( "file" ) MultipartFile file )
	{
		ImageDto auth = new ImageDto();
		PersonDto person = new PersonDto();
		if ( !file.isEmpty() )
		{
			try
			{
				auth = ( ImageDto ) validateSession( requestContext, auth, sessionId, token );
				auth.setFileName( file.getOriginalFilename() );
				person = personImageService.addAndSetAsProfileImage( auth, uid, file.getBytes() );
				return new ResponseEntity<PersonDto>( person, HttpStatus.OK );
			}
			catch ( ServiceException ex )
			{
				person = ( PersonDto ) setErrorCode( auth, ex );
				return new ResponseEntity<PersonDto>( person, ex.getHttpStatus() );
			}
			catch ( IOException e )
			{
				ServiceException ex = ErrorCode.SYSTEM_ERROR.exception;
				ex.initCause( e );
				person = ( PersonDto ) setErrorCode( auth, ex );
				return new ResponseEntity<PersonDto>( person, ex.getHttpStatus() );
			}
		}
		else
		{
			person = ( PersonDto ) setErrorCode( auth, ErrorCode.FILE_MISSING.exception );
			return new ResponseEntity<PersonDto>( person, ErrorCode.FILE_MISSING.exception.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.POST, value = "/companies/{uid}/logos", headers = ("content-type=multipart/*"))
	public ResponseEntity<CompanyDto> importCompanyLogo( HttpServletRequest requestContext, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token, @PathVariable String uid, @RequestParam( "file" ) MultipartFile file )
	{
		ImageDto auth = new ImageDto();
		CompanyDto company = new CompanyDto();
		if ( !file.isEmpty() )
		{
			try
			{
				auth = ( ImageDto ) validateSession( requestContext, auth, sessionId, token );
				auth.setFileName( file.getOriginalFilename() );
				company = companyImageService.addAndSetCompanyLogo( auth, uid, file.getBytes() );
				return new ResponseEntity<CompanyDto>( company, HttpStatus.OK );
			}
			catch ( ServiceException ex )
			{
				company = ( CompanyDto ) setErrorCode( company, ex );
				return new ResponseEntity<CompanyDto>( company, ex.getHttpStatus() );
			}
			catch ( IOException e )
			{
				ServiceException ex = ErrorCode.SYSTEM_ERROR.exception;
				ex.initCause( e );
				company = ( CompanyDto ) setErrorCode( company, ex );
				return new ResponseEntity<CompanyDto>( company, ex.getHttpStatus() );
			}
		}
		else
		{
			company = ( CompanyDto ) setErrorCode( company, ErrorCode.FILE_MISSING.exception );
			return new ResponseEntity<CompanyDto>( company, ErrorCode.FILE_MISSING.exception.getHttpStatus() );
		}
	}

	@RequestMapping( method = RequestMethod.POST, value = "/images" )
	public ResponseEntity<ImageDto> importImage( HttpServletRequest requestContext, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token, @RequestParam( "file" ) MultipartFile file )
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
	public ResponseEntity<FileDto> importFile( HttpServletRequest requestContext, @RequestParam( "session-id" ) String sessionId, @RequestParam( "user-token" ) String token, @RequestParam( "file" ) MultipartFile file )
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

	@RequestMapping( method = RequestMethod.GET, value = "/images/{uid}" )
	public ResponseEntity<?> streamImage( @PathVariable( "uid" ) String uid )
	{
		try
		{
			FileDto metadata = service.getFileMetadata( uid );
			byte[] file = service.getFileData( uid );
			ByteArrayResource resource = new ByteArrayResource( file );

			HttpHeaders headers = new HttpHeaders();
			headers.add( "Cache-Control", "no-cache, no-store, must-revalidate" );
			headers.add( "Pragma", "no-cache" );
			headers.add( "Expires", "0" );

			return ResponseEntity.ok().headers( headers ).contentLength( file.length ).contentType( MediaType.parseMediaType( metadata.getMimeType() ) ).body( resource );
		}
		catch ( ServiceException e )
		{
			FileDto metadata = new FileDto();
			return new ResponseEntity<FileDto>( metadata, HttpStatus.NOT_FOUND );
		}
	}
}
