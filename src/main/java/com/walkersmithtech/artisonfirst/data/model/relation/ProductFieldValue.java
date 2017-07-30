package com.walkersmithtech.artisonfirst.data.model.relation;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;

public class ProductFieldValue extends BaseRelation
{
	public ProductFieldValue()
	{
		this.role = RelationshipRole.PRODUCT_FIELD_VALUE.name();
	}
}
