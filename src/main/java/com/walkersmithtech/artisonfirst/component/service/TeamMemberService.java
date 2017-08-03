package com.walkersmithtech.artisonfirst.component.service;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.component.BaseRelationService;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.object.Person;
import com.walkersmithtech.artisonfirst.data.model.object.Team;
import com.walkersmithtech.artisonfirst.data.model.relation.TeamMember;

@Service
public class TeamMemberService extends BaseRelationService<TeamMember>
{
	public TeamMemberService()
	{
		roleType = RelationshipRole.TEAM_MEMBER.name();
		relationClass = TeamMember.class;
		sourceClass = Team.class;
		targetClass = Person.class;
	}

	@Override
	public TeamMember createModel( TeamMember relation )
	{
		if ( relation != null )
		{
			// TODO: check for duplicates...
			createData( relation );
		}
		return relation;
	}

	@Override
	public TeamMember updateModel( TeamMember relation )
	{
		if ( relation != null )
		{
			// TODO: check for duplicates...
			updateData( relation );
		}
		return relation;
	}
}
