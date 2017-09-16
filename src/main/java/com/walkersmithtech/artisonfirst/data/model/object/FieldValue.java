package com.walkersmithtech.artisonfirst.data.model.object;

import com.walkersmithtech.artisonfirst.data.model.BaseObject;

public class FieldValue extends BaseObject
{
	private Field field;
	private String value;

	public Field getField()
	{
		return field;
	}

	public void setField( Field field )
	{
		this.field = field;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue( String value )
	{
		this.value = value;
	}
}
