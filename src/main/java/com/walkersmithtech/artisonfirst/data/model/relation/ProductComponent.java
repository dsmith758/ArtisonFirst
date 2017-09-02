package com.walkersmithtech.artisonfirst.data.model.relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.model.BaseRelation;

@JsonInclude( Include.NON_EMPTY )
public class ProductComponent extends BaseRelation
{
	private Float quantity;
	private String unit;

	public ProductComponent()
	{
		this.type = RelationshipType.PRODUCT_COMPONENT.name();
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
