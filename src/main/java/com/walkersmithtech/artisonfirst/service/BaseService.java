package com.walkersmithtech.artisonfirst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.data.entity.ObjectData;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.repository.ObjectDataIndexRepository;
import com.walkersmithtech.artisonfirst.data.repository.ObjectDataRepository;
import com.walkersmithtech.artisonfirst.data.repository.ObjectRelationDataIndexRepository;
import com.walkersmithtech.artisonfirst.data.repository.ObjectRelationDataRepository;

@Service
public class BaseService
{
	@Autowired
	protected ObjectDataRepository objectDataRepo;

	@Autowired
	protected ObjectDataIndexRepository objectIndexRepo;

	@Autowired
	protected ObjectRelationDataRepository relationDataRepo;

	@Autowired
	protected ObjectRelationDataIndexRepository relationIndexRepo;

	@Transactional
	public boolean deleteObjectModel( String objectUid )
	{
		ObjectData entity = objectDataRepo.findByUid( objectUid );
		if ( entity == null )
		{
			return false;
		}
		deleteObjectIndexData( objectUid );
		deleteRelationModelsByUid( objectUid );
		objectDataRepo.deleteByUid( objectUid );
		return true;
	}

	protected void deleteObjectModelByUid( String objectUid )
	{
		deleteObjectIndexData( objectUid );
		deleteRelationModelsByUid( objectUid );
		objectDataRepo.deleteByUid( objectUid );
	}

	protected void deleteObjectIndexData( String objectUid )
	{
		objectIndexRepo.deleteByUid( objectUid );
	}

	public boolean deleteRelationModel( Integer id )
	{
		ObjectRelationData entity = relationDataRepo.findOne( id );
		if ( entity == null )
		{
			return false;
		}
		return deleteRelationModel( entity.getUid() );
	}

	public boolean deleteRelationModel( String uid )
	{
		relationIndexRepo.deleteByUid( uid );
		relationDataRepo.deleteByUid( uid );
		return true;
	}

	public boolean deleteRelationModelsBySourceUid( String uid )
	{
		List<ObjectRelationData> relations = getRelationsBySourceUid( uid );
		if ( relations != null && relations.size() > 0 )
		{
			for ( ObjectRelationData relation : relations )
			{
				deleteRelationIndexByUid( relation.getUid() );
			}
		}

		relationDataRepo.deleteBySourceUid( uid );
		return true;
	}

	public boolean deleteRelationModelsBySourceUidAndRole( String uid, String role )
	{
		List<ObjectRelationData> relations = getRelationsBySourceUidAndRole( uid, role );
		if ( relations != null && relations.size() > 0 )
		{
			for ( ObjectRelationData relation : relations )
			{
				deleteRelationIndexByUid( relation.getUid() );
			}
		}

		relationDataRepo.deleteBySourceUidAndRole( uid, role );
		return true;
	}

	public boolean deleteRelationModelsByTargetUid( String uid )
	{
		List<ObjectRelationData> relations = getRelationsByTargetUid( uid );
		if ( relations != null && relations.size() > 0 )
		{
			for ( ObjectRelationData relation : relations )
			{
				deleteRelationIndexByUid( relation.getUid() );
			}
		}
		relationDataRepo.deleteByTargetUid( uid );
		return true;
	}

	public boolean deleteRelationModelsByTargetUidAndRole( String uid, String role )
	{
		List<ObjectRelationData> relations = getRelationsByTargetUidAndRole( uid, role );
		if ( relations != null && relations.size() > 0 )
		{
			for ( ObjectRelationData relation : relations )
			{
				deleteRelationIndexByUid( relation.getUid() );
			}
		}

		relationDataRepo.deleteByTargetUidAndRole( uid, role );
		return true;
	}

	public boolean deleteRelationModelsByUid( String uid )
	{
		List<ObjectRelationData> relations = getRelationsBySourceUid( uid );
		if ( relations != null && relations.size() > 0 )
		{
			for ( ObjectRelationData relation : relations )
			{
				deleteRelationIndexByUid( relation.getUid() );
			}
		}
		relations = getRelationsByTargetUid( uid );
		if ( relations != null && relations.size() > 0 )
		{
			for ( ObjectRelationData relation : relations )
			{
				deleteRelationIndexByUid( relation.getUid() );
			}
		}
		relationDataRepo.deleteBySourceUid( uid );
		relationDataRepo.deleteByTargetUid( uid );
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

	public List<ObjectRelationData> getRelationsBySourceUid( String uid )
	{
		List<ObjectRelationData> entities = relationDataRepo.findBySourceUid( uid );
		return entities;
	}

	public List<ObjectRelationData> getRelationsByTargetUid( String uid )
	{
		List<ObjectRelationData> entities = relationDataRepo.findByTargetUid( uid );
		return entities;
	}
	
	public List<ObjectRelationData> getRelationsBySourceUidAndRole( String uid, String role )
	{
		List<ObjectRelationData> entities = relationDataRepo.findBySourceUidAndRole( uid, role );
		return entities;
	}

	public List<ObjectRelationData> getRelationsByTargetUidAndRole( String uid, String role )
	{
		List<ObjectRelationData> entities = relationDataRepo.findByTargetUidAndRole( uid, role );
		return entities;
	}

}
