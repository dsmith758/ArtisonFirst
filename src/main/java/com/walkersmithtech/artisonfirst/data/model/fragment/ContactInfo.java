package com.walkersmithtech.artisonfirst.data.model.fragment;

public class ContactInfo
{
	private String contactType;
	private String value;

	public String getContactType()
	{
		if ( contactType == null )
		{
			return "";
		}
		return contactType;
	}

	public void setContactType( String contactType )
	{
		this.contactType = contactType;
	}
	
	public String getValue()
	{
		if ( value == null )
		{
			return "";
		}
		return value;
	}

	public void setValue( String value )
	{
		this.value = value;
	}
}
