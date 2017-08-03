package com.walkersmithtech.artisonfirst.data.model.relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.BaseObjectRelation;

@JsonInclude( Include.NON_EMPTY )
public class PersonLocation extends BaseObjectRelation
{
	public PersonLocation()
	{
		this.role = RelationshipRole.PERSON_LOCATION.name();
	}
}
