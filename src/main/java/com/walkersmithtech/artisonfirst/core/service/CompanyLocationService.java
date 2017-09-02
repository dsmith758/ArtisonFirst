package com.walkersmithtech.artisonfirst.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.BaseRelationService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Location;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyLocation;

@Service
public class CompanyLocationService extends BaseRelationService<CompanyLocation>
{

	public CompanyLocationService()
	{
		type = RelationshipType.COMPANY_LOCATION;
		modelClass = CompanyLocation.class;
	}

	public void createCompanyLocations( List<Location> addresses, Company company )
	{
		if ( addresses != null && addresses.size() > 0 )
		{
			CompanyLocation companyLocation;
			for ( Location address : addresses )
			{
				companyLocation = new CompanyLocation();
				companyLocation.addResident( company );
				companyLocation.addLocation( address );
				companyLocation = createModel( companyLocation );
			}
		}
	}

	@Override
	protected void createIndex( CompanyLocation model )
	{
		deleteIndex( model.getUid() );
	}

	public List<CompanyLocation> getCompanyLocationsByCompanyUid( String uid )
	{
		return getRelationsBySourceAndType( uid, type, RelationshipRole.RESIDENT );
	}

	public List<CompanyLocation> getCompanyLocationsByLocationUid( String uid )
	{
		return getRelationsBySourceAndType( uid, type, RelationshipRole.LOCATION );
	}

	@Override
	public void validate( CompanyLocation model ) throws ServiceException
	{
	}

}
