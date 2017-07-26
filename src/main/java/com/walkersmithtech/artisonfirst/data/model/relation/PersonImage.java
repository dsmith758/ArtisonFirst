package com.walkersmithtech.artisonfirst.data.model.relation;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;

public class PersonImage extends BaseFileRelation
{
	public PersonImage()
	{
		this.role = RelationshipRole.PERSON_IMAGE.name();
	}
}
