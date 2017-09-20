package com.walkersmithtech.artisonfirst.core.service;

import java.util.ArrayList;
import java.util.List;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.BaseRelationService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.object.Person;
import com.walkersmithtech.artisonfirst.data.model.relation.PersonContact;

public class PersonContactService extends BaseRelationService<PersonContact>
{
	public PersonContactService()
	{
		type = RelationshipType.PERSON_CONTACT;
		modelClass = PersonContact.class;
	}

	public void createOrUpdatePersonContacts( List<Person> contacts, Person person ) throws ServiceException
	{
		if ( contacts != null && contacts.size() > 0 )
		{
			PersonContact model;
			for ( Person contact : contacts )
			{
				model = new PersonContact();
				model.addPerson( person );
				model.addContact( contact );
				model = createOrUpdatePersonContact( model );
			}
		}
	}

	public PersonContact createOrUpdatePersonContact( PersonContact personContact ) throws ServiceException
	{
		validate( personContact );
		RoleData person = personContact.retrievePerson();
		RoleData contact = personContact.retrieveContact();
		PersonContact match = getRelationsByPersonAndContact( person.getObjectUid(), contact.getObjectUid() );
		if ( match != null )
		{
			personContact.setUid( match.getUid() );
			personContact.setId( match.getId() );
			personContact = updatePersonContact( personContact );
		}
		else
		{
			personContact = createPersonContact( personContact );
		}
		return personContact;
	}

	public PersonContact createPersonContact( PersonContact model ) throws ServiceException
	{
		model = createModel( model );
		return model;
	}

	public PersonContact updatePersonContact( PersonContact model ) throws ServiceException
	{
		model = updateModel( model );
		return model;
	}

	public List<PersonContact> getRelationsByPerson( String personUid )
	{
		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( personUid, type, RelationshipRole.PERSON );
		return buildList( entities );
	}

	public List<PersonContact> getRelationsByContact( String contactUid )
	{
		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( contactUid, type, RelationshipRole.CONTACT );
		return buildList( entities );
	}

	public PersonContact getRelationsByPersonAndContact( String personUid, String contactUid )
	{
		List<String> objectUids = new ArrayList<>();
		objectUids.add( personUid );
		objectUids.add( contactUid );
		return getRelationsByCollaboratorsAndType( objectUids, type );
	}

	private List<PersonContact> buildList( List<ObjectRelationData> entities )
	{
		List<PersonContact> models = new ArrayList<>();
		if ( entities != null && entities.size() > 0 )
		{
			for ( ObjectRelationData entity : entities )
			{
				models.add( convertEntityToModel( entity ) );
			}
		}
		return models;
	}

	@Override
	protected void createIndex( PersonContact model )
	{
		deleteIndex( model.getUid() );
		saveIndexData( model.getUid(), IndexType.PERSON, model.retrievePerson().getObject().getUid() );
		saveIndexData( model.getUid(), IndexType.CONTACT, model.retrieveContact().getObject().getUid() );
	}

	@Override
	protected void validate( PersonContact model ) throws ServiceException
	{
		RoleData person = model.retrievePerson();
		if ( person == null )
		{
			throw ErrorCode.CONTACT_PERSON_MISSING.exception;
		}

		if ( person.getObject() == null )
		{
			throw ErrorCode.CONTACT_PERSON_MISSING.exception;
		}

		RoleData contact = model.retrieveContact();
		if ( contact == null )
		{
			throw ErrorCode.CONTACT_MISSING.exception;
		}

		if ( contact.getObject() == null )
		{
			throw ErrorCode.CONTACT_MISSING.exception;
		}
	}
}
