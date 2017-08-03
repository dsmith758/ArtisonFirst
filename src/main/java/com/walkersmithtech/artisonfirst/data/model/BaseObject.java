package com.walkersmithtech.artisonfirst.data.model;

public abstract class BaseObject extends BaseModel
{
	protected String type;

	public abstract void initType();

	public BaseObject()
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

}
