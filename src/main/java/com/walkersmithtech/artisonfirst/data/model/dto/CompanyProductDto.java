package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.data.model.BaseDto;
import com.walkersmithtech.artisonfirst.data.model.object.Company;

@JsonInclude( Include.NON_EMPTY )
public class CompanyProductDto extends BaseDto
{
	private Company company;
	private List<ProductDto> products;

	public Company getCompany()
	{
		return company;
	}

	public void setCompany( Company company )
	{
		this.company = company;
	}

	public List<ProductDto> getProducts()
	{
		return products;
	}

	public void setProducts( List<ProductDto> products )
	{
		this.products = products;
	}
}
