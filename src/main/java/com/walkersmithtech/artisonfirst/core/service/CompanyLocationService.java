package com.walkersmithtech.artisonfirst.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.BaseRelationService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
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

	public CompanyLocation createCompanyLocation( CompanyLocation companyLocation ) throws ServiceException
	{
		validate( companyLocation );
		RoleData resident = companyLocation.retrieveResident();
		RoleData address = companyLocation.retrieveAddress();
		CompanyLocation match = getRelationsByCompanyAndLocation( resident.getObjectUid(), address.getObjectUid() );
		if ( match != null )
		{
			updateCompanyLocation( companyLocation );
		}
		companyLocation = createModel( companyLocation );
		return companyLocation;
	}

	public CompanyLocation updateCompanyLocation( CompanyLocation companyLocation ) throws ServiceException
	{
		validate( companyLocation );
		companyLocation = updateModel( companyLocation );
		return companyLocation;
	}

	public List<CompanyLocation> getRelationsByCompany( String companyUid )
	{
		return getRelatonsByCollaboratorAndTypeAndRole( companyUid, type, RelationshipRole.RESIDENT );
	}

	public List<CompanyLocation> getRelationsByLocation( String locationUid )
	{
		return getRelatonsByCollaboratorAndTypeAndRole( locationUid, type, RelationshipRole.LOCATION );
	}

	public CompanyLocation getRelationsByCompanyAndLocation( String companyUid, String locationUid )
	{
		List<String> objectUids = new ArrayList<>();
		objectUids.add( companyUid );
		objectUids.add( locationUid );
		return getRelationsByCollaboratorsAndType( objectUids, type );
	}

	@Override
	protected void createIndex( CompanyLocation model )
	{
		deleteIndex( model.getUid() );
		saveIndexData( model.getUid(), IndexType.RESIDENT, model.retrieveResident().getObject().getUid() );
		saveIndexData( model.getUid(), IndexType.ADDRESS, model.retrieveAddress().getObject().getUid() );
	}

	@Override
	public void validate( CompanyLocation model ) throws ServiceException
	{
		RoleData resident = model.retrieveResident();
		if ( resident == null )
		{
			throw ErrorCode.ADDRESS_MISSING_COMPANY.exception;
		}

		if ( resident.getObject() == null )
		{
			throw ErrorCode.ADDRESS_MISSING_COMPANY.exception;
		}

		RoleData address = model.retrieveAddress();
		if ( address == null )
		{
			throw ErrorCode.ADDRESS_MISSING_LOCATION.exception;
		}

		if ( address.getObject() == null )
		{
			throw ErrorCode.ADDRESS_MISSING_LOCATION.exception;
		}

	}

}
