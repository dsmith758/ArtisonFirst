package com.walkersmithtech.artisonfirst.data.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude( Include.NON_EMPTY )
public class BaseObject extends BaseModel
{
	protected String type;
	
	@JsonIgnore
	private Map<String, Object> data;

	public void initType()
	{
		
	}

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

	@JsonAnySetter
	public void addData( String key, Object value )
	{
		if ( data == null )
		{
			data = new HashMap<String, Object>();
		}
		data.put( key, value );
	}

	@JsonAnyGetter
	public Map<String, Object> getData()
	{
		return data;
	}

}
