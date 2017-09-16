package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.walkersmithtech.artisonfirst.data.model.BaseList;
import com.walkersmithtech.artisonfirst.data.model.object.Product;

public class ProductCompaniesDto extends BaseList<OrganizationDto>
{
	private Product product;

	public Product getProduct()
	{
		return product;
	}

	public void setProduct( Product product )
	{
		this.product = product;
	}
	
	@JsonIgnore
	public List<OrganizationDto> getOwners()
	{
		return list;
	}

	@JsonIgnore
	public void setOwners( List<OrganizationDto> owners )
	{
		this.list = owners;
	}
}
