package com.walkersmithtech.artisonfirst.data.model;

import com.walkersmithtech.artisonfirst.constant.DataType;

public class Team extends BaseModel
{
	private String name;
	
	public void initType()
	{
		this.type = DataType.TEAM.name();
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}
}
