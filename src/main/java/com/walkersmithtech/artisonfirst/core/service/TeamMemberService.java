package com.walkersmithtech.artisonfirst.core.service;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.BaseRelationService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.model.relation.TeamMember;

@Service
public class TeamMemberService extends BaseRelationService<TeamMember>
{
	public TeamMemberService()
	{
		type = RelationshipType.TEAM_MEMBER;
		modelClass = TeamMember.class;
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

	@Override
	protected void createIndex( TeamMember model )
	{

	}

	@Override
	protected void validate( TeamMember model ) throws ServiceException
	{

	}

}
