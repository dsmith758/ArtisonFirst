package com.walkersmithtech.artisonfirst.data.model.object;

import java.util.Date;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.data.model.BaseObject;

public class ProductUnit extends BaseObject
{
	private String productUid;
	private String refId;
	private Date finishDate;
	private Date shippedDate;
	private String status;

	@Override
	public void initType()
	{
		this.type = DataType.PRODUCT.name();
	}

	public String getRefId()
	{
		return refId;
	}

	public void setRefId( String refId )
	{
		this.refId = refId;
	}

	public Date getFinishDate()
	{
		return finishDate;
	}

	public void setFinishDate( Date finishDate )
	{
		this.finishDate = finishDate;
	}

	public Date getShippedDate()
	{
		return shippedDate;
	}

	public void setShippedDate( Date shippedDate )
	{
		this.shippedDate = shippedDate;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus( String status )
	{
		this.status = status;
	}

	public String getProductUid()
	{
		return productUid;
	}

	public void setProductUid( String productUid )
	{
		this.productUid = productUid;
	}
}
