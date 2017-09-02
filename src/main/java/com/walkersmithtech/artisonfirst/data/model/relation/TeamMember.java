package com.walkersmithtech.artisonfirst.data.model.relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.ObjectType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.BaseObjectRelation;
import com.walkersmithtech.artisonfirst.data.model.object.Person;
import com.walkersmithtech.artisonfirst.data.model.object.Team;

@JsonInclude( Include.NON_EMPTY )
public class TeamMember extends BaseObjectRelation
{
	private int membershipRights;
	
	public TeamMember()
	{
		this.type = RelationshipType.TEAM_MEMBER.name();
	}

	public int getMembershipRights()
	{
		return membershipRights;
	}

	public void setMembershipRights( int membershipRights )
	{
		this.membershipRights = membershipRights;
	}
	
	public void addTeam( Team team )
	{
		RoleData role = new RoleData();
		role.setObject( team );
		role.setObjectUid( team.getUid() );
		role.setObjectType( ObjectType.TEAM.name() );
		role.setRole( RelationshipRole.TEAM.name() );
		addCollaborator( role );
	}
	
	public void addMember( Person person )
	{
		RoleData role = new RoleData();
		role.setObject( person );
		role.setObjectUid( person.getUid() );
		role.setObjectType( ObjectType.PERSON.name() );
		role.setRole( RelationshipRole.MEMBER.name() );
		addCollaborator( role );
	}
}
