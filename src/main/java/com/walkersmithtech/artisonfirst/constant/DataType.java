package com.walkersmithtech.artisonfirst.constant;

public enum DataType
{
	COMPANY( "COMPANY" ), 
	FIELD( "FIELD" ),
	FILE( "FILE" ),
	LOCATION( "LOCATION" ), 
	PERSON( "PERSON" ), 
	PRODUCT( "PRODUCT" ),
	TEAM( "TEAM" ), 
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
