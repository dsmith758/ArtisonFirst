package com.walkersmithtech.artisonfirst.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.data.dao.ObjectDataDao;
import com.walkersmithtech.artisonfirst.data.entity.ObjectData;
import com.walkersmithtech.artisonfirst.data.entity.ObjectDataIndex;
import com.walkersmithtech.artisonfirst.data.model.BaseObject;
import com.walkersmithtech.artisonfirst.data.repository.ObjectDataIndexRepository;
import com.walkersmithtech.artisonfirst.data.repository.ObjectDataRepository;
import com.walkersmithtech.artisonfirst.util.DateUtil;
import com.walkersmithtech.artisonfirst.util.JsonUtil;

public abstract class BaseModelService<T extends BaseObject>
{
	@Autowired
	protected ObjectDataRepository dataRepo;

	@Autowired
	protected ObjectDataIndexRepository indexRepo;

	@Autowired
	protected BaseService baseService;

	@Autowired
	protected ObjectDataDao dao;

	protected DataType dataType;

	protected Class<T> modelClass;

	public abstract T createModel( T model );

	public abstract T updateModel( T model );

	@Transactional
	public boolean deleteModel( String objectUid )
	{
		return baseService.deleteObjectModel( objectUid );
	}

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

	protected T createData( T model )
	{
		model.setUid( DateUtil.generateUuid() );
		ObjectData entity = new ObjectData();
		entity.setUid( model.getUid());
		entity.setCreatedOn( DateUtil.getCurrentDate() );
		entity.setUpdatedOn( DateUtil.getCurrentDate() );
		entity.setStatus( 1 );
		entity.setType( model.getType() );
		entity.setData( JsonUtil.createJsonFromModel( model ) );
		entity = dataRepo.save( entity );
		model.setUid( entity.getUid() );
		return model;
	}

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

	protected List<T> getBySourceRelation( String sourceUid )
	{
		List<ObjectData> entities = dao.findObjectBySourceRelation( sourceUid );
		return convertEntitiesToModels( entities );
	}

	protected List<T> getByTargetRelation( String targetUid )
	{
		List<ObjectData> entities = dao.findObjectByTargetRelation( targetUid );
		return convertEntitiesToModels( entities );
	}

	public List<T> getByFreeformSearch( String criteria )
	{
		List<ObjectData> entities = dataRepo.findByTypeAndDataAndStatus( dataType.type, criteria, 1 );
		return convertEntitiesToModels( entities );
	}

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
			return (T) model;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}

}
