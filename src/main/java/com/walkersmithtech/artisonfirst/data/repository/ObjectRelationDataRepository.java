package com.walkersmithtech.artisonfirst.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;

public interface ObjectRelationDataRepository extends JpaRepository< ObjectRelationData, Integer >
{
	public ObjectRelationData findByUid( String uid );

	public List<ObjectRelationData> findByType( String type );
	
	@Modifying
	@Transactional
	@Query("delete from ObjectRelationData idx where idx.uid = ?1")
	public void deleteByUid( String uid );
	
}
