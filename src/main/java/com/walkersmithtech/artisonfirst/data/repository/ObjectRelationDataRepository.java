package com.walkersmithtech.artisonfirst.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;

public interface ObjectRelationDataRepository extends JpaRepository< ObjectRelationData, Integer >
{
	public List<ObjectRelationData> findByUid( String uid );

	public List<ObjectRelationData> findByRole( String role );
	
	public List<ObjectRelationData> findByRoleAndData( String role, String data );

	public List<ObjectRelationData> findBySourceUid( String uid );

	public List<ObjectRelationData> findByTargetUid( String uid );
	
	public List<ObjectRelationData> findBySourceUidAndRole( String uid, String role );

	public List<ObjectRelationData> findByTargetUidAndRole( String uid, String role );
	
	public ObjectRelationData findBySourceUidAndTargetUid( String sourceUid, String targetUid );
	
	public ObjectRelationData findBySourceUidAndTargetUidAndRole( String sourceUid, String targetUid, String role );

	@Modifying
	@Transactional
	@Query("delete from ObjectRelationData idx where idx.uid = ?1")
	public void deleteByUid( String uid );
	
	@Modifying
	@Transactional
	@Query("delete from ObjectRelationData idx where idx.sourceUid = ?1")
	public void deleteBySourceUid( String uid );
	
	@Modifying
	@Transactional
	@Query("delete from ObjectRelationData idx where idx.sourceUid = ?1 and idx.role = ?2")
	public void deleteBySourceUidAndRole( String uid, String role );
	
	@Modifying
	@Transactional
	@Query("delete from ObjectRelationData idx where idx.targetUid = ?1")
	public void deleteByTargetUid( String uid );
	
	@Modifying
	@Transactional
	@Query("delete from ObjectRelationData idx where idx.targetUid = ?1 and idx.role = ?2")
	public void deleteByTargetUidAndRole( String uid, String role );
	
	@Modifying
	@Transactional
	@Query("delete from ObjectRelationData idx where idx.sourceUid = ?1 and idx.targetUid = ?2")
	public void deleteBySourceUidAndTargetUid( String sourceUid, String targetUid );
}
