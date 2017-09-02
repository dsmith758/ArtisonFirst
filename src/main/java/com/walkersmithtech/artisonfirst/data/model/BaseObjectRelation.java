package com.walkersmithtech.artisonfirst.data.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;

@JsonInclude( Include.NON_EMPTY )
public class BaseObjectRelation extends BaseRelation
{
	protected List<RoleData> collaborators;

	public List<RoleData> getCollaborators()
	{
		if ( collaborators == null )
		{
			collaborators = new ArrayList<>();
		}
		return collaborators;
	}

	public void setCollaborators( List<RoleData> collaborators )
	{
		this.collaborators = collaborators;
	}

	@JsonIgnore
	public void addCollaborator( RoleData collaborator )
	{
		getCollaborators();
		if ( collaborator != null )
		{
			collaborators.add( collaborator );
		}
	}

	@JsonIgnore
	public RoleData getCollaborator( String roleType )
	{
		if ( collaborators != null && collaborators.size() > 0 )
		{
			for ( RoleData collaborator : collaborators )
			{
				if ( collaborator.getRole().equals( roleType ) )
				{
					return collaborator;
				}
			}
		}
		return null;
	}
	
}
