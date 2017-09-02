package com.walkersmithtech.artisonfirst.core.service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.core.BaseObjectService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.model.object.Field;

public class FieldService extends BaseObjectService<Field>
{

	public Field createProduct( Field model ) throws ServiceException
	{
		validate( model );
		return createModel( model );
	}

	public Field updateProduct( Field model ) throws ServiceException
	{
		validate( model );
		Field match = getModelByUid( model.getUid() );
		if ( match == null )
		{
			throw ErrorCode.PRODUCT_NOT_FOUND.exception;
		}
		return updateModel( model );
	}

	@Override
	protected void createIndex( Field model )
	{
		deleteIndex( model.getUid() );
	}

	public Field getProductByUid( String uid ) throws ServiceException
	{
		Field model = getModelByUid( uid );
		if ( model == null )
		{
			throw ErrorCode.FIELD_NOT_FOUND.exception;
		}
		return model;
	}

	protected void validate( Field model ) throws ServiceException
	{
		if ( model == null )
		{
			throw ErrorCode.FIELD_RECORD_NULL.exception;
		}

		if ( model.getLabel() == null )
		{
			throw ErrorCode.FIELD_LABEL_MISSING.exception;
		}
	}

}
