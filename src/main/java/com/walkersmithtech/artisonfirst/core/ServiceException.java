package com.walkersmithtech.artisonfirst.core;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception
{

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;
	private HttpStatus httpStatus;

	public ServiceException( String errorCode, String errorMessage, HttpStatus httpStatus )
	{
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public HttpStatus getHttpStatus()
	{
		return httpStatus;
	}

}
