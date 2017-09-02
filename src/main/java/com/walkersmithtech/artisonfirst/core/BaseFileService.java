package com.walkersmithtech.artisonfirst.core;

import org.springframework.beans.factory.annotation.Autowired;

import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.service.FileManagerSerivce;

public abstract class BaseFileService
{
	@Autowired
	private FileManagerSerivce fileService;
	
	public void removeObjectImages( String objectUid )
	{
		fileService.deleteFileRelationByObject( objectUid );
	}

	public void removeImage( String fileUid )
	{
		fileService.deleteFileRecord( fileUid );
	}

	public void unsetObjectImage( String objectUid, RelationshipType imageType ) throws ServiceException
	{
		fileService.deleteFileRelationByObjectAndRole( objectUid, imageType.name() );
	}
}
