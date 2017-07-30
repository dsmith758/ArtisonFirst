package com.walkersmithtech.artisonfirst.data.model;

import com.walkersmithtech.artisonfirst.constant.DataType;

public class Product extends BaseObject
{
	private String productName;

	@Override
	public void initType()
	{
		this.type = DataType.PRODUCT.name();
	}

}
