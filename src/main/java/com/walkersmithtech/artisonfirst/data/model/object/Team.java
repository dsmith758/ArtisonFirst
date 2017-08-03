package com.walkersmithtech.artisonfirst.data.model.object;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.data.model.BaseObject;

public class Team extends BaseObject
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
