package com.walkersmithtech.artisonfirst.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.data.entity.FileData;
import com.walkersmithtech.artisonfirst.data.entity.FileRelationData;
import com.walkersmithtech.artisonfirst.data.model.dto.FileDto;
import com.walkersmithtech.artisonfirst.data.repository.FileDataRepository;
import com.walkersmithtech.artisonfirst.data.repository.FileRelationDataRepository;
import com.walkersmithtech.artisonfirst.service.BaseService;
import com.walkersmithtech.artisonfirst.service.ServiceException;
import com.walkersmithtech.artisonfirst.util.DateUtil;
import com.walkersmithtech.artisonfirst.util.JsonUtil;

@Service
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
		dataRepo.deleteByUid( uid );
		relationRepo.deleteByFileUid( uid );
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
	
	public void deleteFileRelationByObjectAndRole( String objectUid, String role )
	{
		relationRepo.deleteByObjectUidAndRole( objectUid, role );
	}
	
	public void deleteFileRelationByObject( String objectUid )
	{
		relationRepo.deleteByObjectUid( objectUid );
	}
	
	public FileDto getFileMetadata( String uid ) throws ServiceException
	{
		FileData file = dataRepo.findByUid( uid );
		if ( file != null )
		{
			try
			{
				FileDto metadata = new FileDto();
				metadata = ( FileDto ) JsonUtil.createModelFromJson( file.getData(), FileDto.class );
				return metadata;
			}
			catch ( Exception e )
			{
				ServiceException ex = ErrorCode.SYSTEM_ERROR.exception;
				ex.initCause( e );
				throw ex;
			}
		}
		throw ErrorCode.FILE_MISSING.exception;		
	}
	
	public byte[] getFileData( String uid ) throws ServiceException
	{
		FileData file = dataRepo.findByUid( uid );
		if ( file != null )
		{
			return file.getFile();
		}
		throw ErrorCode.FILE_MISSING.exception;
	}


}
