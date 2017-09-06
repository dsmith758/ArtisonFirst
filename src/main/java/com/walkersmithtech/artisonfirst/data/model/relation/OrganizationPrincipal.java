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
public class OrganizationPrincipal extends BaseObjectRelation
{
	private String authorization;
	private boolean isDefault = false;

	public OrganizationPrincipal()
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

	public RoleData retrieveOrganization()
	{
		return getCollaborator( RelationshipRole.ORGANIZATION.name() );
	}

	public void addPrincipal( Person principal )
	{
		RoleData role = new RoleData();
		role.setObject( principal );
		role.setObjectUid( principal.getUid() );
		role.setObjectType( ObjectType.PERSON.name() );
		role.setRole( RelationshipRole.PRINCIPAL.name() );
		addCollaborator( role );
	}

	public RoleData retrievePrincipal()
	{
		return getCollaborator( RelationshipRole.PRINCIPAL.name() );
	}

	public String getAuthorization()
	{
		return authorization;
	}

	public void setAuthorization( String authorization )
	{
		this.authorization = authorization;
	}

	public boolean getIsDefault()
	{
		return isDefault;
	}

	public void setIsDefault( boolean isDefault )
	{
		this.isDefault = isDefault;
	}

}
