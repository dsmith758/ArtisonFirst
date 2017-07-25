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
@Table( name = "user_account" )
@NamedQuery( name = "UserAccount.findAll", query = "SELECT o FROM UserAccount o" )
public class UserAccount
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Integer id;

	@Column( name = "person_uid", nullable = false, length = 64 )
	private String personUid;

	@Column( name = "login_name", nullable = false, length = 255 )
	private String loginName;

	@Column( name = "display_name", nullable = false, length = 255 )
	private String displayName;

	@Column( name = "confirmation_code", nullable = false, length = 255 )
	private String confirmationCode;

	@Column( name = "password", nullable = false, length = 64 )
	private String password;

	@Column( name = "active", nullable = false, length = 10 )
	private Integer active;

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "updated_on", nullable = false )
	private Date updatedOn;

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "created_on", nullable = false )
	private Date createdOn;

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

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

	public String getConfirmationCode()
	{
		return confirmationCode;
	}

	public void setConfirmationCode( String confirmationCode )
	{
		this.confirmationCode = confirmationCode;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public Integer getActive()
	{
		return active;
	}

	public void setActive( Integer active )
	{
		this.active = active;
	}

	public Date getUpdatedOn()
	{
		return updatedOn;
	}

	public void setUpdatedOn( Date updatedOn )
	{
		this.updatedOn = updatedOn;
	}

	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn( Date createdOn )
	{
		this.createdOn = createdOn;
	}

}
