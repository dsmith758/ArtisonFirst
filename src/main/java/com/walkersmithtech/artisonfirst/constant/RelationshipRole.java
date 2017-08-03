package com.walkersmithtech.artisonfirst.constant;

public enum RelationshipRole
{
	PERSON_LOCATION( DataType.PERSON, DataType.LOCATION ),
	PERSON_IMAGE( DataType.PERSON, DataType.FILE ),
	PERSON_COMPANY( DataType.PERSON, DataType.COMPANY ),
	PROFILE_IMAGE( DataType.PERSON, DataType.FILE ),
	COMPANY_LOCATION( DataType.COMPANY, DataType.LOCATION ),
	COMPANY_IMAGE( DataType.COMPANY, DataType.FILE ),
	COMPANY_LOGO( DataType.COMPANY, DataType.FILE ),
	COMPANY_PRODUCT( DataType.COMPANY, DataType.PRODUCT ),
	TEAM_MEMBER( DataType.TEAM, DataType.PERSON ),
	;
	
	public DataType targetType;
	public DataType sourceType;
	
	private RelationshipRole( DataType sourceType, DataType targetType )
	{
		this.sourceType = sourceType;
		this.targetType = targetType;
	}
}
