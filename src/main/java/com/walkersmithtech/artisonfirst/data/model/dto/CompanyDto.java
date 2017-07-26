package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.data.model.Company;
import com.walkersmithtech.artisonfirst.data.model.Location;

@JsonInclude( Include.NON_EMPTY )
public class CompanyDto extends BaseDto
{
	private Company company;
	private List<Location> addressInfo;

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
