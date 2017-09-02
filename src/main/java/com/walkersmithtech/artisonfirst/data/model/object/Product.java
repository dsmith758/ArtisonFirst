package com.walkersmithtech.artisonfirst.data.model.object;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.data.model.BaseObject;

public class Product extends BaseObject
{
	private String name;
	private String description;
	private String imageUri;
	private Boolean verified;
	private String status;

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

	public String getImageUri()
	{
		return imageUri;
	}

	public void setImageUri( String imageUri )
	{
		this.imageUri = imageUri;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus( String status )
	{
		this.status = status;
	}

}
