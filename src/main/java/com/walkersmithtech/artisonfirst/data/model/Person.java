package com.walkersmithtech.artisonfirst.data.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.data.model.fragment.ContactInfo;

@JsonInclude( Include.NON_EMPTY )
public class Person extends BaseModel
{
	private String firstName;
	private String middleName;
	private String lastName;
	private String personalExperience;
	private String imageUri;
	private List<ContactInfo> contactInfo;

	public void initType()
	{
		this.type = DataType.PERSON.name();
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName( String middleName )
	{
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}

	public String getPersonalExperience()
	{
		return personalExperience;
	}

	public void setPersonalExperience( String personalExperience )
	{
		this.personalExperience = personalExperience;
	}

	public List<ContactInfo> getContactInfo()
	{
		return contactInfo;
	}

	public void setContactInfo( List<ContactInfo> contactInfo )
	{
		this.contactInfo = contactInfo;
	}

	public String getImageUri()
	{
		if ( imageUri == null )
		{
			return "image/system/blank-profile-hi.png";
		}
		return imageUri;
	}

	public void setImageUri( String imageUri )
	{
		this.imageUri = imageUri;
	}
}
