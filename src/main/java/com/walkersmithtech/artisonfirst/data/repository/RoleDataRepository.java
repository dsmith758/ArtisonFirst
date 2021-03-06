package com.walkersmithtech.artisonfirst.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.data.entity.RoleData;

public interface RoleDataRepository extends JpaRepository<RoleData, Integer>
{
	public List<RoleData> findByObjectUid( String objectUid );
	
	public List<RoleData> findByObjectRelationUid( String objectRelationUid );
	
	public List<RoleData> findByObjectUidAndRole( String uid, String role );
	
	@Modifying
	@Transactional
	@Query("delete from RoleData idx where idx.objectUid = ?1")
	public void deleteByObjectUid( String objectUid );
	
	@Modifying
	@Transactional
	@Query("delete from RoleData idx where idx.objectUid = ?1 and idx.role = ?2")
	public void deleteByObjectUidAndRole( String objectUid, String role );
		
	@Modifying
	@Transactional
	@Query("delete from RoleData idx where idx.objectRelationUid = ?1")
	public void deleteByObjectRelationUid( String objectUid );
		
	@Modifying
	@Transactional
	@Query("delete from RoleData idx where idx.uid = ?1")
	public void deleteByUid( String uid );
}
