package com.walkersmithtech.artisonfirst.component.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.component.BaseObjectService;
import com.walkersmithtech.artisonfirst.component.ServiceException;
import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.ErrorCode;
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

	public Product createProduct( Product model ) throws ServiceException
	{
		validateModel( model );
		return createModel( model );
	}

	public Product updateProduct( Product model ) throws ServiceException
	{
		validateModel( model );
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
	public Product createModel( Product model )
	{
		if ( model != null )
		{
			model = createData( model );
			createIndex( model );
		}
		return model;
	}

	@Override
	public Product updateModel( Product model )
	{
		if ( model != null )
		{
			model = updateData( model );
			createIndex( model );
		}
		return model;
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

	private void createIndex( Product model )
	{
		indexRepo.deleteByUid( model.getUid() );
	}

	private void validateModel( Product model ) throws ServiceException
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
