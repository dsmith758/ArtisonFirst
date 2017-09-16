package com.walkersmithtech.artisonfirst.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.core.BaseObjectService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.model.object.Product;

@Service
public class ProductService extends BaseObjectService<Product>
{

	@Autowired
	private PersonImageService imageService;

	public ProductService()
	{
		dataType = DataType.PRODUCT;
		modelClass = Product.class;
	}
	
	public Product saveProduct( Product model ) throws ServiceException
	{
		if (model.getUid() == null )
		{
			return createProduct( model );
		}
		return updateProduct( model );
	}

	public Product createProduct( Product model ) throws ServiceException
	{
		validate( model );
		return createModel( model );
	}

	public Product updateProduct( Product model ) throws ServiceException
	{
		validate( model );
		Product match = getModelByUid( model.getUid() );
		if ( match == null )
		{
			throw ErrorCode.PRODUCT_NOT_FOUND.exception;
		}
		return updateModel( model );
	}

	public void deleteProduct( String uid ) throws ServiceException
	{
		imageService.removeObjectImages( uid );
		deleteModel( uid );
	}

	@Override
	protected void createIndex( Product model )
	{
		indexRepo.deleteByUid( model.getUid() );
		saveIndexData( model.getUid(), IndexType.PRODUCT_NAME, model.getName() );
	}

	public Product getProductByUid( String uid ) throws ServiceException
	{
		Product model = getModelByUid( uid );
		if ( model == null )
		{
			throw ErrorCode.PRODUCT_NOT_FOUND.exception;
		}
		return model;
	}
	
	public List<Product> getProductsByOwnerAndName( String companyUid, String name ) throws ServiceException
	{
		return getProductsByOwnerAndIndex( companyUid, IndexType.PRODUCT_NAME, name );
	}
	
	private List<Product> getProductsByOwnerAndIndex( String sourceUid, IndexType type, String value ) throws ServiceException
	{
		List<Product> products = getModelsByIndexAndRoles( sourceUid, RelationshipRole.OWNER, RelationshipRole.PRODUCT, type, value );
		return products;
	}

	@Override
	protected void validate( Product model ) throws ServiceException
	{
		if ( model == null )
		{
			throw ErrorCode.PRODUCT_RECORD_NULL.exception;
		}

		if ( model.getDescription() == null )
		{
			throw ErrorCode.PRODUCT_DESCRIPTION_MISSING.exception;
		}
	}

}
