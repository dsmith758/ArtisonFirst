package com.walkersmithtech.artisonfirst.data.model.relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;

@JsonInclude( Include.NON_EMPTY )
public class TeamMember extends BaseRelation
{
	private int membershipRights;
	
	public TeamMember()
	{
		this.role = RelationshipRole.TEAM_MEMBER;
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
