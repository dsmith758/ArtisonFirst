package com.walkersmithtech.artisonfirst.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "user_session" )
@NamedQuery( name = "UserSession.findAll", query = "SELECT o FROM UserSession o" )
public class UserSession
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id" )
	private Integer id;

	@Column( name = "session_id" )
	private String sessionId;

	@Column( name = "ip_address" )
	private String ipAddress;

	@Column( name = "token" )
	private String token;

	@Column( name = "salt" )
	private String salt;

	@Column( name = "never_expires" )
	private Integer neverExpires = 0;

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "initialized_on" )
	private Date initializedOn;

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "last_touched_on" )
	private Date lastTouchedOn;

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
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

	public String getSalt()
	{
		return salt;
	}

	public void setSalt( String salt )
	{
		this.salt = salt;
	}

	public Date getInitializedOn()
	{
		return initializedOn;
	}

	public void setInitializedOn( Date initializedOn )
	{
		this.initializedOn = initializedOn;
	}

	public Date getLastTouchedOn()
	{
		return lastTouchedOn;
	}

	public void setLastTouchedOn( Date lastTouchedOn )
	{
		this.lastTouchedOn = lastTouchedOn;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress( String ipAddress )
	{
		this.ipAddress = ipAddress;
	}

	public Integer getNeverExpires()
	{
		if ( this.neverExpires == null )
		{
			neverExpires = 0;
		}
		return neverExpires;
	}

	public void setNeverExpires( Integer neverExpires )
	{
		this.neverExpires = neverExpires;
	}

}
