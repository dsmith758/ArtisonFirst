package com.walkersmithtech.artisonfirst.data.model.relation;

import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.model.BaseFileRelation;

public class PersonImage extends BaseFileRelation
{
	public PersonImage()
	{
		this.type = RelationshipType.PERSON_IMAGE.name();
	}
}
