package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.data.model.BaseList;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Product;

@JsonInclude( Include.NON_EMPTY )
public class CompanyProductsDto extends BaseList<Product>
{
	private Company company;

	public Company getCompany()
	{
		return company;
	}

	public void setCompany( Company company )
	{
		this.company = company;
	}

	@JsonIgnore
	public List<Product> getProducts()
	{
		return list;
	}

	@JsonIgnore
	public void setProducts( List<Product> products )
	{
		this.list = products;
	}
}
