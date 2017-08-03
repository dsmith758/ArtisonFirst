package com.walkersmithtech.artisonfirst.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude( Include.NON_EMPTY )
public class Account
{
	private String personUid;
	private String companyUid;
	private String loginName;
	private String displayName;
	private String sessionId;
	private String token;
	private Boolean authenticated;
	private String password;
	private String confirmationCode;

	public String getPersonUid()
	{
		return personUid;
	}

	public void setPersonUid( String personUid )
	{
		this.personUid = personUid;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName( String loginName )
	{
		this.loginName = loginName;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName( String displayName )
	{
		this.displayName = displayName;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId( String sessionId )
	{
		this.sessionId = sessionId;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken( String token )
	{
		this.token = token;
	}

	public Boolean getAuthenticated()
	{
		return authenticated;
	}

	public void setAuthenticated( Boolean authenticated )
	{
		this.authenticated = authenticated;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public String getConfirmationCode()
	{
		return confirmationCode;
	}

	public void setConfirmationCode( String confirmationCode )
	{
		this.confirmationCode = confirmationCode;
	}

	public String getCompanyUid()
	{
		return companyUid;
	}

	public void setCompanyUid( String companyUid )
	{
		this.companyUid = companyUid;
	}

}
