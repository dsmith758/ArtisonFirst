package com.walkersmithtech.artisonfirst.component.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.component.BaseBuilder;
import com.walkersmithtech.artisonfirst.component.ServiceException;
import com.walkersmithtech.artisonfirst.component.service.CompanyProductService;
import com.walkersmithtech.artisonfirst.component.service.CompanyService;
import com.walkersmithtech.artisonfirst.component.service.ProductService;
import com.walkersmithtech.artisonfirst.constant.ErrorCode;
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

	public ProductDto createProduct( ProductDto model ) throws ServiceException
	{
		Company company = companyService.getCompanyByUid( model.getCompany().getUid() );
		Product product = productService.createProduct( model.getProduct() );
		model.setProduct( product );
		model.setCompany( company );
		CompanyProduct profile = model.getProfile();
		profile.setSourceUid( company.getUid() );
		profile.setTargetUid( product.getUid() );
		profile = companyProductService.createModel( profile );
		model.setProfile( profile );
		return model;
	}

	public ProductDto updateProduct( ProductDto model ) throws ServiceException
	{
		Company company = companyService.getCompanyByUid( model.getCompany().getUid() );
		Product product = productService.updateProduct( model.getProduct() );
		model.setProduct( product );
		model.setCompany( company );
		CompanyProduct profile = model.getProfile();
		profile.setSourceUid( company.getUid() );
		profile.setTargetUid( product.getUid() );
		profile = companyProductService.updateModel( profile );
		model.setProfile( profile );
		return model;
	}

	public ProductDto getProductByUid( String productUid, ProductDto model ) throws ServiceException
	{
		Company company = companyService.getCompanyByPersonUid( model.getAccount().getPersonUid() );
		Product product = productService.getProductByUid( productUid );
		return buildProductDto( product, company, model );
	}

	public ProductDto getCompanyProduct( String productUid, String companyUid, ProductDto model ) throws ServiceException
	{
		Company company = companyService.getCompanyByPersonUid( companyUid );
		Product product = productService.getProductByUid( productUid );
		return buildProductDto( product, company, model );
	}

	public CompanyProductDto getCompanyProducts( String companyUid, CompanyProductDto auth ) throws ServiceException
	{
		Company company = companyService.getCompanyByUid( companyUid );
		List<ProductDto> products = new ArrayList<>();
		Product product;
		ProductDto model;
		auth.setCompany( company );

		List<CompanyProduct> profiles = companyProductService.getCompanyProductsByCompanyUid( companyUid );
		if ( profiles != null && profiles.size() > 0 )
		{
			for ( CompanyProduct profile : profiles )
			{
				model = new ProductDto();
				product = productService.getProductByUid( profile.getTargetUid() );
				if ( product != null )
				{
					products.add( populateProductDto( product, company, profile, model ) );
				}
			}
		}
		auth.setProducts( products );
		return auth;
	}

	private ProductDto buildProductDto( Product product, Company company, ProductDto model ) throws ServiceException
	{
		CompanyProduct profile = companyProductService.getCompanyProductBySourceAndTarget( company.getUid(), product.getUid() );
		if ( profile == null )
		{
			throw ErrorCode.PRODUCT_COMPANY_MISMATCH.exception;
		}
		return populateProductDto( product, company, profile, model );
	}
	
	private ProductDto populateProductDto( Product product, Company company, CompanyProduct profile, ProductDto model )
	{
		model.setProduct( product );
		model.setCompany( company );
		model.setProfile( profile );
		return model;
	}

}
