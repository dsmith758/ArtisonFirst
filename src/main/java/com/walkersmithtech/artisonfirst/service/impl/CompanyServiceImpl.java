package com.walkersmithtech.artisonfirst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.Company;
import com.walkersmithtech.artisonfirst.data.model.Location;
import com.walkersmithtech.artisonfirst.data.model.dto.CompanyDto;
import com.walkersmithtech.artisonfirst.data.model.dto.ImageDto;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyLocation;
import com.walkersmithtech.artisonfirst.data.model.relation.PersonCompany;
import com.walkersmithtech.artisonfirst.service.BaseModelService;
import com.walkersmithtech.artisonfirst.service.ServiceException;

@Service
public class CompanyServiceImpl extends BaseModelService<Company>
{

	@Autowired
	private LocationServiceImpl locationService;

	@Autowired
	private CompanyLocationServiceImpl companyLocationService;

	@Autowired
	private PersonCompanyServiceImpl personCompanyService;
	
	@Autowired
	private FileManagerSerivceImpl fileService;

	public CompanyServiceImpl()
	{
		dataType = DataType.COMPANY;
		modelClass = Company.class;
	}

	public CompanyDto createOrganization( CompanyDto model ) throws ServiceException
	{
		Company company = createCompany( model.getCompany() );
		model.setCompany( company );
		List<Location> locations = locationService.createLocations( model.getAddressInfo() );
		model.setAddressInfo( locations );
		companyLocationService.createCompanyLocation( locations, company );
		return model;
	}

	public CompanyDto updateOrganization( CompanyDto model ) throws ServiceException
	{
		Company company = updateCompany( model.getCompany() );
		model.setCompany( company );
		List<Location> locations = locationService.updateLocations( model.getAddressInfo() );
		model.setAddressInfo( locations );
		baseService.deleteRelationModelsBySourceUidAndRole( company.getUid(), RelationshipRole.COMPANY_LOCATION.name() );
		companyLocationService.createCompanyLocation( locations, company );
		return model;
	}

	public CompanyDto getOrganizationByPersonUid( CompanyDto model ) throws ServiceException
	{
		PersonCompany personCompany = personCompanyService.getModelBySourceUid( model.getAccount().getPersonUid() );
		if ( personCompany != null )
		{
			Company company = getCompanyByUid( personCompany.getTargetUid() );
			model.setCompany( company );
			List<CompanyLocation> addresses = companyLocationService.getCompanyLocationsByCompanyUid( company.getUid() );
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
			model.setAddressInfo( addressInfo );
		}
		return model;
	}

	public Company createCompany( Company company ) throws ServiceException
	{
		validateCompany( company );
		return createModel( company );
	}

	public Company updateCompany( Company company ) throws ServiceException
	{
		validateCompany( company );
		Company match = getModelByUid( company.getUid() );
		if ( match == null )
		{
			throw ErrorCode.COMPANY_NOT_FOUND.exception;
		}
		return updateModel( company );
	}

	@Override
	public Company createModel( Company model )
	{
		if ( model != null )
		{
			model = createData( model );
			createIndex( model );
		}
		return model;
	}

	@Override
	public Company updateModel( Company model )
	{
		if ( model != null )
		{
			model = updateData( model );
			createIndex( model );
		}
		return model;
	}

	private void createIndex( Company model )
	{
		deleteIndex( model.getUid() );
		saveIndexData( model.getUid(), IndexType.COMPANY_NAME, model.getCompanyName() );
		saveIndexData( model.getUid(), IndexType.BUSINESS_TYPE, model.getBusinessType() );
	}

	private void deleteIndex( String uid )
	{
		indexRepo.deleteByUid( uid );
	}

	public Company getCompanyByUid( String uid ) throws ServiceException
	{
		Company company = getModelByUid( uid );
		if ( company == null )
		{
			throw ErrorCode.COMPANY_NOT_FOUND.exception;
		}
		return company;
	}

	public List<Company> getCompanyByName( String companyName )
	{
		List<Company> models = getByTypeAndData( IndexType.COMPANY_NAME, companyName );
		return models;
	}

	public List<Company> getCompanyByBusinessType( String businessType )
	{
		List<Company> models = getByTypeAndData( IndexType.BUSINESS_TYPE, businessType );
		return models;
	}
	
	public ImageDto addAndSetCompanyLogo( ImageDto auth, String companyUid, byte[] file ) throws ServiceException
	{
		auth = addCompanyImage( auth, companyUid, file );
		auth = setCompanyLogo( auth, companyUid, file );
		return auth;
	}
	
	public ImageDto addCompanyImage( ImageDto auth, String companyUid, byte[] file ) throws ServiceException
	{
		auth = ( ImageDto ) fileService.createFileRecord( file, auth );
		auth = setCompanyLogo( auth, companyUid, file );
		auth = (ImageDto) fileService.createFileRelation( companyUid, RelationshipRole.COMPANY_IMAGE.name(), auth );
		return auth;
	}
	
	public ImageDto setCompanyLogo( ImageDto auth, String companyUid, byte[] file ) throws ServiceException
	{
		auth = ( ImageDto ) fileService.createFileRecord( file, auth );
		fileService.deleteFileRelationByObjectAndRole( companyUid, RelationshipRole.COMPANY_LOGO.name() );
		auth = (ImageDto) fileService.createFileRelation( companyUid, RelationshipRole.COMPANY_LOGO.name(), auth );
		return auth;
	}

	public void removeImage( String fileUid )
	{
		fileService.deleteFileRecord( fileUid );
	}

	private void validateCompany( Company company ) throws ServiceException
	{
		if ( company == null )
		{
			throw ErrorCode.COMPANY_RECORD_NULL.exception;
		}

		if ( company.getCompanyName() == null )
		{
			throw ErrorCode.COMPANY_NAME_MISSING.exception;
		}
	}

}
