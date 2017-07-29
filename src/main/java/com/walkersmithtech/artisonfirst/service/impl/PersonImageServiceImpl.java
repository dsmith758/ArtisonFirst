package com.walkersmithtech.artisonfirst.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.Person;
import com.walkersmithtech.artisonfirst.data.model.dto.ImageDto;
import com.walkersmithtech.artisonfirst.data.model.dto.PersonDto;
import com.walkersmithtech.artisonfirst.service.ServiceException;

@Service
public class PersonImageServiceImpl
{

	@Autowired
	private FileManagerSerivceImpl fileService;

	@Autowired
	private PersonServiceImpl personService;

	public PersonDto addAndSetAsProfileImage( ImageDto auth, String personUid, byte[] file ) throws ServiceException
	{
		Person person = personService.getModelByUid( personUid );
		if ( person != null )
		{
			auth = addPersonImage( auth, personUid, file );
			auth = setProfileImage( auth, personUid, file );
			PersonDto dto = new PersonDto();
			person.setImageUri( "/images/" + auth.getUid() );
			personService.updatePerson( person );
			dto.setAccount( auth.getAccount() );
			return dto;
		}
		throw ErrorCode.PERSON_NOT_FOUND.exception;
	}

	public ImageDto addPersonImage( ImageDto auth, String personUid, byte[] file ) throws ServiceException
	{
		auth = ( ImageDto ) fileService.createFileRecord( file, auth );
		auth = setProfileImage( auth, personUid, file );
		auth = ( ImageDto ) fileService.createFileRelation( personUid, RelationshipRole.PERSON_IMAGE.name(), auth );
		return auth;
	}

	public ImageDto setProfileImage( ImageDto auth, String personUid, byte[] file ) throws ServiceException
	{
		unsetProfileImage( personUid );
		auth = ( ImageDto ) fileService.createFileRelation( personUid, RelationshipRole.PROFILE_IMAGE.name(), auth );
		return auth;
	}

	public void removePersonImages( String personUid )
	{
		fileService.deleteFileRelationByObject( personUid );
	}

	public void removeImage( String fileUid )
	{
		fileService.deleteFileRecord( fileUid );
	}

	public void unsetProfileImage( String personUid ) throws ServiceException
	{
		fileService.deleteFileRelationByObjectAndRole( personUid, RelationshipRole.PROFILE_IMAGE.name() );
	}
}
