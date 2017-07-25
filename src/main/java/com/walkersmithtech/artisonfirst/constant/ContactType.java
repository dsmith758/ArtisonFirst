package com.walkersmithtech.artisonfirst.constant;

public enum ContactType
{
	CELL( "Cell Phone" ),
	HOME( "Home Phone" ),
	OFFICE( "Office Phone" ),
	EMAIL( "Email Address" ),
	TWITTER( "Twitter" ),
	FACEBOOK( "Facebook" ),
	LINKEDIN( "LinkedIn" ),
	;
	
	public String name;
	
	private ContactType( String name )
	{
		this.name = name;
	}
	
	public static ContactType findByName( String value )
	{
		for ( ContactType contentType : values() )
		{
			if ( contentType.name().equals( value ) )
			{
				return contentType;
			}
		}
		return null;
	}
}
