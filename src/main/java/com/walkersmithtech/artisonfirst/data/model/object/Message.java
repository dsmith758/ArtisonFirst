package com.walkersmithtech.artisonfirst.data.model.object;

import com.walkersmithtech.artisonfirst.data.model.BaseObject;

public class Message extends BaseObject
{
	private String title;
	private String message;


	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage( String message )
	{
		this.message = message;
	}
	
}
