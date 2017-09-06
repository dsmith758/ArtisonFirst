package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.BaseDto;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Location;
import com.walkersmithtech.artisonfirst.data.model.relation.OrganizationPrincipal;

/*
 * A company becomes an organization when other data types are associated to it.
 */
@JsonInclude( Include.NON_EMPTY )
public class OrganizationDto extends BaseDto
{
	private Company company;
	private List<Location> addressInfo;
	private List<OrganizationPrincipal> principals;

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

	public List<OrganizationPrincipal> getPrincipals()
	{
		if ( principals == null )
		{
			principals = new ArrayList<>();
		}
		return principals;
	}

	public void setPrincipals( List<OrganizationPrincipal> principals )
	{
		this.principals = principals;
	}

	public void addPrincipal( OrganizationPrincipal principal )
	{
		getPrincipals();
		if ( !hasPrincipal( principal ) )
		{
			principals.add( principal );
		}
	}

	public boolean hasPrincipal( OrganizationPrincipal match )
	{
		getPrincipals();
		RoleData matchCompany = match.retrieveOrganization();
		RoleData matchPerson = match.retrievePrincipal();
		RoleData company;
		RoleData person;

		for ( OrganizationPrincipal principal : principals )
		{
			company = principal.retrieveOrganization();
			person = principal.retrievePrincipal();
			if ( matchCompany.getObjectUid().equals( company.getObjectUid() ) && matchPerson.getObjectUid().equals( person.getObjectUid() ) )
			{
				return true;
			}
		}
		return false;
	}
}
