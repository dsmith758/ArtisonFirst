package com.walkersmithtech.artisonfirst.data.model;

import com.walkersmithtech.artisonfirst.constant.DataType;

/*
 * Holds the values of the field
 */
public class FieldValue extends BaseObject
{

	@Override
	public void initType()
	{
		this.type = DataType.FIELD_VALUE.name();
	}

}
