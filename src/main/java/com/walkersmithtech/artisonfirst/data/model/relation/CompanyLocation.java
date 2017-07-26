package com.walkersmithtech.artisonfirst.data.model.relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;

@JsonInclude( Include.NON_EMPTY )
public class CompanyLocation extends BaseObjectRelation
{

	public CompanyLocation()
	{
		this.role = RelationshipRole.COMPANY_LOCATION.name();
	}
}
