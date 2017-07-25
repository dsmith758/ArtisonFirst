package com.walkersmithtech.artisonfirst.data.request;

import com.walkersmithtech.artisonfirst.data.model.Location;

public class AddressLocation extends Location
{
	protected String addressType;

	public String getAddressType()
	{
		return addressType;
	}

	public void setAddressType( String addressType )
	{
		this.addressType = addressType;
	}
}
