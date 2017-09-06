package com.walkersmithtech.artisonfirst.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.BaseRelationService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.relation.MessageSender;

@Service
public class MessageSenderService extends BaseRelationService<MessageSender>
{
	public MessageSenderService()
	{
		type = RelationshipType.MESSAGE_SENDER;
		modelClass = MessageSender.class;
	}
	
	public MessageSender createSender( MessageSender sender ) throws ServiceException
	{
		validate( sender );
		RoleData message = sender.retrieveMessage();
		RoleData person = sender.retrieveSender();
		MessageSender match = getRelationByMessageAndSender( message.getObjectUid(), person.getObjectUid() );
		if ( match != null )
		{
			updateSender( sender );
		}
		sender = createModel( sender );
		return sender;
	}
	
	public MessageSender updateSender( MessageSender sender ) throws ServiceException
	{
		validate( sender );
		sender = updateModel( sender );
		return sender;
	}

	public List<MessageSender> getRelationsBySender( String serderUid )
	{
		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( serderUid, type, RelationshipRole.SENDER );
		List<MessageSender> models = new ArrayList<>();
		if ( entities != null && entities.size() > 0 )
		{		
			MessageSender model;
			for ( ObjectRelationData entity : entities )
			{
				model = convertEntityToModel( entity );
				models.add( model );
			}
		}
		return models;
	}

	public MessageSender getRelationsByMessage( String messageUid )
	{
		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( messageUid, type, RelationshipRole.MESSAGE );
		if ( entities != null && entities.size() > 0 )
		{
			ObjectRelationData entity = entities.get( 0 );
			MessageSender model = convertEntityToModel( entity );
			return model;
		}
		return null;
	}
	
	public MessageSender getRelationByMessageAndSender( String messageUid, String senderUid )
	{
		List<String> objectUids = new ArrayList<>();
		objectUids.add( messageUid );
		objectUids.add( senderUid );
		return getRelationsByCollaboratorsAndType( objectUids, type );
	}


	@Override
	protected void createIndex( MessageSender model )
	{
		deleteIndex( model.getUid() );
		saveIndexData( model.getUid(), IndexType.SENDER, model.retrieveSender().getObject().getUid() );
		saveIndexData( model.getUid(), IndexType.MESSAGE, model.retrieveMessage().getObject().getUid() );
	}

	@Override
	protected void validate( MessageSender model ) throws ServiceException
	{
		RoleData sender = model.retrieveSender();
		if ( sender == null )
		{
			throw ErrorCode.MESSAGE_MISSING_SENDER.exception;
		}
		
		if ( sender.getObject() == null )
		{
			throw ErrorCode.MESSAGE_MISSING_SENDER.exception;
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
