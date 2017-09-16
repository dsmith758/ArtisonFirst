package com.walkersmithtech.artisonfirst.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.dao.ObjectDataDao;
import com.walkersmithtech.artisonfirst.data.entity.ObjectData;
import com.walkersmithtech.artisonfirst.data.entity.ObjectDataIndex;
import com.walkersmithtech.artisonfirst.data.model.BaseObject;
import com.walkersmithtech.artisonfirst.data.repository.ObjectDataIndexRepository;
import com.walkersmithtech.artisonfirst.data.repository.ObjectDataRepository;
import com.walkersmithtech.artisonfirst.util.DateUtil;
import com.walkersmithtech.artisonfirst.util.JsonUtil;

public abstract class BaseObjectService<T extends BaseObject> extends BaseService<T, ObjectData>
{
	@Autowired
	protected ObjectDataRepository dataRepo;

	@Autowired
	protected ObjectDataIndexRepository indexRepo;

	@Autowired
	protected ObjectDataDao dao;
	
	protected DataType dataType;

	protected Class<T> modelClass;
	
	@Override
	public T createModel( T model )
	{
		if ( model != null )
		{
			model = createData( model );
			createIndex( model );
		}
		return model;
	}

	@Override
	public T updateModel( T model )
	{
		if ( model != null )
		{
			model = updateData( model );
			createIndex( model );
		}
		return model;
	}

	@Override
	@Transactional
	public boolean deleteModel( String objectUid )
	{
		return deleteObjectModel( objectUid );
	}

	@Override
	public boolean deleteIndex( String objectUid )
	{
		deleteObjectIndexData( objectUid );
		return true;
	}

	@Override
	public T getModelByUid( String uid )
	{
		ObjectData entity = dataRepo.findByUid( uid );
		if ( entity != null )
		{
			T model = convertEntityToModel( entity );
			return model;
		}
		return null;
	}
	
	public List<T> getModelsByIndexAndRoles( String sourceObjectUid, RelationshipRole sourceRole, RelationshipRole targetRole, IndexType type, String data )
	{
		List<ObjectData> entities = dao.findObjectByIndexAndRoles( sourceObjectUid, sourceRole.name(), targetRole.name(), type.name(), data );
		return convertEntitiesToModels( entities );
		
	}

	public List<T> getModelsByFreeformSearch( String criteria )
	{
		List<ObjectData> entities = dataRepo.findByTypeAndDataAndStatus( dataType.type, criteria, 1 );
		return convertEntitiesToModels( entities );
	}

	public List<T> getAllModels()
	{
		List<ObjectData> entities = dataRepo.findByType( dataType.type );
		return convertEntitiesToModels( entities );
	}

	@Override
	protected T createData( T model )
	{
		model.setUid( DateUtil.generateUuid() );
		ObjectData entity = new ObjectData();
		entity.setUid( model.getUid() );
		entity.setCreatedOn( DateUtil.getCurrentDate() );
		entity.setUpdatedOn( DateUtil.getCurrentDate() );
		entity.setStatus( 1 );
		entity.setType( model.getType() );
		entity.setData( JsonUtil.createJsonFromModel( model ) );
		entity = dataRepo.save( entity );
		model.setUid( entity.getUid() );
		return model;
	}

	@Override
	protected T updateData( T model )
	{
		ObjectData entity = dataRepo.findByUid( model.getUid() );
		if ( entity != null )
		{
			entity.setUpdatedOn( DateUtil.getCurrentDate() );
			entity.setData( JsonUtil.createJsonFromModel( model ) );
			entity = dataRepo.save( entity );
		}
		else
		{
			model = createData( model );
		}
		return model;
	}

	@Override
	protected void saveIndexData( String uid, IndexType type, String data )
	{
		if ( type != null && data != null && !data.isEmpty() )
		{
			ObjectDataIndex index = new ObjectDataIndex();
			index.setUid( uid );
			index.setType( type.name );
			index.setData( data );
			indexRepo.save( index );
		}
	}

	@Override
	protected void updateIndexData( String objectUid, IndexType type, String data )
	{
		ObjectDataIndex index = indexRepo.findByUidAndType( objectUid, type.name );
		if ( index == null )
		{
			saveIndexData( objectUid, type, data );
		}
		index.setData( data );
		indexRepo.save( index );
	}

	protected List<T> getByTypeAndData( IndexType type, String data )
	{
		List<ObjectData> entities = dao.findObjectByIndex( type.name, data );
		return convertEntitiesToModels( entities );
	}

	@Override
	public List<T> getByFreeformSearch( String criteria )
	{
		List<ObjectData> entities = dataRepo.findByTypeAndDataAndStatus( dataType.type, criteria, 1 );
		return convertEntitiesToModels( entities );
	}

	@Override
	protected List<T> convertEntitiesToModels( List<ObjectData> entities )
	{
		List<T> models = new ArrayList<T>();
		T model;
		for ( ObjectData entity : entities )
		{
			model = convertEntityToModel( entity );
			models.add( model );
		}
		return models;
	}

	@SuppressWarnings( "unchecked" )
	protected T convertEntityToModel( ObjectData entity )
	{
		try
		{
			String json = entity.getData();
			Object model = JsonUtil.createModelFromJson( json, modelClass );
			return ( T ) model;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}

}
