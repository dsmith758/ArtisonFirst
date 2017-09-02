package com.walkersmithtech.artisonfirst.data.model.object;

import java.util.List;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.data.model.BaseObject;

public class InventoryItem extends BaseObject
{
	private String name;
	private String quantity;
	private String unitOfMeasure;
	private List<Field> fields;
	private String companyId;

	@Override
	public void initType()
	{
		this.type = DataType.INVENTORY_ITEM.name();
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getQuantity()
	{
		return quantity;
	}

	public void setQuantity( String quantity )
	{
		this.quantity = quantity;
	}

	public String getUnitOfMeasure()
	{
		return unitOfMeasure;
	}

	public void setUnitOfMeasure( String unitOfMeasure )
	{
		this.unitOfMeasure = unitOfMeasure;
	}

	public List<Field> getFields()
	{
		return fields;
	}

	public void setFields( List<Field> fields )
	{
		this.fields = fields;
	}

	public String getCompanyId()
	{
		return companyId;
	}

	public void setCompanyId( String companyId )
	{
		this.companyId = companyId;
	}

}
