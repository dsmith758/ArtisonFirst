package com.walkersmithtech.artisonfirst.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.entity.RelationData;
import com.walkersmithtech.artisonfirst.data.entity.RelationDataIndex;
import com.walkersmithtech.artisonfirst.data.model.relation.BaseRelation;
import com.walkersmithtech.artisonfirst.data.repository.RelationDataIndexRepository;
import com.walkersmithtech.artisonfirst.data.repository.RelationDataRepository;
import com.walkersmithtech.artisonfirst.util.DateUtil;
import com.walkersmithtech.artisonfirst.util.JsonUtil;

public abstract class BaseRelationService<T extends BaseRelation>
{

	@Autowired
	protected RelationDataRepository dataRepo;

	@Autowired
	protected RelationDataIndexRepository indexRepo;
	
	@Autowired
	private BaseService baseService;

	protected RelationshipRole roleType;
	protected Class<?> relationClass;
	protected Class<?> modelClass;
	protected Class<?> sourceClass;
	protected Class<?> targetClass;

	public abstract T createModel( T relation );

	public abstract T updateModel( T relation );

	public boolean deleteModel( Integer id )
	{
		return baseService.deleteRelationModel( id );
	}

	public boolean deleteModel( String uid )
	{
		return baseService.deleteRelationModel( uid );
	}

	public boolean deleteModelsByUid( String uid )
	{
		return baseService.deleteRelationModelsByUid( uid );
	}

	protected void saveIndexData( String uid, IndexType type, String data )
	{
		RelationDataIndex index = new RelationDataIndex();
		index.setUid( uid );
		index.setType( type.name );
		index.setData( data );
		indexRepo.save( index );
	}

	protected void updateIndexData( String objectUid, IndexType type, String data )
	{
		RelationDataIndex index = indexRepo.findByUidAndType( objectUid, type.name );
		if ( index == null )
		{
			saveIndexData( objectUid, type, data );
		}
		index.setData( data );
		indexRepo.save( index );
	}
	
	public List<T> getAllRelations()
	{
		List<RelationData> entities = dataRepo.findByRole( roleType.name() );
		return convertEntitiesToModels( entities );
	}

	public T getRelationById( Integer id )
	{
		try
		{
			RelationData entity = dataRepo.findOne( id );
			if ( entity != null )
			{
				return convertEntityToModel( entity );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}

	protected T createData( T relation )
	{
		if ( relation == null )
		{
			return null;
		}
		relation.setRole( roleType );
		RelationData entity = new RelationData();
		entity.setUid( DateUtil.generateUuid() );
		entity.setSourceUid( relation.getSourceUid() );
		entity.setTargetUid( relation.getTargetUid() );
		entity.setCreatedOn( DateUtil.getCurrentDate() );
		entity.setUpdatedOn( DateUtil.getCurrentDate() );
		entity.setRole( relation.getRole().name() );
		entity.setData( JsonUtil.createJsonFromModel( relation ));
		entity = dataRepo.save( entity );
		relation.setId( entity.getId() );
		relation.setUid( entity.getUid() );
		return relation;
	}

	protected T updateData( T relation )
	{
		if ( relation != null )
		{
			RelationData entity = dataRepo.findOne( relation.getId() );
			if ( entity != null )
			{
				entity.setSourceUid( relation.getSourceUid() );
				entity.setTargetUid( relation.getTargetUid() );
				entity.setUpdatedOn( DateUtil.getCurrentDate() );
				entity.setData( JsonUtil.createJsonFromModel( relation ) );
				entity = dataRepo.save( entity );
			}
			else
			{
				relation = createData( relation );
			}
		}
		return relation;
	}

	protected List<T> convertEntitiesToModels( List<RelationData> entities )
	{
		List<T> models = new ArrayList<T>();
		for ( RelationData entity : entities )
		{
			models.add( convertEntityToModel( entity ) );
		}
		return models;
	}
	
	@SuppressWarnings( "unchecked" )
	protected T convertEntityToModel( RelationData entity )
	{
		try
		{
			String json = entity.getData();
			Object model = JsonUtil.createModelFromJson( json, relationClass );
			return (T) model;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}
}