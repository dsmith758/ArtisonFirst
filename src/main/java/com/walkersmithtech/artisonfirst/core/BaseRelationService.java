package com.walkersmithtech.artisonfirst.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationDataIndex;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.BaseObjectRelation;
import com.walkersmithtech.artisonfirst.data.repository.ObjectRelationDataIndexRepository;
import com.walkersmithtech.artisonfirst.data.repository.ObjectRelationDataRepository;
import com.walkersmithtech.artisonfirst.util.DateUtil;
import com.walkersmithtech.artisonfirst.util.JsonUtil;

public abstract class BaseRelationService<T extends BaseObjectRelation> extends BaseService<T, ObjectRelationData>
{

	@Autowired
	protected ObjectRelationDataRepository dataRepo;

	@Autowired
	protected ObjectRelationDataIndexRepository indexRepo;

	protected RelationshipType type;
	
	protected Class<T> modelClass;

	@Override
	public T createModel( T model )
	{
		model = saveRelationship( model );
		createIndex( model );
		return model;
	}

	@Override
	public T updateModel( T model )
	{
		model = saveRelationship( model );
		createIndex( model );
		return model;
	}

	private T saveRelationship( T relation )
	{
		if ( relation != null )
		{
			ObjectRelationData entity = null;
			List<ObjectRelationData> entities = getRelationsByCollaboratorsAndType( relation.getCollaborators(), type );
			if ( entities != null && entities.size() > 0 )
			{
				entity = entities.get( 0 );
			}
			if ( entity == null )
			{
				relation = createData( relation );
			}
			else
			{
				relation = updateData( relation );
			}
		}
		return relation;
	}

	@Override
	public boolean deleteModel( String uid )
	{
		return deleteRelationModel( uid );
	}

	@Override
	protected void saveIndexData( String uid, IndexType type, String data )
	{
		saveIndexData( uid, type.name, data );
	}

	protected void saveIndexData( String uid, String type, String data )
	{
		ObjectRelationDataIndex index = new ObjectRelationDataIndex();
		index.setUid( uid );
		index.setType( type );
		index.setData( data );
		indexRepo.save( index );
	}

	@Override
	protected void updateIndexData( String objectUid, IndexType type, String data )
	{
		ObjectRelationDataIndex index = indexRepo.findByUidAndType( objectUid, type.name );
		if ( index == null )
		{
			saveIndexData( objectUid, type, data );
		}
		index.setData( data );
		indexRepo.save( index );
	}

	@Override
	public boolean deleteIndex( String uid )
	{
		deleteRelationIndexData( uid );
		return true;
	}

	public List<T> getAllModels()
	{
		List<ObjectRelationData> entities = dataRepo.findByType( type.name() );
		return convertEntitiesToModels( entities );
	}

	@Override
	public T getModelByUid( String uid )
	{
		ObjectRelationData entity = dataRepo.findByUid( uid );
		if ( entity != null )
		{
			T model = convertEntityToModel( entity );
			return model;
		}
		return null;
	}

	@Override
	public List<T> getByFreeformSearch( String criteria )
	{
		return null;
	}

	public List<T> getRelationsBySourceAndType( String uid, RelationshipType type, RelationshipRole role )
	{

		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( uid, type, role );
		List<T> relations = new ArrayList<>();
		if ( entities != null && entities.size() > 0 )
		{
			for ( ObjectRelationData entity : entities )
			{
				relations.add( convertEntityToModel( entity ) );
			}
		}
		return relations;
	}

	public List<T> getRelationsByTargetAndType( String uid, RelationshipType type, RelationshipRole role )
	{
		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( uid, type, role );
		List<T> relations = new ArrayList<>();
		if ( entities != null && entities.size() > 0 )
		{
			for ( ObjectRelationData entity : entities )
			{
				relations.add( convertEntityToModel( entity ) );
			}
		}
		return relations;
	}

	public T getRelationsBySourceAndTargetAndType( List<String> objectUid, RelationshipType type )
	{
		ObjectRelationData entity = null;
		List<ObjectRelationData> entities = getRelationsByObjectUidsAndType( objectUid, type );
		if ( entities != null && entities.size() > 0 )
		{
			entity = entities.get( 0 );
		}
		if ( entity != null )
		{
			return convertEntityToModel( entity );
		}
		return null;
	}

	protected T createData( T relation )
	{
		if ( relation == null )
		{
			return null;
		}
		relation.setType( type.name() );
		ObjectRelationData entity = new ObjectRelationData();
		entity.setUid( DateUtil.generateUuid() );
		entity.setCreatedOn( DateUtil.getCurrentDate() );
		entity.setUpdatedOn( DateUtil.getCurrentDate() );
		entity.setType( relation.getType() );
		entity.setData( JsonUtil.createJsonFromModel( relation ) );
		entity = dataRepo.save( entity );
		relation.setId( entity.getId() );
		relation.setUid( entity.getUid() );
		relation = createRoles( relation );
		return relation;
	}

	protected T updateData( T relation )
	{
		if ( relation != null )
		{
			ObjectRelationData entity = dataRepo.findOne( relation.getId() );
			if ( entity != null )
			{
				entity.setUpdatedOn( DateUtil.getCurrentDate() );
				entity.setData( JsonUtil.createJsonFromModel( relation ) );
				entity = dataRepo.save( entity );
				relation = createRoles( relation );
			}
			else
			{
				relation = createData( relation );
			}
		}
		return relation;
	}

	public T createRoles( T relation )
	{
		roleRepo.deleteByObjectRelationUid( relation.getUid() );
		List<RoleData> roleData = new ArrayList<>();
		List<RoleData> collaborators = relation.getCollaborators();
		if ( collaborators != null && collaborators.size() > 0 )
		{
			for ( RoleData collaborator : collaborators )
			{
				collaborator.setId( null );
				collaborator.setUid( DateUtil.generateUuid() );
				collaborator.setObjectRelationUid( relation.getUid() );
				collaborator = roleRepo.save( collaborator );
				roleData.add( collaborator );
			}
		}
		relation.setCollaborators( roleData );
		return relation;
	}

	protected List<T> convertEntitiesToModels( List<ObjectRelationData> entities )
	{
		List<T> models = new ArrayList<T>();
		for ( ObjectRelationData entity : entities )
		{
			models.add( convertEntityToModel( entity ) );
		}
		return models;
	}
	
	@Override
	protected T convertEntityToModel( ObjectRelationData entity )
	{
		try
		{
			String json = entity.getData();
			T model = ( T ) JsonUtil.createModelFromJson( json, modelClass );
			model.setUid( entity.getUid() );
			model.setId( entity.getId() );
			model = getRoles( model );
			return model;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}
	
	protected T getRoles( T relation )
	{
		if ( relation != null )
		{
			List<RoleData> collaborators = roleRepo.findByObjectRelationUid( relation.getUid() );
			relation.setCollaborators( collaborators );
			return relation;
		}
		return null;
	}

}
