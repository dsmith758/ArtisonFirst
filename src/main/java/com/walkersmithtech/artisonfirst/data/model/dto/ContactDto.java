package com.walkersmithtech.artisonfirst.data.model.dto;

import com.walkersmithtech.artisonfirst.data.model.BaseDto;
import com.walkersmithtech.artisonfirst.data.model.object.Person;

public class ContactDto extends BaseDto
{

	private Person contact;
	private OrganizationDto organization;

	public Person getContact()
	{
		return contact;
	}

	public void setContact( Person contact )
	{
		this.contact = contact;
	}

	public OrganizationDto getOrganization()
	{
		return organization;
	}

	public void setOrganization( OrganizationDto organization )
	{
		this.organization = organization;
	}

}
