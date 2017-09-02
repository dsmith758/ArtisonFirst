package com.walkersmithtech.artisonfirst.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.core.BaseObjectService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.model.fragment.ContactInfo;
import com.walkersmithtech.artisonfirst.data.model.object.Person;

@Service
public class PersonService extends BaseObjectService<Person>
{
	
	@Autowired
	private PersonImageService imageService;

	public PersonService()
	{
		dataType = DataType.PERSON;
		modelClass = Person.class;
	}

	public Person createPerson( Person person ) throws ServiceException
	{
		validate( person );
		return createModel( person );
	}

	public Person updatePerson( Person person ) throws ServiceException
	{
		validate( person );
		Person match = getModelByUid( person.getUid() );
		if ( match == null )
		{
			throw ErrorCode.PERSON_NOT_FOUND.exception;
		}
		return updateModel( person );
	}
	
	public void deletePerson( String uid ) throws ServiceException
	{
		imageService.removeObjectImages( uid );
		deleteModel( uid );
	}

	@Override
	protected void createIndex( Person model )
	{
		indexRepo.deleteByUid( model.getUid() );
		saveIndexData( model.getUid(), IndexType.FIRSTNAME, model.getFirstName() );
		saveIndexData( model.getUid(), IndexType.LASTNAME, model.getLastName() );
		if ( model.getContactInfo() != null & model.getContactInfo().size() > 0 )
		{
			for ( ContactInfo contactInfo : model.getContactInfo() )
			{
				if ( !contactInfo.getContactType().isEmpty() && !contactInfo.getValue().isEmpty() )
				{
					saveIndexData( model.getUid(), IndexType.CONTACT_INFO, contactInfo.getValue() );
				}
			}
		}
	}

	public Person getPersonByUid( String uid ) throws ServiceException
	{
		Person person = getModelByUid( uid );
		if ( person == null )
		{
			throw ErrorCode.PERSON_NOT_FOUND.exception;
		}
		return person;
	}

	public List<Person> getPersonByLastName( String lastname )
	{
		List<Person> models = getByTypeAndData( IndexType.LASTNAME, lastname );
		return models;
	}

	public List<Person> getPersonByFirstName( String firstmane )
	{
		List<Person> models = getByTypeAndData( IndexType.FIRSTNAME, firstmane );
		return models;
	}
	
	@Override
	protected void validate( Person person ) throws ServiceException
	{
		if ( person == null )
		{
			throw ErrorCode.PERSON_RECORD_NULL.exception;
		}

		if ( person.getFirstName() == null )
		{
			throw ErrorCode.PERSON_FIRSNAME_MISSING.exception;
		}

		if ( person.getLastName() == null )
		{
			throw ErrorCode.PERSON_LASTNAME_MISSING.exception;
		}
	}
}
