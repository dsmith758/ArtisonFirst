package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.data.model.BaseDto;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Location;
import com.walkersmithtech.artisonfirst.data.model.object.Person;

@JsonInclude( Include.NON_EMPTY )
public class RegistrationDto extends BaseDto
{
	private Person person;
	private Company company;
	private List<Location> addressInfo;

	public Person getPerson()
	{
		return person;
	}

	public void setPerson( Person person )
	{
		this.person = person;
	}

	public Company getCompany()
	{
		return company;
	}

	public void setCompany( Company company )
	{
		this.company = company;
	}

	public List<Location> getAddressInfo()
	{
		return addressInfo;
	}

	public void setAddressInfo( List<Location> addressInfo )
	{
		this.addressInfo = addressInfo;
	}

}
