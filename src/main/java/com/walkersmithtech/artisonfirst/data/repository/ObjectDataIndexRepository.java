package com.walkersmithtech.artisonfirst.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.data.entity.ObjectDataIndex;

public interface ObjectDataIndexRepository extends JpaRepository<ObjectDataIndex, Integer>
{
	public List<ObjectDataIndex> findByType( String type );

	public ObjectDataIndex findByUidAndType( String uid, String type );

	@Modifying
	@Transactional
	@Query( "delete from ObjectDataIndex idx where idx.uid = ?1" )
	public void deleteByUid( String uid );
}
