package com.walkersmithtech.artisonfirst.data.model.relation;

import java.util.List;

import com.walkersmithtech.artisonfirst.constant.ObjectType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.BaseObjectRelation;
import com.walkersmithtech.artisonfirst.data.model.fragment.ProductFieldDefinition;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Product;

public class CompanyProduct extends BaseObjectRelation
{
	private List<ProductFieldDefinition> fields;

	public CompanyProduct()
	{
		this.type = RelationshipType.COMPANY_PRODUCT.name();
	}

	public List<ProductFieldDefinition> getFields()
	{
		return fields;
	}

	public void setFields( List<ProductFieldDefinition> fields )
	{
		this.fields = fields;
	}
	
	public void addOwner( Company owner )
	{
		RoleData role = new RoleData();
		role.setObject( owner );
		role.setObjectUid( owner.getUid() );
		role.setObjectType( ObjectType.COMPANY.name() );
		role.setRole( RelationshipRole.OWNER.name() );
		addCollaborator( role );
	}
	
	public RoleData retrieveOwner()
	{
		return getCollaborator( RelationshipRole.OWNER.name() );
	}
	
	public void addProduct( Product product )
	{
		RoleData role = new RoleData();
		role.setObject( product );
		role.setObjectUid( product.getUid() );
		role.setObjectType( ObjectType.PRODUCT.name() );
		role.setRole( RelationshipRole.PRODUCT.name() );
		addCollaborator( role );
	}
	
	public RoleData retrieveProduct()
	{
		return getCollaborator( RelationshipRole.PRODUCT.name() );
	}
	

}
