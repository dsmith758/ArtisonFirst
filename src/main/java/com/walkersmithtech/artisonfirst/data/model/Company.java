package com.walkersmithtech.artisonfirst.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.DataType;

@JsonInclude( Include.NON_EMPTY )
public class Company extends BaseObject
{

	private String companyName;
	private String businessType;
	private String logoUri;

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
		if ( logoUri == null )
		{
			return "image/system/blank-business-logo.png";
		}
		return logoUri;
	}

	public void setLogoUri( String logoUri )
	{
		this.logoUri = logoUri;
	}

}
