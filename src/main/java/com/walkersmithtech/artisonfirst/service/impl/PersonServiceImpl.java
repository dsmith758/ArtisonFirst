package com.walkersmithtech.artisonfirst.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.data.model.Person;
import com.walkersmithtech.artisonfirst.data.model.fragment.ContactInfo;
import com.walkersmithtech.artisonfirst.service.BaseModelService;
import com.walkersmithtech.artisonfirst.service.ServiceException;

@Service
public class PersonServiceImpl extends BaseModelService<Person>
{

	public PersonServiceImpl()
	{
		dataType = DataType.PERSON;
		modelClass = Person.class;
	}

	public Person createPerson( Person person ) throws ServiceException
	{
		validatePerson( person );
		return createModel( person );
	}

	public Person updatePerson( Person person ) throws ServiceException
	{
		validatePerson( person );
		Person match = getModelByUid( person.getUid() );
		if ( match == null )
		{
			throw ErrorCode.PERSON_NOT_FOUND.exception;
		}
		return updateModel( person );
	}

	@Override
	public Person createModel( Person model )
	{
		if ( model != null )
		{
			model = createData( model );
			createIndex( model );
		}
		return model;
	}

	@Override
	public Person updateModel( Person model )
	{
		if ( model != null )
		{
			model = updateData( model );
			createIndex( model );
		}
		return model;
	}

	private void createIndex( Person model )
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

	private void validatePerson( Person person ) throws ServiceException
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
