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
import com.walkersmithtech.artisonfirst.data.model.object.Product;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyProduct;

@Service
public class CompanyProductService extends BaseRelationService<CompanyProduct>
{

	public CompanyProductService()
	{
		type = RelationshipType.COMPANY_PRODUCT;
		modelClass = CompanyProduct.class;
	}
	
	public void createOrUpdateCompanyProducts( List<Product> products, Company company ) throws ServiceException
	{
		if ( products != null && products.size() > 0 )
		{
			CompanyProduct companyProduct;
			for ( Product product : products )
			{
				companyProduct = new CompanyProduct();
				companyProduct.addProduct( product );
				companyProduct.addOwner( company );
				companyProduct = createOrUpdateCompanyProduct( companyProduct );
			}
		}
	}

	public CompanyProduct createOrUpdateCompanyProduct( CompanyProduct companyProduct ) throws ServiceException
	{
		validate( companyProduct );
		RoleData product = companyProduct.retrieveProduct();
		RoleData owner = companyProduct.retrieveOwner();
		CompanyProduct match = getRelationsByCompanyAndProduct( owner.getObjectUid(), product.getObjectUid() );
		if ( match != null )
		{
			companyProduct.setUid( match.getUid() );
			companyProduct.setId( match.getId() );
			companyProduct = updateCompanyProduct( companyProduct );
		}
		else
		{
			companyProduct = createCompanyProduct( companyProduct );
		}
		return companyProduct;
	}

	private CompanyProduct createCompanyProduct( CompanyProduct companyProduct ) throws ServiceException
	{
		companyProduct = createModel( companyProduct );
		return companyProduct;
	}

	private CompanyProduct updateCompanyProduct( CompanyProduct companyProduct ) throws ServiceException
	{
		companyProduct = updateModel( companyProduct );
		return companyProduct;
	}

	public List<CompanyProduct> getRelationsByCompany( String ownerUid )
	{
		return getRelatonsByCollaboratorAndTypeAndRole( ownerUid, type, RelationshipRole.OWNER );
	}

	public List<CompanyProduct> getRelationsByProduct( String productUid )
	{
		return getRelatonsByCollaboratorAndTypeAndRole( productUid, type, RelationshipRole.PRODUCT );
	}

	public CompanyProduct getRelationsByCompanyAndProduct( String companyUid, String productUid )
	{
		List<String> objectUids = new ArrayList<>();
		objectUids.add( companyUid );
		objectUids.add( productUid );
		return getRelationsByCollaboratorsAndType( objectUids, type );
	}

	@Override
	protected void createIndex( CompanyProduct model )
	{
		deleteIndex( model.getUid() );
		saveIndexData( model.getUid(), IndexType.OWNER, model.retrieveOwner().getObject().getUid() );
		saveIndexData( model.getUid(), IndexType.PRODUCT, model.retrieveProduct().getObject().getUid() );
		// TODO: create field indices...
	}

	@Override
	protected void validate( CompanyProduct model ) throws ServiceException
	{
		RoleData owner = model.retrieveOwner();
		if ( owner == null )
		{
			throw ErrorCode.PRODUCT_OWNER_MISSING.exception;
		}
		
		if ( owner.getObject() == null )
		{
			throw ErrorCode.PRODUCT_OWNER_MISSING.exception;
		}
		
		RoleData product = model.retrieveProduct();
		if ( product == null )
		{
			throw ErrorCode.PRODUCT_MISSING.exception;
		}
		
		if ( product.getObject() == null )
		{
			throw ErrorCode.PRODUCT_MISSING.exception;
		}
	}

}
