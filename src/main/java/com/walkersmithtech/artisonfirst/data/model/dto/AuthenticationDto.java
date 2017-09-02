package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class AuthenticationDto
{
	private String loginName;
	private String personUid;
	private String sessionId;
	private String ipAddress;
	private Date expiresOn;
	private Map<String, Object> data;

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName( String loginName )
	{
		this.loginName = loginName;
	}

	public String getPersonUid()
	{
		return personUid;
	}

	public void setPersonUid( String personUid )
	{
		this.personUid = personUid;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId( String sessionId )
	{
		this.sessionId = sessionId;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress( String ipAddress )
	{
		this.ipAddress = ipAddress;
	}

	public Date getExpiresOn()
	{
		return expiresOn;
	}

	public void setExpiresOn( Date expiresOn )
	{
		this.expiresOn = expiresOn;
	}

	@JsonAnyGetter
	public Map<String, Object> getData()
	{
		return data;
	}

	@JsonAnySetter
	public void setData( Map<String, Object> data )
	{
		this.data = data;
	}

}
