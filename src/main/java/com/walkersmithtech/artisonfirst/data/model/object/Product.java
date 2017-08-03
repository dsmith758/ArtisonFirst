package com.walkersmithtech.artisonfirst.data.model.object;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.data.model.BaseObject;

public class Product extends BaseObject
{
	private String description;
	private Boolean verified;

	@Override
	public void initType()
	{
		this.type = DataType.PRODUCT.name();
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public Boolean getVerified()
	{
		return verified;
	}

	public void setVerified( Boolean verified )
	{
		this.verified = verified;
	}

}
