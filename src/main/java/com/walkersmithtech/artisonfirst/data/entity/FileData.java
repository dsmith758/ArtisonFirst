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
@Table( name = "file_data" )
@NamedQuery( name = "FileData.findAll", query = "SELECT r FROM FileData r" )
public class FileData
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", unique = true, nullable = false, length = 11 )
	private Integer id;

	@Column( name = "uid", unique = true, nullable = false, length = 64 )
	private String uid;

	@Column( name = "doc_type", unique = true, nullable = false, length = 32 )
	private String docType;

	@Lob
	@Column( name = "data", nullable = false )
	private String data;

	@Lob
	@Column( name = "file", nullable = false )
	private byte[] file;

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

	public String getDocType()
	{
		return docType;
	}

	public void setDocType( String docType )
	{
		this.docType = docType;
	}

	public String getData()
	{
		return data;
	}

	public void setData( String data )
	{
		this.data = data;
	}

	public byte[] getFile()
	{
		return file;
	}

	public void setFile( byte[] file )
	{
		this.file = file;
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
}
