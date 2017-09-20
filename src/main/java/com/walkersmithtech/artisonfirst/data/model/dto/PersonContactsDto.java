package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.walkersmithtech.artisonfirst.data.model.BaseList;
import com.walkersmithtech.artisonfirst.data.model.object.Person;

public class PersonContactsDto extends BaseList<ContactDto>
{
	private Person person;

	@JsonIgnore
	public List<ContactDto> getContacts()
	{
		return list;
	}

	@JsonIgnore
	public void setContacts( List<ContactDto> contacts )
	{
		this.list = contacts;
	}

	public Person getPerson()
	{
		return person;
	}

	public void setPerson( Person person )
	{
		this.person = person;
	}
}
