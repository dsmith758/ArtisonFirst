package com.walkersmithtech.artisonfirst.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.BaseFileService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.model.dto.ImageDto;
import com.walkersmithtech.artisonfirst.data.model.dto.PersonDto;
import com.walkersmithtech.artisonfirst.data.model.object.Person;

@Service
public class PersonImageService extends BaseFileService
{

	@Autowired
	private FileManagerSerivce fileService;

	@Autowired
	private PersonService personService;

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
			dto.setPerson( person );
			return dto;
		}
		throw ErrorCode.PERSON_NOT_FOUND.exception;
	}

	public ImageDto addPersonImage( ImageDto auth, String personUid, byte[] file ) throws ServiceException
	{
		auth = ( ImageDto ) fileService.createFileRecord( file, auth );
		auth = setProfileImage( auth, personUid, file );
		auth = ( ImageDto ) fileService.createFileRelation( personUid, RelationshipType.PERSON_IMAGE.name(), auth );
		return auth;
	}

	public ImageDto setProfileImage( ImageDto auth, String personUid, byte[] file ) throws ServiceException
	{
		unsetObjectImage( personUid, RelationshipType.PROFILE_IMAGE );
		auth = ( ImageDto ) fileService.createFileRelation( personUid, RelationshipType.PROFILE_IMAGE.name(), auth );
		return auth;
	}

}
