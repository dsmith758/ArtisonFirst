package com.walkersmithtech.artisonfirst.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "object_data" )
@NamedQuery( name = "ObjectData.findAll", query = "SELECT o FROM ObjectData o" )
public class ObjectData
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", unique = true, nullable = false, length = 11 )
	private Integer id;

	@Column( name = "uid", unique = true, nullable = false, length = 64 )
	private String uid;

	@Column( name = "type", unique = true, nullable = false, length = 16 )
	private String type;

	@Column( name = "status", unique = true, nullable = false, length = 10 )
	private Integer status;

	@Lob
	@Column( name = "data", nullable = false )
	private String data;

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "created_on", nullable = false )
	private Date createdOn;

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "updated_on", nullable = false )
	private Date updatedOn;

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid( String uid )
	{
		this.uid = uid;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus( Integer status )
	{
		this.status = status;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public String getData()
	{
		return data;
	}

	public void setData( String data )
	{
		this.data = data;
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