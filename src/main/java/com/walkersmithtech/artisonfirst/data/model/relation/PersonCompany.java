package com.walkersmithtech.artisonfirst.data.model.relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.ObjectType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.BaseObjectRelation;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Person;

@JsonInclude( Include.NON_EMPTY )
public class PersonCompany extends BaseObjectRelation
{
	public PersonCompany()
	{
		this.type = RelationshipType.PERSON_COMPANY.name();
	}
	
	public void addOrganization( Company organization )
	{
		RoleData role = new RoleData();
		role.setObject( organization );
		role.setObjectUid( organization.getUid() );
		role.setObjectType( ObjectType.COMPANY.name() );
		role.setRole( RelationshipRole.ORGANIZATION.name() );
		addCollaborator( role );
	}
	
	public void addPrinciple( Person principle )
	{
		RoleData role = new RoleData();
		role.setObject( principle );
		role.setObjectUid( principle.getUid() );
		role.setObjectType( ObjectType.PERSON.name() );
		role.setRole( RelationshipRole.PRINCIPLE.name() );
		addCollaborator( role );
	}
}
