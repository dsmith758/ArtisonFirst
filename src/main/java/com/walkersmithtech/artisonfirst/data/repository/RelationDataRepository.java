package com.walkersmithtech.artisonfirst.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.data.entity.RelationData;

public interface RelationDataRepository extends JpaRepository< RelationData, Integer >
{
	public List<RelationData> findByUid( String uid );

	public List<RelationData> findByRole( String role );
	
	public List<RelationData> findByRoleAndData( String role, String data );

	public List<RelationData> findBySourceUid( String uid );

	public List<RelationData> findByTargetUid( String uid );
	
	public List<RelationData> findBySourceUidAndRole( String uid, String role );

	public List<RelationData> findByTargetUidAndRole( String uid, String role );
	
	public RelationData findBySourceUidAndTargetUid( String sourceUid, String targetUid );
	
	public RelationData findBySourceUidAndTargetUidAndRole( String sourceUid, String targetUid, String role );

	@Modifying
	@Transactional
	@Query("delete from RelationData idx where idx.uid = ?1")
	public void deleteByUid( String uid );
	
	@Modifying
	@Transactional
	@Query("delete from RelationData idx where idx.sourceUid = ?1")
	public void deleteBySourceUid( String uid );
	
	@Modifying
	@Transactional
	@Query("delete from RelationData idx where idx.sourceUid = ?1 and idx.role = ?2")
	public void deleteBySourceUidAndRole( String uid, String role );
	
	@Modifying
	@Transactional
	@Query("delete from RelationData idx where idx.targetUid = ?1")
	public void deleteByTargetUid( String uid );
	
	@Modifying
	@Transactional
	@Query("delete from RelationData idx where idx.targetUid = ?1 and idx.role = ?2")
	public void deleteByTargetUidAndRole( String uid, String role );
	
	@Modifying
	@Transactional
	@Query("delete from RelationData idx where idx.sourceUid = ?1 and idx.targetUid = ?2")
	public void deleteBySourceUidAndTargetUid( String sourceUid, String targetUid );
}
