package com.walkersmithtech.artisonfirst.data.model;

public abstract class BaseModel
{
	protected Integer id;
	protected String uid;

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

}
