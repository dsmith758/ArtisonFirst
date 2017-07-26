package com.walkersmithtech.artisonfirst.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( name = "file_data_index" )
@NamedQuery( name = "FileDataIndex.findAll", query = "SELECT r FROM FileDataIndex r" )
public class FileDataIndex
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Integer id;

	@Column( name = "uid", nullable = false, length = 64 )
	private String uid;

	@Column( name = "type", nullable = false, length = 255 )
	private String type;

	@Column( name = "data", nullable = false, length = 255 )
	private String data;

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

}
