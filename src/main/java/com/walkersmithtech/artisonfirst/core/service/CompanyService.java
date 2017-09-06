package com.walkersmithtech.artisonfirst.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.core.BaseObjectService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.relation.OrganizationPrincipal;

@Service
public class CompanyService extends BaseObjectService<Company>
{

	@Autowired
	private PersonCompanyService personCompanyService;

	@Autowired
	private PersonImageService imageService;

	public CompanyService()
	{
		this.dataType = DataType.COMPANY;
		this.modelClass = Company.class;
	}

	public Company createCompany( Company company ) throws ServiceException
	{
		validate( company );
		return createModel( company );
	}

	public Company updateCompany( Company company ) throws ServiceException
	{
		validate( company );
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
	protected void createIndex( Company model )
	{
		deleteIndex( model.getUid() );
		saveIndexData( model.getUid(), IndexType.COMPANY_NAME, model.getCompanyName() );
		saveIndexData( model.getUid(), IndexType.BUSINESS_TYPE, model.getBusinessType() );
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

	public Company getDefaultCompanyByPersonUid( String personUid ) throws ServiceException
	{
		List<OrganizationPrincipal> principals = personCompanyService.getRelationsByPrinciple( personUid );
		if ( principals != null && principals.size() > 0 )
		{
			RoleData role;
			Company company;
			for ( OrganizationPrincipal principal : principals )
			{
				if ( principal.getIsDefault() )
				{
					role = principal.getCollaborator( RelationshipRole.ORGANIZATION.name() );
					if ( role != null )
					{
						company = getCompanyByUid( role.getObjectUid() );
						return company;
					}
				}
			}
		}
		throw ErrorCode.COMPANY_NOT_FOUND.exception;
	}

	public List<Company> getCompaniesByPersonUid( String personUid ) throws ServiceException
	{
		List<OrganizationPrincipal> principals = personCompanyService.getRelationsByPrinciple( personUid );
		if ( principals != null && principals.size() > 0 )
		{
			List<Company> companies = new ArrayList<>();
			RoleData role;
			Company company;
			for ( OrganizationPrincipal principal : principals )
			{
				role = principal.getCollaborator( RelationshipRole.ORGANIZATION.name() );
				if ( role != null )
				{
					company = getCompanyByUid( role.getObjectUid() );
					companies.add( company );
				}
			}
			return companies;
		}
		throw ErrorCode.COMPANY_NOT_FOUND.exception;
	}

	@Override
	protected void validate( Company company ) throws ServiceException
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
