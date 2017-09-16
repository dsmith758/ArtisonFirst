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
import com.walkersmithtech.artisonfirst.data.model.dto.CompanyProductsDto;
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
		Product product = productService.createProduct( model.getProduct() );
		Company company = companyService.getCompanyByUid( model.getCompany().getUid() );
		
		CompanyProduct companyProduct = new CompanyProduct();
		companyProduct.addProduct( product );
		companyProduct.addOwner( company );
		companyProduct = companyProductService.createOrUpdateCompanyProduct( companyProduct );
		
		return buildProductDto( product, company, model );
	}

	public ProductDto updateProduct( ProductDto model ) throws ServiceException
	{
		Product product = productService.updateProduct( model.getProduct() );
		Company company = companyService.getCompanyByUid( model.getCompany().getUid() );
		
		CompanyProduct companyProduct = new CompanyProduct();
		companyProduct.addProduct( product );
		companyProduct.addOwner( company );
		companyProduct = companyProductService.createOrUpdateCompanyProduct( companyProduct );
		
		return buildProductDto( product, company, model );
	}

	public ProductDto getProductByUid( ProductDto model, String productUid ) throws ServiceException
	{
		Company company = getCompany( model );
		Product product = productService.getProductByUid( productUid );
		return buildProductDto( product, company, model );
	}

	public ProductDto getCompanyProduct( String productUid, String companyUid, ProductDto model ) throws ServiceException
	{
		Company company = companyService.getCompanyByUid( companyUid );
		Product product = productService.getProductByUid( productUid );
		return buildProductDto( product, company, model );
	}
	
	private Company getCompany( ProductDto model ) throws ServiceException
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

	public CompanyProductsDto getCompanyProducts( String companyUid, CompanyProductsDto auth ) throws ServiceException
	{
		Company company = companyService.getCompanyByUid( companyUid );
		List<Product> products = new ArrayList<>();
		Product product;
		ProductDto model;
		auth.setCompany( company );

		List<CompanyProduct> companyProducts = companyProductService.getRelationsByCompany( companyUid );
		if ( companyProducts != null && companyProducts.size() > 0 )
		{
			RoleData productRole;
			for ( CompanyProduct companyProduct : companyProducts )
			{
				productRole = companyProduct.getCollaborator( RelationshipRole.PRODUCT.name() );
				if ( productRole != null )
				{
					model = new ProductDto();
					product = productService.getProductByUid( productRole.getObjectUid() );
					if ( product != null )
					{
						model = buildProductDto( product, company, model );
						products.add( model.getProduct() );
					}
				}
			}
		}
		auth.setProducts( products );
		return auth;
	}

	private ProductDto buildProductDto( Product product, Company company, ProductDto model ) throws ServiceException
	{
		CompanyProduct companyProduct = companyProductService.getRelationsByCompanyAndProduct( company.getUid(), product.getUid() );
		if ( companyProduct == null )
		{
			throw ErrorCode.PRODUCT_COMPANY_MISMATCH.exception;
		}
		model.setProduct( product );
		model.setCompany( company );
		return model;
	}

}
