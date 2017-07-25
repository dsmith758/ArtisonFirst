package com.walkersmithtech.artisonfirst.data.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude( Include.NON_EMPTY )
public abstract class BaseDto
{
	protected AccountDto account;
	protected String errorCode;
	protected String errorMessage;
	protected String cause;
	protected String ipAddress;

	public AccountDto getAccount()
	{
		return account;
	}

	public void setAccount( AccountDto account )
	{
		this.account = account;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode( String errorCode )
	{
		this.errorCode = errorCode;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage( String errorMessage )
	{
		this.errorMessage = errorMessage;
	}

	public String getCause()
	{
		return cause;
	}

	public void setCause( String cause )
	{
		this.cause = cause;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress( String ipAddress )
	{
		this.ipAddress = ipAddress;
	}

}
