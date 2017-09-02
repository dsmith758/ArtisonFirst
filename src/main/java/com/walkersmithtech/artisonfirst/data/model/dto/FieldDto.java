package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.List;

import com.walkersmithtech.artisonfirst.data.model.BaseDto;
import com.walkersmithtech.artisonfirst.data.model.object.Field;

public class FieldDto extends BaseDto
{
	private List<Field> fields;

	public List<Field> getFields()
	{
		return fields;
	}

	public void setFields( List<Field> fields )
	{
		this.fields = fields;
	}
}
