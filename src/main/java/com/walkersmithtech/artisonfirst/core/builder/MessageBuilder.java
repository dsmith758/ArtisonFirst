package com.walkersmithtech.artisonfirst.core.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.walkersmithtech.artisonfirst.core.BaseBuilder;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.core.service.MessageRecipientService;
import com.walkersmithtech.artisonfirst.core.service.MessageSenderService;
import com.walkersmithtech.artisonfirst.core.service.MessageService;
import com.walkersmithtech.artisonfirst.data.model.dto.MessageDto;
import com.walkersmithtech.artisonfirst.data.model.object.Message;
import com.walkersmithtech.artisonfirst.data.model.object.Person;
import com.walkersmithtech.artisonfirst.data.model.relation.MessageRecipient;
import com.walkersmithtech.artisonfirst.data.model.relation.MessageSender;
import com.walkersmithtech.artisonfirst.util.DateUtil;

public class MessageBuilder extends BaseBuilder<MessageDto>
{

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageSenderService senderService;

	@Autowired
	private MessageRecipientService recipientService;

	public MessageDto createMessage( MessageDto model ) throws ServiceException
	{
		Message message = new Message();
		message.setBody( model.getBody() );
		message.setSubject( model.getSubject() );
		message.setSentOn( DateUtil.getCurrentDate() );
		message = messageService.createMessage( message );

		buildSender( model.getSender(), message );
		buildRecipients( model.getRecipients(), message );
		model = buildMessageDto( message );
		return model;
	}
	
	public MessageDto updateMessage( MessageDto model ) throws ServiceException
	{
		Message message = new Message();
		message.setUid( model.getUid() );
		message.setBody( model.getBody() );
		message.setSubject( model.getSubject() );
		message.setSentOn( DateUtil.getCurrentDate() );
		message = messageService.updateMessage( message );

		buildRecipients( model.getRecipients(), message );
		model = buildMessageDto( message );
		return model;
	}
	
	public MessageDto openMessage( String messageUid, String recipientUid ) throws ServiceException
	{
		return null;
	}
	
	public MessageDto getMessage( String messageUid ) throws ServiceException
	{
		return null;
	}
	
	private MessageDto buildMessageDto( Message entity )
	{
		return null;
	}
	
	private MessageSender buildSender( Person person, Message message )
	{
		MessageSender sender = new MessageSender();
		sender.addMessage( message );
		sender.addSender( person );
		senderService.createModel( sender );
		return sender;
	}
	
	private List<MessageRecipient> buildRecipients( List<Person> persons, Message message )
	{
		List<MessageRecipient> recipients = new ArrayList<>();
		if ( persons != null && persons.size() > 0 )
		{
			MessageRecipient recipient;
			for ( Person person : persons )
			{
				recipient = new MessageRecipient();
				recipient.addMessage( message );
				recipient.addRecipient( person );
				recipient = recipientService.createModel( recipient );
				recipients.add( recipient );
			}
		}
		return recipients;
	}
}
