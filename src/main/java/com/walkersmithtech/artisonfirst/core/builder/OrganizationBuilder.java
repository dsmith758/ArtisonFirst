package com.walkersmithtech.artisonfirst.core.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.core.BaseBuilder;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.core.service.CompanyLocationService;
import com.walkersmithtech.artisonfirst.core.service.CompanyService;
import com.walkersmithtech.artisonfirst.core.service.LocationService;
import com.walkersmithtech.artisonfirst.core.service.PersonCompanyService;
import com.walkersmithtech.artisonfirst.core.service.PersonService;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.dto.OrganizationDto;
import com.walkersmithtech.artisonfirst.data.model.dto.PrincipalOrganizationsDto;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Location;
import com.walkersmithtech.artisonfirst.data.model.object.Person;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyLocation;
import com.walkersmithtech.artisonfirst.data.model.relation.OrganizationPrincipal;

@Service
public class OrganizationBuilder extends BaseBuilder<OrganizationDto>
{
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private PersonService personService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private CompanyLocationService companyLocationService;
	
	@Autowired
	private PersonCompanyService personCompanyService;

	public OrganizationDto createOrganization( OrganizationDto model ) throws ServiceException
	{
		Company company = companyService.createCompany( model.getCompany() );
		List<Location> locations = locationService.createLocations( model.getAddressInfo() );
		companyLocationService.createOrUpdateCompanyLocations( locations, company );
		model = createOrUpdateCompanyPrincipal( model );
		return buildCompanyDto( company, model );
	}

	public OrganizationDto updateOrganization( OrganizationDto model ) throws ServiceException
	{
		Company company = companyService.updateCompany( model.getCompany() );
		List<Location> locations = locationService.updateLocations( model.getAddressInfo() );
		companyLocationService.createOrUpdateCompanyLocations( locations, company );
		model = createOrUpdateCompanyPrincipal( model );
		return buildCompanyDto( company, model );
	}
	
	public OrganizationDto createOrUpdateCompanyPrincipal( OrganizationDto model ) throws ServiceException
	{
		Person person = personService.getModelByUid( model.getAccount().getPersonUid() );
		if ( person == null )
		{
			throw ErrorCode.ORGRANIZATION_MISSING_PERSON.exception;
		}
		
		Company company = model.getCompany();
		if ( company == null )
		{
			throw ErrorCode.ORGRANIZATION_MISSING_COMPANY.exception;
		}

		OrganizationPrincipal personCompany = new OrganizationPrincipal();
		personCompany.addOrganization( company );
		personCompany.addPrincipal( person );
		personCompany.setIsDefault( false );
		personCompany = personCompanyService.createModel( personCompany );
		return model;
	}
	
	public PrincipalOrganizationsDto getOrganizationsByPersonUid( OrganizationDto model ) throws ServiceException
	{
		String personUid = model.getAccount().getPersonUid();
		List<Company> companies = companyService.getCompaniesByPersonUid( personUid );
		List<OrganizationDto> organizations = new ArrayList<>();
		PrincipalOrganizationsDto dto = new PrincipalOrganizationsDto();
		OrganizationDto org;
		if ( companies != null && companies.size() > 0 )
		{
			for ( Company company : companies )
			{
				org = new OrganizationDto();
				organizations.add( buildCompanyDto( company, org ) );
			}
		}
		dto.setOrganizations( organizations );
		dto.setAccount( model.getAccount() );
		return dto;
	}

	public OrganizationDto getDefaultOrganizationByPersonUid( OrganizationDto model ) throws ServiceException
	{
		String personUid = model.getAccount().getPersonUid();
		Company company = companyService.getDefaultCompanyByPersonUid( personUid );
		return buildCompanyDto( company, model );
	}

	public OrganizationDto getOrganizationByCompanyUid( OrganizationDto model ) throws ServiceException
	{
		String companyUid = model.getAccount().getCompanyUid();
		Company company = companyService.getCompanyByUid( companyUid );
		return buildCompanyDto( company, model );
	}
	
	public boolean deleteOrganization( String uid )
	{
		// we want to delete the organization, but not the company...
		companyService.deleteModel( uid );
		return true;
	}
	
	private OrganizationDto buildCompanyDto( Company company, OrganizationDto model ) throws ServiceException
	{
		if ( company != null )
		{
			model.getAccount().setCompanyUid( company.getUid() );
			model.setCompany( company );
			model.setAddressInfo( buildCompanyAddresses( company.getUid() ) );
			model.setPrincipals( personCompanyService.getRelationsByOrganization( company.getUid() ) );
		}
		return model;
	}
	
	private List<Location> buildCompanyAddresses( String companyUid ) throws ServiceException
	{
		List<CompanyLocation> addresses = companyLocationService.getRelationsByCompany( companyUid );
		Location location;
		List<Location> addressInfo = new ArrayList<>();
		if ( addresses != null && addresses.size() > 0 )
		{
			RoleData roleData;
			for ( CompanyLocation address : addresses )
			{
				roleData = address.getCollaborator( RelationshipRole.ADDRESS.name() );
				if ( roleData != null )
				{
					location = locationService.getModelByUid( roleData.getObjectUid() );
					if ( location != null )
					{
						addressInfo.add( location );
					}
				}
			}
		}
		return addressInfo;
	}


}
