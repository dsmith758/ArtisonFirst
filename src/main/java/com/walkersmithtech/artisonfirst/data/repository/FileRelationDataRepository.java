package com.walkersmithtech.artisonfirst.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.data.entity.FileRelationData;

public interface FileRelationDataRepository extends JpaRepository<FileRelationData, Integer>
{
	@Modifying
	@Transactional
	@Query("delete from FileRelationData idx where idx.uid = ?1")
	public void deleteByUid( String uid );
	
	@Modifying
	@Transactional
	@Query("delete from FileRelationData idx where idx.objectUid = ?1")
	public void deleteByObjectUid( String uid );
	
	@Modifying
	@Transactional
	@Query("delete from FileRelationData idx where idx.objectUid = ?1 and idx.role = ?2")
	public void deleteByObjectUidAndRole( String uid, String role );
	
	@Modifying
	@Transactional
	@Query("delete from FileRelationData idx where idx.fileUid = ?1")
	public void deleteByFileUid( String uid );
	
	@Modifying
	@Transactional
	@Query("delete from FileRelationData idx where idx.fileUid = ?1 and idx.role = ?2")
	public void deleteByFileUidAndRole( String uid, String role );
	
	@Modifying
	@Transactional
	@Query("delete from FileRelationData idx where idx.objectUid = ?1 and idx.fileUid = ?2")
	public void deleteByObjectUidAndFileUid( String objectUid, String fileUid );
}
