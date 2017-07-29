package com.walkersmithtech.artisonfirst.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.service.impl.FileManagerSerivceImpl;

public abstract class BaseFileService
{
	@Autowired
	private FileManagerSerivceImpl fileService;
	
	public void removeObjectImages( String objectUid )
	{
		fileService.deleteFileRelationByObject( objectUid );
	}

	public void removeImage( String fileUid )
	{
		fileService.deleteFileRecord( fileUid );
	}

	public void unsetObjectImage( String objectUid, RelationshipRole imageType ) throws ServiceException
	{
		fileService.deleteFileRelationByObjectAndRole( objectUid, imageType.name() );
	}
}
