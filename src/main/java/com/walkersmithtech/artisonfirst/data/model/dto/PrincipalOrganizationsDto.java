package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.walkersmithtech.artisonfirst.data.model.BaseList;
import com.walkersmithtech.artisonfirst.data.model.object.Person;

public class PrincipalOrganizationsDto extends BaseList<OrganizationDto>
{
	private Person principal;
	
	@JsonIgnore
	public List<OrganizationDto> getOrganizations()
	{
		return list;
	}

	@JsonIgnore
	public void setOrganizations( List<OrganizationDto> list )
	{
		this.list = list;
	}

	public Person getPrincipal()
	{
		return principal;
	}

	public void setPrincipal( Person principal )
	{
		this.principal = principal;
	}


}
