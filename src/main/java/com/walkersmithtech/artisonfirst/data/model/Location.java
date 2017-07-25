package com.walkersmithtech.artisonfirst.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.DataType;

@JsonInclude( Include.NON_EMPTY )
public class Location extends BaseModel
{

	private String addressName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String zip;
	private String longitude;
	private String latitude;

	public void initType()
	{
		type = DataType.LOCATION.name();
	}

	public String getAddress1()
	{
		return address1;
	}

	public void setAddress1( String address1 )
	{
		this.address1 = address1;
	}

	public String getAddress2()
	{
		return address2;
	}

	public void setAddress2( String address2 )
	{
		this.address2 = address2;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity( String city )
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState( String state )
	{
		this.state = state;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry( String country )
	{
		this.country = country;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip( String zip )
	{
		this.zip = zip;
	}

	public String getLongitude()
	{
		return longitude;
	}

	public void setLongitude( String longitude )
	{
		this.longitude = longitude;
	}

	public String getLatitude()
	{
		return latitude;
	}

	public void setLatitude( String latitude )
	{
		this.latitude = latitude;
	}

	public String getAddressName()
	{
		return addressName;
	}

	public void setAddressName( String addressName )
	{
		this.addressName = addressName;
	}

}
