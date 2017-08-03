package com.walkersmithtech.artisonfirst.component.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.component.BaseRelationService;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Product;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyProduct;

@Service
public class CompanyProductService extends BaseRelationService<CompanyProduct>
{

	public CompanyProductService()
	{
		roleType = RelationshipRole.COMPANY_PRODUCT.name();
		relationClass = CompanyProduct.class;
		sourceClass = Company.class;
		targetClass = Product.class;
	}
	
	public void createCompanyProducts( List<Product> products, Company company )
	{
		if ( products != null && products.size() > 0 )
		{
			CompanyProduct companyProduct;
			for ( Product product : products )
			{
				companyProduct = new CompanyProduct();
				companyProduct.setSourceUid( company.getUid() );
				companyProduct.setTargetUid( product.getUid() );
				companyProduct = createModel( companyProduct );
			}
		}
	}

	@Override
	public CompanyProduct createModel( CompanyProduct relation )
	{
		return saveRelationship( relation );
	}

	@Override
	public CompanyProduct updateModel( CompanyProduct relation )
	{
		return saveRelationship( relation );
	}

	public List<CompanyProduct> getCompanyProductsByCompanyUid( String uid )
	{
		return getRelationsBySourceAndType( uid, RelationshipRole.COMPANY_PRODUCT );
	}

	public List<CompanyProduct> getCompanyProductsByProductUid( String uid )
	{
		return getRelationsByTargetAndType( uid, RelationshipRole.COMPANY_PRODUCT );
	}
	
	public CompanyProduct getCompanyProductBySourceAndTarget( String sourceUid, String targetUid )
	{
		return getRelationsBySourceAndTargetAndType( sourceUid, targetUid, RelationshipRole.COMPANY_PRODUCT );
	}

}
