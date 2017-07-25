package com.walkersmithtech.artisonfirst.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.data.entity.RelationDataIndex;

public interface RelationDataIndexRepository extends JpaRepository<RelationDataIndex, Integer>
{
	public List<RelationDataIndex> findByUid( String uid );

	public RelationDataIndex findByUidAndType( String uid, String type );

	@Modifying
	@Transactional
	@Query("delete from RelationDataIndex idx where idx.uid = ?1")
	public void deleteByUid( String objectUid );
	
}
