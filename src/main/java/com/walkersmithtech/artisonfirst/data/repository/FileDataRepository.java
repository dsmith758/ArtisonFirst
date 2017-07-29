package com.walkersmithtech.artisonfirst.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.walkersmithtech.artisonfirst.data.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Integer>
{
	
	public FileData findByUid( String uid );

	@Modifying
	@Transactional
	@Query("delete from FileData idx where idx.uid = ?1")
	public void deleteByUid( String uid );
}
