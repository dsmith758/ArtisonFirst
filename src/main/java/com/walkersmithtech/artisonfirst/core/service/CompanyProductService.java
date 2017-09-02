package com.walkersmithtech.artisonfirst.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.BaseRelationService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.model.fragment.ProductFieldDefinition;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyProduct;

@Service
public class CompanyProductService extends BaseRelationService<CompanyProduct>
{

	public CompanyProductService()
	{
		type = RelationshipType.COMPANY_PRODUCT;
		modelClass = CompanyProduct.class;
	}

	@Override
	protected void createIndex( CompanyProduct model )
	{
		deleteIndex( model.getUid() );
		List<ProductFieldDefinition> fields = model.getFields();
		if ( fields != null && fields.size() > 0 )
		{
			for ( ProductFieldDefinition field : fields )
			{
				if ( field.getValues() != null && field.getValues().size() > 0 )
				{
					for ( String value : field.getValues() )
					{
						saveIndexData( model.getUid(), field.getLabel().toUpperCase(), value );
					}
				}
			}
		}
	}

	public List<CompanyProduct> getCompanyProductsByCompanyUid( String uid )
	{
		return getRelationsBySourceAndType( uid, type, RelationshipRole.OWNER );
	}

	public List<CompanyProduct> getCompanyProductsByProductUid( String uid )
	{
		return getRelationsBySourceAndType( uid, type, RelationshipRole.PRODUCT );
	}

	public CompanyProduct getCompanyProductBySourceAndTarget( String sourceUid, String targetUid )
	{
		List<String> objectUids = new ArrayList<>();
		objectUids.add( sourceUid );
		objectUids.add( targetUid );
		return getRelationsBySourceAndTargetAndType( objectUids, type );
	}

	@Override
	protected void validate( CompanyProduct model ) throws ServiceException
	{
	}

}
