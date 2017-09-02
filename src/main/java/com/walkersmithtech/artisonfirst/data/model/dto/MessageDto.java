package com.walkersmithtech.artisonfirst.data.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.walkersmithtech.artisonfirst.data.model.BaseDto;
import com.walkersmithtech.artisonfirst.data.model.object.File;
import com.walkersmithtech.artisonfirst.data.model.object.Message;
import com.walkersmithtech.artisonfirst.data.model.relation.MessagePerson;

public class MessageDto extends BaseDto
{
	private Message message;
	private MessagePerson recipients;
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

	public Message getMessage()
	{
		return message;
	}

	public void setMessage( Message message )
	{
		this.message = message;
	}

	public MessagePerson getRecipients()
	{
		return recipients;
	}

	public void setRecipients( MessagePerson recipients )
	{
		this.recipients = recipients;
	}
}
