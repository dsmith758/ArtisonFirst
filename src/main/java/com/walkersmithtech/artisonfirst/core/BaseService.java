package com.walkersmithtech.artisonfirst.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.dao.ObjectRelationDao;
import com.walkersmithtech.artisonfirst.data.entity.ObjectData;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.repository.ObjectDataIndexRepository;
import com.walkersmithtech.artisonfirst.data.repository.ObjectDataRepository;
import com.walkersmithtech.artisonfirst.data.repository.ObjectRelationDataIndexRepository;
import com.walkersmithtech.artisonfirst.data.repository.ObjectRelationDataRepository;
import com.walkersmithtech.artisonfirst.data.repository.RoleDataRepository;

public abstract class BaseService<T, O>
{
	
	@Autowired
	protected ObjectDataRepository objectDataRepo;

	@Autowired
	protected ObjectDataIndexRepository objectIndexRepo;

	@Autowired
	protected ObjectRelationDataRepository relationDataRepo;

	@Autowired
	protected ObjectRelationDataIndexRepository relationIndexRepo;
	
	@Autowired
	protected ObjectRelationDao dataDao;
	
	@Autowired
	protected RoleDataRepository roleRepo;
	
	public abstract T createModel( T model );
	
	public abstract T updateModel( T model );
	
	protected abstract void createIndex( T model );
	
	public abstract boolean deleteModel( String objectUid );
	
	public abstract boolean deleteIndex( String objectUid );
	
	public abstract T getModelByUid( String uid );
	
	protected abstract T createData( T model );
	
	protected abstract T updateData( T model );
	
	protected abstract void saveIndexData( String uid, IndexType type, String data );
	
	protected abstract void updateIndexData( String objectUid, IndexType type, String data );
	
	public abstract List<T> getByFreeformSearch( String criteria );

	protected abstract List<T> convertEntitiesToModels( List<O> entities );

	protected abstract  T convertEntityToModel( O entity );
	
	protected abstract void validate( T model ) throws ServiceException;
	
	@Transactional
	public boolean deleteObjectModel( String objectUid )
	{
		ObjectData entity = objectDataRepo.findByUid( objectUid );
		if ( entity == null )
		{
			return false;
		}
		deleteObjectIndexData( objectUid );
		deleteRelationsByObjectUid( objectUid );
		return true;
	}

	protected void deleteObjectIndexData( String objectUid )
	{
		objectIndexRepo.deleteByUid( objectUid );
	}

	public boolean deleteRelationsByObjectUid( String objectUid )
	{
		List<ObjectRelationData> relations = getRelationsByObjectUid( objectUid );
		deleteRelations( relations );
		return true;
	}

	public boolean deleteRelationsByObjectUidAndType( String uid, RelationshipType type )
	{
		List<ObjectRelationData> relations = getRelationsByObjectUidAndType( uid, type );
		deleteRelations( relations );
		return true;

	}

	private void deleteRelations( List<ObjectRelationData> relations )
	{
		if ( relations != null && relations.size() > 0 )
		{
			for ( ObjectRelationData relation : relations )
			{
				deleteRelationModel( relation.getUid() );
			}
		}
	}

	public boolean deleteRelationModel( String uid )
	{
		relationIndexRepo.deleteByUid( uid );
		roleRepo.deleteByObjectRelationUid( uid );
		relationDataRepo.deleteByUid( uid );
		return true;
	}

	public void deleteRelationIndexData( String objectUid )
	{
		deleteRelationIndexByUid( objectUid );
	}

	public boolean deleteRelationIndexByUid( String uid )
	{
		relationIndexRepo.deleteByUid( uid );
		return true;
	}
	
	public List<ObjectRelationData> getRelationsByRoles( List<RoleData> collaborators )
	{
		List<ObjectRelationData> entities = dataDao.findObjectRelationByCollaborators( collaborators );
		return entities;
	}

	public List<ObjectRelationData> getRelationsByObjectUid( String objectUid )
	{
		List<String> objectUids = new ArrayList<>();
		objectUids.add( objectUid );
		return getRelationsByObjectUid( objectUids );
	}
	
	public List<ObjectRelationData> getRelationsByObjectUid( List<String> objectUids )
	{
		List<ObjectRelationData> entities = dataDao.findObjectRelationByObjectUids( objectUids );
		return entities;
	}

	public List<ObjectRelationData> getRelationsByRoleDataAndType( List<RoleData> collaborators, RelationshipType type )
	{
		List<ObjectRelationData> entities = dataDao.findObjectRelationByCollaboratorsAndType( collaborators, type );
		return entities;
	}

	public List<ObjectRelationData> getRelationsByObjectUidsAndType( List<String> objectUids, RelationshipType type )
	{
		List<ObjectRelationData> entities = dataDao.findObjectRelationByObjectUidAndType( objectUids, type );
		return entities;
	}

	public List<ObjectRelationData> getRelationsByObjectUidAndType( String objectUid, RelationshipType type )
	{
		List<String> objectUids = new ArrayList<>();
		objectUids.add( objectUid );
		List<ObjectRelationData> entities = dataDao.findObjectRelationByObjectUidAndType( objectUids, type );
		return entities;
	}
	
	public List<ObjectRelationData> getRelationsByObjectUidAndRole( String objectUid, RelationshipType type, RelationshipRole role )
	{
		List<ObjectRelationData> collaborators = dataDao.findObjectRelationByCollaborators( objectUid, type, role );
		return collaborators;
	}
	
}
