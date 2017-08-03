package com.walkersmithtech.artisonfirst.data.model.fragment;

import java.util.List;

public class ProductFieldDefinition
{
	private String fieldUid;
	private String label;
	private List<String> values;
	private Boolean isUnique;
	private Boolean isPublic;

	public String getFieldUid()
	{
		return fieldUid;
	}

	public void setFieldUid( String fieldUid )
	{
		this.fieldUid = fieldUid;
	}

	public List<String> getValues()
	{
		return values;
	}

	public void setValues( List<String> values )
	{
		this.values = values;
	}

	public Boolean getIsUnique()
	{
		return isUnique;
	}

	public void setIsUnique( Boolean isUnique )
	{
		this.isUnique = isUnique;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel( String label )
	{
		this.label = label;
	}

	public Boolean getIsPublic()
	{
		return isPublic;
	}

	public void setIsPublic( Boolean isPublic )
	{
		this.isPublic = isPublic;
	}
}
