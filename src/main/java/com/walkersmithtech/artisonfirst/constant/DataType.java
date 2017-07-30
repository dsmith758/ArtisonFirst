package com.walkersmithtech.artisonfirst.constant;

public enum DataType
{
	COMPANY( "COMPANY" ), 
	CONTACT( "CONTACT" ), 
	FIELD_VALUE( "FIELD_VALUE" ),
	LOCATION( "LOCATION" ), 
	PERSON( "PERSON" ), 
	PRODUCT( "PRODUCT" ),
	PRODUCT_FIELD( "PRODUCT_FIELD" ),
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
