package com.walkersmithtech.artisonfirst.constant;

public enum DataType
{
	PERSON( "PERSON" ), 
	LOCATION( "LOCATION" ), 
	COMPANY( "COMPANY" ), 
	CONTACT( "CONTACT" ), 
	TEAM( "TEAM" ), 
	USER_PROFILE( "USER_PROFILE" ),
	USER_DATA( "USER_DATA" ),
	;

	public String type;

	private DataType( String type )
	{
		this.type = type;
	}

	public static DataType findByType( String value )
	{
		for ( DataType dataType : values() )
		{
			if ( dataType.type.equals( value ) )
			{
				return dataType;
			}
		}
		return null;
	}
}
