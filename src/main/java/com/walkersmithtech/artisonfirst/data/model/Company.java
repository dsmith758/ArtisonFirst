package com.walkersmithtech.artisonfirst.data.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.data.model.relation.CompanyLocation;

@JsonInclude( Include.NON_EMPTY )
public class Company extends BaseModel
{

	private String companyName;
	private String businessType;
	private String logoUri;
	private List<CompanyLocation> address;

	public void initType()
	{
		this.type = DataType.COMPANY.name();
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName( String companyName )
	{
		this.companyName = companyName;
	}

	public String getBusinessType()
	{
		return businessType;
	}

	public void setBusinessType( String businessType )
	{
		this.businessType = businessType;
	}

	public String getLogoUri()
	{
		return logoUri;
	}

	public void setLogoUri( String logoUri )
	{
		this.logoUri = logoUri;
	}

	public List<CompanyLocation> getAddress()
	{
		return address;
	}

	public void setAddresses( List<CompanyLocation> address )
	{
		this.address = address;
	}

	public void addAddress( CompanyLocation address )
	{
		if ( this.address == null )
		{
			this.address = new ArrayList<CompanyLocation>();
		}
		if ( address != null )
		{
			this.address.add( address );
		}
	}

}
