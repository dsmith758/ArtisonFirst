package com.walkersmithtech.artisonfirst.core.service;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.core.BaseObjectService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.model.object.Message;

@Service
public class MessageService extends BaseObjectService<Message>
{
	public MessageService()
	{
		dataType = DataType.MESSAGE;
		modelClass = Message.class;
	}

	public Message createMessage( Message message ) throws ServiceException
	{
		validate( message );
		return createModel( message );
	}

	public Message updateMessage( Message message ) throws ServiceException
	{
		validate( message );
		Message match = getModelByUid( message.getUid() );
		if ( match == null )
		{
			throw ErrorCode.PERSON_NOT_FOUND.exception;
		}
		return updateModel( message );
	}
	
	public void deleteMessage( String uid ) throws ServiceException
	{
		deleteModel( uid );
	}

	@Override
	protected void createIndex( Message model )
	{
	}

	@Override
	protected void validate( Message model ) throws ServiceException
	{
		
	}

}
