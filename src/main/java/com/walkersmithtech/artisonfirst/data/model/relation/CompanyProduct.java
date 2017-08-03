package com.walkersmithtech.artisonfirst.data.model.relation;

import java.util.List;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.BaseObjectRelation;
import com.walkersmithtech.artisonfirst.data.model.fragment.ProductFieldDefinition;

public class CompanyProduct extends BaseObjectRelation
{
	private List<ProductFieldDefinition> fields;
	private String companyRole;

	public CompanyProduct()
	{
		this.role = RelationshipRole.COMPANY_PRODUCT.name();
	}

	public List<ProductFieldDefinition> getFields()
	{
		return fields;
	}

	public void setFields( List<ProductFieldDefinition> fields )
	{
		this.fields = fields;
	}

	public String getCompanyRole()
	{
		return companyRole;
	}

	public void setCompanyRole( String companyRole )
	{
		this.companyRole = companyRole;
	}
}
