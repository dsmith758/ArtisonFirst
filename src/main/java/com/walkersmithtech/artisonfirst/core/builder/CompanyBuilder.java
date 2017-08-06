package com.walkersmithtech.artisonfirst.component.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.component.BaseBuilder;
import com.walkersmithtech.artisonfirst.component.ServiceException;
import com.walkersmithtech.artisonfirst.component.service.CompanyLocationService;
import com.walkersmithtech.artisonfirst.component.service.CompanyService;
import com.walkersmithtech.artisonfirst.component.service.LocationService;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.dto.CompanyDto;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Location;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyLocation;

@Service
public class CompanyBuilder extends BaseBuilder<CompanyDto>
{
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private LocationService locationService;

	@Autowired
	private CompanyLocationService companyLocationService;
	
	public CompanyDto createOrganization( CompanyDto model ) throws ServiceException
	{
		Company company = companyService.createCompany( model.getCompany() );
		model.setCompany( company );
		List<Location> locations = locationService.createLocations( model.getAddressInfo() );
		model.setAddressInfo( locations );
		companyLocationService.createCompanyLocations( locations, company );
		return model;
	}

	public CompanyDto updateOrganization( CompanyDto model ) throws ServiceException
	{
		Company company = companyService.updateCompany( model.getCompany() );
		model.setCompany( company );
		List<Location> locations = locationService.updateLocations( model.getAddressInfo() );
		model.setAddressInfo( locations );
		baseService.deleteRelationModelsBySourceUidAndRole( company.getUid(), RelationshipRole.COMPANY_LOCATION.name() );
		companyLocationService.createCompanyLocations( locations, company );
		return model;
	}

	public CompanyDto getOrganizationByPersonUid( String personUid, CompanyDto model ) throws ServiceException
	{
		Company company = companyService.getCompanyByPersonUid( personUid );
		return buildCompanyDto( company, model );
	}
	
	public CompanyDto getOrganizationByCompanyUid( String uid, CompanyDto model ) throws ServiceException
	{
		Company company = companyService.getCompanyByUid( uid );
		return buildCompanyDto( company, model );
	}
	
	private CompanyDto buildCompanyDto( Company company, CompanyDto model ) throws ServiceException
	{
		if ( company != null )
		{
			model.setCompany( company );
			model.setAddressInfo( getCompanyLocationsByCompanyUid( company.getUid() ) );
			model.getAccount().setCompanyUid( company.getUid() );
		}
		return model;
	}
	
	public List<Location> getCompanyLocationsByCompanyUid( String companyUid ) throws ServiceException
	{
		List<CompanyLocation> addresses = companyLocationService.getCompanyLocationsByCompanyUid( companyUid );
		Location location;
		List<Location> addressInfo = new ArrayList<>();
		if ( addresses != null && addresses.size() > 0 )
		{
			for ( CompanyLocation address : addresses )
			{
				location = locationService.getModelByUid( address.getTargetUid() );
				if ( location != null )
				{
					addressInfo.add( location );
				}
			}
		}
		return addressInfo;
	}
	
}
