package com.walkersmithtech.artisonfirst.data.model;

public abstract class BaseRelation extends BaseModel
{
	protected String role;
	protected String data;

	public String getData()
	{
		return data;
	}

	public void setData( String data )
	{
		this.data = data;
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
