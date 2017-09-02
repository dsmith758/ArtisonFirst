package com.walkersmithtech.artisonfirst.data.model.relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.ObjectType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.BaseObjectRelation;
import com.walkersmithtech.artisonfirst.data.model.object.Location;
import com.walkersmithtech.artisonfirst.data.model.object.Person;

@JsonInclude( Include.NON_EMPTY )
public class PersonLocation extends BaseObjectRelation
{
	public PersonLocation()
	{
		this.type = RelationshipType.PERSON_LOCATION.name();
	}
	
	public void addResident( Person person )
	{
		RoleData role = new RoleData();
		role.setObject( person );
		role.setObjectUid( person.getUid() );
		role.setObjectType( ObjectType.PERSON.name() );
		role.setRole( RelationshipRole.RESIDENT.name() );
		addCollaborator( role );
	}
	
	public void addResidence( Location residence )
	{
		RoleData role = new RoleData();
		role.setObject( residence );
		role.setObjectUid( residence.getUid() );
		role.setObjectType( ObjectType.LOCATION.name() );
		role.setRole( RelationshipRole.RESIDENCE.name() );
		addCollaborator( role );
	}
	
}
