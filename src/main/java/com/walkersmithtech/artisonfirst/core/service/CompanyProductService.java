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
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyProduct;

@Service
public class CompanyProductService extends BaseRelationService<CompanyProduct>
{

	public CompanyProductService()
	{
		type = RelationshipType.COMPANY_PRODUCT;
		modelClass = CompanyProduct.class;
	}
	
	public CompanyProduct createProductOwner( CompanyProduct productOwner ) throws ServiceException
	{
		validate( productOwner );
		RoleData owner = productOwner.retrieveProduct();
		RoleData product = productOwner.retrieveProduct();
		CompanyProduct match = getRelationsByCompanyAndProduct( owner.getObjectUid(), product.getObjectUid() );
		if ( match != null )
		{
			updateProductOwner( productOwner );
		}
		productOwner = createModel( productOwner );
		return productOwner;
	}
	
	public CompanyProduct updateProductOwner( CompanyProduct productOwner ) throws ServiceException
	{
		validate( productOwner );
		productOwner = updateModel( productOwner );
		return productOwner;
	}

	public List<CompanyProduct> getRelationsByOwner( String ownerUid )
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
