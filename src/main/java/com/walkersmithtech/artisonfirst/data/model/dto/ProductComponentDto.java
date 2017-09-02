package com.walkersmithtech.artisonfirst.data.model.dto;

import com.walkersmithtech.artisonfirst.data.model.BaseDto;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Product;

public class ProductComponentDto extends BaseDto
{
	private Product product;
	private Company company;
	private Float quantity;
	private String unit;

	public Product getProduct()
	{
		return product;
	}

	public void setProduct( Product product )
	{
		this.product = product;
	}

	public Company getCompany()
	{
		return company;
	}

	public void setCompany( Company company )
	{
		this.company = company;
	}

	public Float getQuantity()
	{
		return quantity;
	}

	public void setQuantity( Float quantity )
	{
		this.quantity = quantity;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit( String unit )
	{
		this.unit = unit;
	}

}
