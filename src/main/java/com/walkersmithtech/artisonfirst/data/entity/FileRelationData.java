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
@Table( name = "file_relation_data" )
@NamedQuery( name = "FileRelationData.findAll", query = "SELECT r FROM FileRelationData r" )
public class FileRelationData
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Integer id;

	@Column( name = "uid", unique = true, nullable = false, length = 64 )
	private String uid;

	@Column( name = "role", unique = true, nullable = false, length = 32 )
	private String role;

	@Lob
	@Column( name = "data", nullable = false )
	private String data;

	@Column( name = "object_uid", nullable = false, length = 64 )
	private String objectUid;

	@Column( name = "file_uid", nullable = false, length = 64 )
	private String fileUid;

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

	public String getData()
	{
		return data;
	}

	public void setData( String data )
	{
		this.data = data;
	}

	public String getObjectUid()
	{
		return objectUid;
	}

	public void setObjectUid( String objectUid )
	{
		this.objectUid = objectUid;
	}

	public String getFileUid()
	{
		return fileUid;
	}

	public void setFileUid( String fileUid )
	{
		this.fileUid = fileUid;
	}

	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn( Date createdOn )
	{
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn()
	{
		return updatedOn;
	}

	public void setUpdatedOn( Date updatedOn )
	{
		this.updatedOn = updatedOn;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole( String role )
	{
		this.role = role;
	}
}
