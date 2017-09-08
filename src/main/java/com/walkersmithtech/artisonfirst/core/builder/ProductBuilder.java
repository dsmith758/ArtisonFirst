package com.walkersmithtech.artisonfirst.core.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.core.BaseBuilder;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.core.service.CompanyProductService;
import com.walkersmithtech.artisonfirst.core.service.CompanyService;
import com.walkersmithtech.artisonfirst.core.service.ProductService;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.dto.CompanyProductDto;
import com.walkersmithtech.artisonfirst.data.model.dto.ProductDto;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Product;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyProduct;

@Service
public class ProductBuilder extends BaseBuilder<ProductDto>
{
	@Autowired
	private ProductService productService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyProductService companyProductService;

	public List<ProductDto> createProducts( List<ProductDto> models ) throws ServiceException
	{
		if ( models != null && models.size() > 0 )
		{
			for ( ProductDto model : models )
			{
				model = createProduct( model );
			}
		}
		return models;
	}

	public ProductDto createProduct( ProductDto model ) throws ServiceException
	{
		model = buildProductDto( model );
		CompanyProduct profile = buildProfile( model );
		profile = companyProductService.createModel( profile );
		return model;
	}

	public ProductDto updateProduct( ProductDto model ) throws ServiceException
	{
		model = buildProductDto( model );
		CompanyProduct profile = buildProfile( model );
		profile = companyProductService.updateModel( profile );
		return model;
	}

	public ProductDto getProductByUid( ProductDto model ) throws ServiceException
	{
		String productUid = model.getProduct().getUid();
		Company company = getPersonCompany( model );
		Product product = productService.getProductByUid( productUid );
		return buildProductDto( product, company, model );
	}

	public ProductDto getCompanyProduct( String productUid, String companyUid, ProductDto model ) throws ServiceException
	{
		Company company = getPersonCompany( model );
		Product product = productService.getProductByUid( productUid );
		return buildProductDto( product, company, model );
	}
	
	private Company getPersonCompany( ProductDto model ) throws ServiceException
	{
		String companyUid = model.getAccount().getCompanyUid();
		Company company = null;
		if ( companyUid == null )
		{
			company = companyService.getDefaultCompanyByPersonUid( model.getAccount().getPersonUid() );
		}
		else
		{
			company = companyService.getCompanyByUid( companyUid );
		}
		return company;
	}

	public CompanyProductDto getCompanyProducts( String companyUid, CompanyProductDto auth ) throws ServiceException
	{
		Company company = companyService.getCompanyByUid( companyUid );
		List<ProductDto> products = new ArrayList<>();
		Product product;
		ProductDto model;
		auth.setCompany( company );

		List<CompanyProduct> profiles = companyProductService.getRelationsByOwner( companyUid );
		if ( profiles != null && profiles.size() > 0 )
		{
			RoleData productRole;
			for ( CompanyProduct profile : profiles )
			{
				productRole = profile.getCollaborator( RelationshipRole.PRODUCT.name() );
				if ( productRole != null )
				{
					model = new ProductDto();
					product = productService.getProductByUid( productRole.getObjectUid() );
					if ( product != null )
					{
						products.add( populateProductDto( product, company, profile, model ) );
					}
				}
			}
		}
		auth.setProducts( products );
		return auth;
	}

	private ProductDto buildProductDto( ProductDto model ) throws ServiceException
	{
		Company company = companyService.getCompanyByUid( model.getCompany().getUid() );
		Product product = productService.saveProduct( model.getProduct() );
		CompanyProduct profile = buildProfile( model );
		return populateProductDto( product, company, profile, model );
	}

	private ProductDto buildProductDto( Product product, Company company, ProductDto model ) throws ServiceException
	{
		CompanyProduct profile = companyProductService.getRelationsByCompanyAndProduct( company.getUid(), product.getUid() );
		if ( profile == null )
		{
			throw ErrorCode.PRODUCT_COMPANY_MISMATCH.exception;
		}
		return populateProductDto( product, company, profile, model );
	}

	private CompanyProduct buildProfile( ProductDto model )
	{
		Company company = model.getCompany();
		Product product = model.getProduct();
		
		CompanyProduct profile = new CompanyProduct();
		profile.addProduct( product );
		profile.addOwner( company );
		return profile;
	}

	private ProductDto populateProductDto( Product product, Company company, CompanyProduct profile, ProductDto model )
	{
		model.setProduct( product );
		model.setCompany( company );
		return model;
	}

}
