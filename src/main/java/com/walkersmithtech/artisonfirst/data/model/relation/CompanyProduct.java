package com.walkersmithtech.artisonfirst.data.model.relation;

import java.util.List;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.fragment.ProductDefinedFields;

public class CompanyProduct extends BaseRelation
{
	private String productId;
	private String productNmae;
	private List<ProductDefinedFields> definedFields;

	public CompanyProduct()
	{
		this.role = RelationshipRole.COMPANY_PRODUCT.name();
	}
}
