package com.walkersmithtech.artisonfirst.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( name = "object_relation_data_index" )
@NamedQuery( name = "ObjectRelationDataIndex.findAll", query = "SELECT o FROM ObjectRelationDataIndex o" )
public class ObjectRelationDataIndex
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int id;

	@Column( name = "uid", nullable = false, length = 64 )
	private String uid;

	@Column( name = "type", nullable = false, length = 32 )
	private String type;

	@Column( name = "data", nullable = false, length = 256 )
	private String data;

	public ObjectRelationDataIndex()
	{
	}

	public int getId()
	{
		return this.id;
	}

	public void setId( int id )
	{
		this.id = id;
	}

	public String getType()
	{
		return this.type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public String getUid()
	{
		return this.uid;
	}

	public void setUid( String uid )
	{
		this.uid = uid;
	}

	public String getData()
	{
		return this.data;
	}

	public void setData( String data )
	{
		this.data = data;
	}

}