package com.walkersmithtech.artisonfirst.constant;

public enum DataType
{
	COMPANY( "COMPANY" ), 
	FIELD( "FIELD" ),
	FILE( "FILE" ),
	INVENTORY_ITEM( "INVENTORY_ITEM" ),
	LOCATION( "LOCATION" ), 
	MESSAGE("MESSAGE"),
	PERSON( "PERSON" ), 
	PRODUCT( "PRODUCT" ),
	PRODUCT_UNIT( "PRODUCT_UNIT" ),
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
