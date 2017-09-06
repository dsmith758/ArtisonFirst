package com.walkersmithtech.artisonfirst.data.model.object;

import java.util.Date;

import com.walkersmithtech.artisonfirst.data.model.BaseObject;

public class Message extends BaseObject
{
	private String subject;
	private String body;
	private Date sentOn;

	public String getSubject()
	{
		return subject;
	}

	public void setSubject( String subject )
	{
		this.subject = subject;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody( String body )
	{
		this.body = body;
	}

	public Date getSentOn()
	{
		return sentOn;
	}

	public void setSentOn( Date sentOn )
	{
		this.sentOn = sentOn;
	}

}
