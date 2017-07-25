package com.walkersmithtech.artisonfirst.data.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.data.model.Person;

@JsonInclude( Include.NON_EMPTY )
public class PersonDto extends BaseDto
{
	private Person person;

	public Person getPerson()
	{
		return person;
	}

	public void setPerson( Person person )
	{
		this.person = person;
	}

}
