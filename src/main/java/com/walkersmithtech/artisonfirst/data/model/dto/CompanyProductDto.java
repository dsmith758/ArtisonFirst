package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.data.model.BaseList;
import com.walkersmithtech.artisonfirst.data.model.object.Company;

@JsonInclude( Include.NON_EMPTY )
public class CompanyProductDto extends BaseList<ProductDto>
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
	public List<ProductDto> getProducts()
	{
		return list;
	}

	@JsonIgnore
	public void setProducts( List<ProductDto> products )
	{
		this.list = products;
	}
}
