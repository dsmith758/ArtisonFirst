package com.walkersmithtech.artisonfirst.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationDataIndex;

public interface ObjectRelationDataIndexRepository extends JpaRepository<ObjectRelationDataIndex, Integer>
{
	public List<ObjectRelationDataIndex> findByUid( String uid );

	public ObjectRelationDataIndex findByUidAndType( String uid, String type );

	@Modifying
	@Transactional
	@Query("delete from ObjectRelationDataIndex idx where idx.uid = ?1")
	public void deleteByUid( String objectUid );
	
}
