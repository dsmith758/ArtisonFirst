package com.walkersmithtech.artisonfirst.component.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.component.BaseRelationService;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Location;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyLocation;

@Service
public class CompanyLocationService extends BaseRelationService<CompanyLocation>
{

	public CompanyLocationService()
	{
		roleType = RelationshipRole.COMPANY_LOCATION.name();
		relationClass = CompanyLocation.class;
		sourceClass = Company.class;
		targetClass = Location.class;
	}

	public void createCompanyLocations( List<Location> addresses, Company company )
	{
		if ( addresses != null && addresses.size() > 0 )
		{
			CompanyLocation companyLocation;
			for ( Location address : addresses )
			{
				companyLocation = new CompanyLocation();
				companyLocation.setSourceUid( company.getUid() );
				companyLocation.setTargetUid( address.getUid() );
				companyLocation = createModel( companyLocation );
			}
		}
	}

	@Override
	public CompanyLocation createModel( CompanyLocation relation )
	{
		return saveRelationship( relation );
	}

	@Override
	public CompanyLocation updateModel( CompanyLocation relation )
	{
		return saveRelationship( relation );
	}

	public List<CompanyLocation> getCompanyLocationsByCompanyUid( String uid )
	{
		return getRelationsBySourceAndType( uid, RelationshipRole.COMPANY_LOCATION );
	}

	public List<CompanyLocation> getCompanyLocationsByLocationUid( String uid )
	{
		return getRelationsByTargetAndType( uid, RelationshipRole.COMPANY_LOCATION );
	}

}
