package com.walkersmithtech.artisonfirst.service.impl;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.Person;
import com.walkersmithtech.artisonfirst.data.model.Team;
import com.walkersmithtech.artisonfirst.data.model.relation.TeamMember;
import com.walkersmithtech.artisonfirst.service.BaseRelationService;

@Service
public class TeamMemberServiceImpl extends BaseRelationService<TeamMember>
{
	public TeamMemberServiceImpl()
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
