package com.walkersmithtech.artisonfirst.constant;

public enum IndexType
{
	FIRSTNAME( "FIRSTNAME" ),
	LASTNAME( "LASTNAME" ),
	EMAIL( "EMAIL" ),
	CITY( "CITY" ),
	STATE( "STATE" ),
	ZIP( "ZIP" ),
	COUNTRY( "COUNTRY" ),
	COMPANY_NAME( "COMPANY_NAME" ),
	BUSINESS_TYPE( "BUSINESS_TYPE" ),
	TEAM_NAME( "TEAM_NAME" ),
	CONTACT_INFO( "CONTACT_INFO" ),
	PRODUCT_FIELD( "PRODUCT_FIELD" ),
	SENDER( "SENDER" ),
	RECIPIENT( "RECIPIENT" ),
	MESSAGE( "MESSAGE" ),
	RESIDENT( "RESIDENT" ),
	ADDRESS( "ADDRESS" ),
	PRODUCT( "PRODUCT" ),
	PRODUCT_NAME( "PRODUCT_NAME" ),
	OWNER( "OWNER" ),
	PRINCIPLE( "PRINCIPLE" ),
	ORGANIZATION( "ORGANIZATION" ),
	;

	public String name;

	private IndexType( String name )
	{
		this.name = name;
	}
}
