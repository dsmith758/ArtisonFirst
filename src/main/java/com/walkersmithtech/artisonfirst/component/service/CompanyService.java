package com.walkersmithtech.artisonfirst.component.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.component.BaseObjectService;
import com.walkersmithtech.artisonfirst.component.ServiceException;
import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.relation.PersonCompany;

@Service
public class CompanyService extends BaseObjectService<Company>
{

	@Autowired
	private PersonCompanyService personCompanyService;
	
	@Autowired
	private PersonImageService imageService;
	
	public CompanyService()
	{
		dataType = DataType.COMPANY;
		modelClass = Company.class;
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
	
	public void deleteCompany( String uid ) throws ServiceException
	{
		imageService.removeObjectImages( uid );
		deleteModel( uid );
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
	
	public Company getCompanyByPersonUid( String personUid ) throws ServiceException
	{
		PersonCompany personCompany = personCompanyService.getModelBySourceUid( personUid );
		if ( personCompany != null )
		{
			Company company = getCompanyByUid( personCompany.getTargetUid() );
			return company;
		}
		throw ErrorCode.COMPANY_NOT_FOUND.exception;
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
