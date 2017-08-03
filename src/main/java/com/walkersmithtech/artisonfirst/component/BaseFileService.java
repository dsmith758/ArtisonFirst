package com.walkersmithtech.artisonfirst.component;

import org.springframework.beans.factory.annotation.Autowired;

import com.walkersmithtech.artisonfirst.component.service.FileManagerSerivce;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;

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

	public void unsetObjectImage( String objectUid, RelationshipRole imageType ) throws ServiceException
	{
		fileService.deleteFileRelationByObjectAndRole( objectUid, imageType.name() );
	}
}
