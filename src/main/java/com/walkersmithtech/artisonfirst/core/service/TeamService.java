package com.walkersmithtech.artisonfirst.component.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.component.BaseObjectService;
import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.data.model.object.Team;

@Service
public class TeamService  extends BaseObjectService<Team>
{
	
	public TeamService()
	{
		dataType = DataType.TEAM;
		modelClass = Team.class;
	}

	@Override
	public Team createModel( Team model )
	{
		if ( model != null )
		{
			model = createData( model );
			createIndex( model );
		}
		return model;
	}

	@Override
	public Team updateModel( Team model )
	{
		if ( model != null )
		{
			model = updateData( model );
			createIndex( model );
		}
		return model;
	}
	
	private void createIndex( Team model )
	{
		saveIndexData( model.getUid(), IndexType.TEAM_NAME, model.getName() );		
	}
	
	public List<Team> getTeamByName( String name )
	{
		List<Team> models = getByTypeAndData( IndexType.TEAM_NAME, name );
		return models;
	}
}
