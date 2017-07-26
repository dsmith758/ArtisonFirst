package com.walkersmithtech.artisonfirst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.model.Company;
import com.walkersmithtech.artisonfirst.data.model.Location;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyLocation;
import com.walkersmithtech.artisonfirst.service.BaseRelationService;

@Service
public class CompanyLocationServiceImpl extends BaseRelationService<CompanyLocation>
{

	public CompanyLocationServiceImpl()
	{
		roleType = RelationshipRole.COMPANY_LOCATION;
		relationClass = CompanyLocation.class;
		sourceClass = Company.class;
		targetClass = Location.class;
	}

	public void createCompanyLocation( List<Location> addresses, Company company )
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
		if ( relation != null )
		{
			ObjectRelationData entity = dataRepo.findBySourceUidAndTargetUid( relation.getSourceUid(), relation.getTargetUid() );
			if ( entity == null )
			{
				relation = createData( relation );
			}
		}
		return relation;
	}

	@Override
	public CompanyLocation updateModel( CompanyLocation relation )
	{
		if ( relation != null )
		{
			ObjectRelationData entity = dataRepo.findBySourceUidAndTargetUid( relation.getSourceUid(), relation.getTargetUid() );
			if ( entity == null )
			{
				relation = createData( relation );
			}
		}
		return relation;
	}
	
	public List<CompanyLocation> getCompanyLocationsByCompanyUid( String uid )
	{
		List<ObjectRelationData> entities = dataRepo.findBySourceUidAndRole( uid, RelationshipRole.COMPANY_LOCATION.name() );
		List<CompanyLocation> addresses = new ArrayList<>();
		if ( entities != null && entities.size() > 0 )
		{
			for ( ObjectRelationData entity : entities )
			{
				addresses.add( convertEntityToModel( entity ) );
			}
		}
		return addresses;
	}
	
	public List<CompanyLocation> getCompanyLocationsByLocationUid( String uid )
	{
		List<ObjectRelationData> entities = dataRepo.findByTargetUidAndRole( uid, RelationshipRole.COMPANY_LOCATION.name() );
		List<CompanyLocation> addresses = new ArrayList<>();
		if ( entities != null && entities.size() > 0 )
		{
			for ( ObjectRelationData entity : entities )
			{
				addresses.add( convertEntityToModel( entity ) );
			}
		}
		return addresses;
	}

}
