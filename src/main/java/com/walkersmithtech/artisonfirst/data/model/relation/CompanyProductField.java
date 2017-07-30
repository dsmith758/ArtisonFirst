package com.walkersmithtech.artisonfirst.data.model.relation;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;

public class CompanyProductField extends BaseRelation
{
	
	public CompanyProductField()
	{
		this.role = RelationshipRole.COMPANY_PRODUCT_FIELDS.name();
	}

}
