package com.walkersmithtech.artisonfirst.core.service;

import java.util.ArrayList;
import java.util.List;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.BaseRelationService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.relation.MessageRecipient;

public class MessageRecipientService extends BaseRelationService<MessageRecipient>
{
	public MessageRecipientService()
	{
		type = RelationshipType.MESSAGE_RECIPIENT;
		modelClass = MessageRecipient.class;
	}
	
	public MessageRecipient createRecipient( MessageRecipient recipient ) throws ServiceException
	{
		validate( recipient );
		RoleData message = recipient.retrieveMessage();
		RoleData person = recipient.retrieveRecipient();
		MessageRecipient match = getRelationByMessageAndRecipient( message.getObjectUid(), person.getObjectUid() );
		if ( match != null )
		{
			updateRecipient( recipient );
		}
		recipient = createModel( recipient );
		return recipient;
	}
	
	public MessageRecipient updateRecipient( MessageRecipient recipient ) throws ServiceException
	{
		validate( recipient );
		recipient = createModel( recipient );
		return recipient;
	}

	public List<MessageRecipient> getRelationsByRecipient( String recipientUid )
	{
		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( recipientUid, type, RelationshipRole.RECIPIENT );
		List<MessageRecipient> models = new ArrayList<>();
		if ( entities != null && entities.size() > 0 )
		{		
			MessageRecipient model;
			for ( ObjectRelationData entity : entities )
			{
				model = convertEntityToModel( entity );
				models.add( model );
			}
		}
		return models;
	}

	public List<MessageRecipient> getRelatinsByMessage( String messageUid )
	{
		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( messageUid, type, RelationshipRole.MESSAGE );
		List<MessageRecipient> models = new ArrayList<>();
		if ( entities != null && entities.size() > 0 )
		{		
			MessageRecipient model;
			for ( ObjectRelationData entity : entities )
			{
				model = convertEntityToModel( entity );
				models.add( model );
			}
		}
		return models;
	}
	
	public MessageRecipient getRelationByMessageAndRecipient( String messageUid, String recipientUid )
	{
		List<String> objectUids = new ArrayList<>();
		objectUids.add( messageUid );
		objectUids.add( recipientUid );
		return getRelationsByCollaboratorsAndType( objectUids, type );
	}


	@Override
	protected void createIndex( MessageRecipient model )
	{
		deleteIndex( model.getUid() );
		saveIndexData( model.getUid(), IndexType.RECIPIENT, model.retrieveRecipient().getObject().getUid() );
		saveIndexData( model.getUid(), IndexType.MESSAGE, model.retrieveMessage().getObject().getUid() );
	}

	@Override
	protected void validate( MessageRecipient model ) throws ServiceException
	{
		RoleData recipient = model.retrieveRecipient();
		if ( recipient == null )
		{
			throw ErrorCode.MESSAGE_MISSING_RECIPIENT.exception;
		}
		
		if ( recipient.getObject() == null )
		{
			throw ErrorCode.MESSAGE_MISSING_RECIPIENT.exception;
		}
		
		RoleData message = model.retrieveMessage();
		if ( message == null )
		{
			throw ErrorCode.MESSAGE_NO_MESSAGE.exception;
		}
		
		if ( message.getObject() == null )
		{
			throw ErrorCode.MESSAGE_NO_MESSAGE.exception;
		}
	}

}
