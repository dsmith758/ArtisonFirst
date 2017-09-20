package com.walkersmithtech.artisonfirst.constant;

import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.File;
import com.walkersmithtech.artisonfirst.data.model.object.Location;
import com.walkersmithtech.artisonfirst.data.model.object.Message;
import com.walkersmithtech.artisonfirst.data.model.object.Person;
import com.walkersmithtech.artisonfirst.data.model.object.Product;
import com.walkersmithtech.artisonfirst.data.model.object.Team;

public enum RelationshipType
{
	COMPANY_LOCATION(  Company.class, Location.class ), 
	COMPANY_IMAGE(  Company.class, File.class ), 
	COMPANY_LOGO(  Company.class, File.class ), 
	COMPANY_PRODUCT(  Company.class, Product.class ),
	MESSAGE_SENDER( Message.class, Person.class ),
	MESSAGE_RECIPIENT( Message.class, Person.class ),
	MESSAGE_CCRECIPIENT( Message.class, Person.class ),
	MESSAGE_bCCRECIPIENT( Message.class, Person.class ),
	PERSON_LOCATION(  Person.class, Location.class ), 
	PERSON_IMAGE( Person.class, File.class ), 
	PERSON_COMPANY(  Person.class, Company.class ), 
	PERSON_CONTACT( Person.class, Person.class ),
	PROFILE_IMAGE(  Person.class, File.class ), 
	PRODUCT_COMPONENT( Product.class, Product.class ),
	PRODUCT_IMAGE( Product.class, File.class ), 
	TEAM_MEMBER( Team.class, Person.class ),
	;

	public Class<?> sourceClass;
	public Class<?> targetClass;

	private RelationshipType( Class<?> sourceClass, Class<?> targetClass )
	{
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
	}
}
