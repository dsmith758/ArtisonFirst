package com.walkersmithtech.artisonfirst.data.model.object;

import java.util.ArrayList;
import java.util.List;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.data.model.BaseObject;

public class Product extends BaseObject
{
	private String name;
	private String itemNumber;
	private String description;
	private List<FieldValue> fields;
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
		if ( imageUri == null )
		{
			return "image/system/product.png";
		}
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

	public List<FieldValue> getFields()
	{
		if ( fields == null )
		{
			fields = new ArrayList<>();
		}
		return fields;
	}

	public void setFields( List<FieldValue> fields )
	{
		this.fields = fields;
	}

	public void addField( FieldValue field )
	{
		getFields();
		if ( field != null )
		{
			fields.add( field );
		}
	}

	public String getItemNumber()
	{
		return itemNumber;
	}

	public void setItemNumber( String itemNumber )
	{
		this.itemNumber = itemNumber;
	}

}
