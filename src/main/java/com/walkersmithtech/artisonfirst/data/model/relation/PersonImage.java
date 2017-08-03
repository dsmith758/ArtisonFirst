package com.walkersmithtech.artisonfirst.data.model.relation;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.BaseFileRelation;

public class PersonImage extends BaseFileRelation
{
	public PersonImage()
	{
		this.role = RelationshipRole.PERSON_IMAGE.name();
	}
}
