package com.walkersmithtech.artisonfirst.core.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.core.service.PersonContactService;
import com.walkersmithtech.artisonfirst.core.service.PersonService;
import com.walkersmithtech.artisonfirst.data.model.Account;
import com.walkersmithtech.artisonfirst.data.model.dto.ContactDto;
import com.walkersmithtech.artisonfirst.data.model.dto.OrganizationDto;
import com.walkersmithtech.artisonfirst.data.model.dto.PersonContactsDto;
import com.walkersmithtech.artisonfirst.data.model.object.Person;
import com.walkersmithtech.artisonfirst.data.model.relation.PersonContact;

public class ContactBuilder
{

	@Autowired
	private PersonService service;

	@Autowired
	private PersonContactService personContactService;

	@Autowired
	OrganizationBuilder orgBuilder;

	public List<ContactDto> createContacts( List<ContactDto> models ) throws ServiceException
	{
		if ( models != null && models.size() > 0 )
		{
			for ( ContactDto model : models )
			{
				model = createContact( model );
			}
		}
		return models;
	}

	public ContactDto createContact( ContactDto model ) throws ServiceException
	{
		Person contact = service.createPerson( model.getContact() );
		Person person = service.getPersonByUid( model.getAccount().getPersonUid() );
		model.setContact( contact );
		return createOrUpdateContact( model, person, contact );
	}

	public ContactDto updateContact( ContactDto model ) throws ServiceException
	{
		Person contact = service.updatePerson( model.getContact() );
		Person person = service.getPersonByUid( model.getAccount().getPersonUid() );
		model.setContact( contact );
		return createOrUpdateContact( model, person, contact );
	}

	private ContactDto createOrUpdateContact( ContactDto model, Person person, Person contact ) throws ServiceException
	{
		PersonContact personContact = new PersonContact();
		personContact.addPerson( person );
		personContact.addContact( contact );
		personContact = personContactService.createOrUpdatePersonContact( personContact );
		buildContactOrganization( model );
		return buildContactDto( person, contact, model );
	}

	public ContactDto getContactByUid( ContactDto model, String contactUid ) throws ServiceException
	{
		Person contact = service.getPersonByUid( contactUid );
		Person person = service.getPersonByUid( model.getAccount().getPersonUid() );
		return buildContactDto( person, contact, model );
	}

	public PersonContactsDto getPersonContacts( ContactDto model ) throws ServiceException
	{
		PersonContactsDto dto = new PersonContactsDto();
		dto.setAccount( model.getAccount() );
		
		String personUid = model.getAccount().getPersonUid();
		Person person = service.getPersonByUid( model.getAccount().getPersonUid() );
		if ( person == null )
		{
			throw ErrorCode.PERSON_NOT_FOUND.exception;
		}

		List<PersonContact> personContacts = personContactService.getRelationsByPerson( personUid );
		List<ContactDto> contacts = new ArrayList<>();
		if ( personContacts != null && personContacts.size() > 0 )
		{
			Person contact;
			ContactDto contactDto;
			for ( PersonContact personContact : personContacts )
			{
				contactDto = new ContactDto();
				contact = service.getModelByUid( personContact.retrieveContact().getObjectUid() );
				contacts.add( buildContactDto( person, contact, contactDto ) );
			}
		}
		
		dto.setPerson( person );
		dto.setContacts( contacts );
		return dto;
	}
	
	public boolean deleteContact( String uid )
	{
		service.deleteModel( uid );
		return true;
	}
	
	private ContactDto buildContactDto( Person person, Person contact, ContactDto model ) throws ServiceException
	{
		PersonContact personContact = personContactService.getRelationsByPersonAndContact( person.getUid(), contact.getUid() );
		if ( personContact == null )
		{
			throw ErrorCode.CONTACT_PERSON_MISMATCH.exception;
		}
		model.setContact( contact );
		return model;
	}

	private ContactDto buildContactOrganization( ContactDto contact ) throws ServiceException
	{
		OrganizationDto org = contact.getOrganization();
		if ( org != null )
		{
			Account account = new Account();
			account.setPersonUid( contact.getContact().getUid() );
			org.setAccount( account );
			if ( org.getCompany().getUid() != null )
			{
				org = orgBuilder.updateOrganization( org );
			}
			else
			{
				org = orgBuilder.createOrganization( org );
			}
			contact.setOrganization( org );
		}
		return contact;
	}

}
