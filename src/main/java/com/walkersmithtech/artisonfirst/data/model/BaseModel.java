package com.walkersmithtech.artisonfirst.data.model;

public abstract class BaseModel
{
	protected String uid;
	protected String type;

	public abstract void initType();

	public BaseModel()
	{
		initType();
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
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
