package com.walkersmithtech.artisonfirst.data.model.relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.BaseObjectRelation;

@JsonInclude( Include.NON_EMPTY )
public class TeamMember extends BaseObjectRelation
{
	private int membershipRights;
	
	public TeamMember()
	{
		this.role = RelationshipRole.TEAM_MEMBER.name();
	}

	public int getMembershipRights()
	{
		return membershipRights;
	}

	public void setMembershipRights( int membershipRights )
	{
		this.membershipRights = membershipRights;
	}
}
