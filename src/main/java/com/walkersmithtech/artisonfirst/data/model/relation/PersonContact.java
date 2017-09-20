package com.walkersmithtech.artisonfirst.data.model.relation;

import com.walkersmithtech.artisonfirst.constant.ObjectType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.BaseObjectRelation;
import com.walkersmithtech.artisonfirst.data.model.object.Person;

public class PersonContact extends BaseObjectRelation
{
	public PersonContact()
	{
		setRelationship( RelationshipType.COMPANY_LOCATION );
	}
	
	public void addPerson( Person person )
	{
		RoleData role = new RoleData();
		role.setObject( person );
		role.setObjectUid( person.getUid() );
		role.setObjectType( ObjectType.PERSON.name() );
		role.setRole( RelationshipRole.PERSON.name() );
		addCollaborator( role );
	}
	
	public RoleData retrievePerson()
	{
		return getCollaborator( RelationshipRole.PERSON.name() );
	}
	
	public void addContact( Person contact )
	{
		RoleData role = new RoleData();
		role.setObject( contact );
		role.setObjectUid( contact.getUid() );
		role.setObjectType( ObjectType.PERSON.name() );
		role.setRole( RelationshipRole.CONTACT.name() );
		addCollaborator( role );
	}
	
	public RoleData retrieveContact()
	{
		return getCollaborator( RelationshipRole.CONTACT.name() );
	}
}
