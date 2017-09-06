package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.walkersmithtech.artisonfirst.data.model.BaseDto;
import com.walkersmithtech.artisonfirst.data.model.object.File;
import com.walkersmithtech.artisonfirst.data.model.object.Person;

public class MessageDto extends BaseDto
{
	private String uid;
	private String subject;
	private String body;
	private Date sentOn;
	private Person sender;
	private List<Person> recipients;
	private List<Person> ccRecipients;
	private List<Person> bccRecipients;
	private List<File> attachments;
	
	public void addAttachment( File file )
	{
		getAttachments();
		attachments.add( file );
	}

	public List<File> getAttachments()
	{
		if ( attachments == null )
		{
			attachments = new ArrayList<>();
		}
		return attachments;
	}

	public void setAttachments( List<File> attachments )
	{
		this.attachments = attachments;
	}

	public Person getSender()
	{
		return sender;
	}

	public void setSender( Person sender )
	{
		this.sender = sender;
	}

	public List<Person> getBccRecipients()
	{
		if ( bccRecipients == null )
		{
			bccRecipients = new ArrayList<>();
		}
		return bccRecipients;
	}

	public void setBccRecipients( List<Person> bccRecipients )
	{
		this.bccRecipients = bccRecipients;
	}
	
	public void addBccRecipient( Person bccRecipient )
	{
		getRecipients();
		bccRecipients.add( bccRecipient );
	}

	public List<Person> getCcRecipients()
	{
		if ( ccRecipients == null )
		{
			ccRecipients = new ArrayList<>();
		}
		return ccRecipients;
	}

	public void setCcRecipients( List<Person> ccRecipients )
	{
		this.ccRecipients = ccRecipients;
	}
	
	public void addCcRecipient( Person ccRecipient )
	{
		getRecipients();
		ccRecipients.add( ccRecipient );
	}

	public List<Person> getRecipients()
	{
		if ( recipients == null )
		{
			recipients = new ArrayList<>();
		}
		return recipients;
	}

	public void setRecipients( List<Person> recipients )
	{
		this.recipients = recipients;
	}
	
	public void addRecipient( Person recipient )
	{
		getRecipients();
		recipients.add( recipient );
	}

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

	public String getUid()
	{
		return uid;
	}

	public void setUid( String uid )
	{
		this.uid = uid;
	}

}
