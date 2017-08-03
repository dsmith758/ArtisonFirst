package com.walkersmithtech.artisonfirst.data.model.dto;

import com.walkersmithtech.artisonfirst.data.model.BaseDto;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Product;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyProduct;

public class ProductDto extends BaseDto
{
	private Product product;
	private Company company;
	private CompanyProduct profile;

	public Product getProduct()
	{
		return product;
	}

	public void setProduct( Product product )
	{
		this.product = product;
	}

	public CompanyProduct getProfile()
	{
		return profile;
	}

	public void setProfile( CompanyProduct profile )
	{
		this.profile = profile;
	}

	public Company getCompany()
	{
		return company;
	}

	public void setCompany( Company company )
	{
		this.company = company;
	}

}
