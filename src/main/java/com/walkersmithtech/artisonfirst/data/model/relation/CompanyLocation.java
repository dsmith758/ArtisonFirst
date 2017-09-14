package com.walkersmithtech.artisonfirst.data.model.relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.ObjectType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.BaseObjectRelation;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Location;

@JsonInclude( Include.NON_EMPTY )
public class CompanyLocation extends BaseObjectRelation
{

	public CompanyLocation()
	{
		setRelationship( RelationshipType.COMPANY_LOCATION );
	}
	
	public void addResident( Company owner )
	{
		RoleData role = new RoleData();
		role.setObject( owner );
		role.setObjectUid( owner.getUid() );
		role.setObjectType( ObjectType.COMPANY.name() );
		role.setRole( RelationshipRole.RESIDENT.name() );
		addCollaborator( role );
	}
	
	public RoleData retrieveResident()
	{
		return getCollaborator( RelationshipRole.RESIDENT.name() );
	}
	
	public void addLocation( Location address )
	{
		RoleData role = new RoleData();
		role.setObject( address );
		role.setObjectUid( address.getUid() );
		role.setObjectType( ObjectType.LOCATION.name() );
		role.setRole( RelationshipRole.ADDRESS.name() );
		addCollaborator( role );
	}
	
	public RoleData retrieveAddress()
	{
		return getCollaborator( RelationshipRole.ADDRESS.name() );
	}
	

}
