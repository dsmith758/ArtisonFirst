package com.walkersmithtech.artisonfirst.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.data.entity.ObjectData;

public interface ObjectDataRepository extends JpaRepository< ObjectData, String >
{
	public List<ObjectData> findByTypeAndDataAndStatus( String type, String data, Integer status );
	
	public ObjectData findByUid( String uid );

	public List<ObjectData> findByType( String type );
	
	@Modifying
	@Transactional
	@Query("delete from ObjectData idx where idx.uid = ?1")
	public void deleteByUid( String uid );

}
