package com.walkersmithtech.artisonfirst.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.walkersmithtech.artisonfirst.data.entity.FileData;
import com.walkersmithtech.artisonfirst.data.entity.FileRelationData;
import com.walkersmithtech.artisonfirst.data.model.dto.FileDto;
import com.walkersmithtech.artisonfirst.data.repository.FileDataRepository;
import com.walkersmithtech.artisonfirst.data.repository.FileRelationDataRepository;
import com.walkersmithtech.artisonfirst.service.BaseService;
import com.walkersmithtech.artisonfirst.service.ServiceException;
import com.walkersmithtech.artisonfirst.util.DateUtil;
import com.walkersmithtech.artisonfirst.util.JsonUtil;

public class FileManagerSerivceImpl extends BaseService
{
	@Autowired
	private FileDataRepository dataRepo;
	
	@Autowired
	private FileRelationDataRepository relationRepo;

	public FileDto createFileRecord( byte[] file, FileDto auth ) throws ServiceException
	{
		FileData fileData = new FileData();
		fileData.setUid( DateUtil.generateUuid() );
		fileData.setData( JsonUtil.createJsonFromModel( auth ) );
		fileData.setDocType( auth.getDocType() );
		fileData.setCreatedOn( DateUtil.getCurrentDate() );
		fileData.setUpdatedOn( DateUtil.getCurrentDate() );
		fileData.setFile( file );
		dataRepo.save( fileData );
		auth.setUid( fileData.getUid() );
		return auth;
	}
	
	public void deleteFileRecord( String uid )
	{
		relationRepo.deleteByUid( uid );
	}
	
	public FileDto createFileRelation( String objectUid, String role, FileDto auth )
	{
		FileRelationData fileRelation = new FileRelationData();
		fileRelation.setUid( DateUtil.generateUuid() );
		fileRelation.setCreatedOn( DateUtil.getCurrentDate() );
		fileRelation.setUpdatedOn( DateUtil.getCurrentDate() );
		fileRelation.setRole( role );
		fileRelation.setFileUid( auth.getUid() );
		fileRelation.setObjectUid( objectUid );
		fileRelation.setData( JsonUtil.createJsonFromModel( auth ) );
		relationRepo.save( fileRelation );
		return auth;
	}
	
	public void deleteFileRelation( String objectUid, String role )
	{
		relationRepo.deleteByObjectUidAndRole( objectUid, role );
	}

}
