package com.walkersmithtech.artisonfirst.data.model.relation;

import com.walkersmithtech.artisonfirst.constant.ObjectType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.BaseObjectRelation;
import com.walkersmithtech.artisonfirst.data.model.object.Message;
import com.walkersmithtech.artisonfirst.data.model.object.Person;

public class MessageSender extends BaseObjectRelation
{
	public MessageSender()
	{
		this.type = RelationshipType.MESSAGE_SENDER.name();
	}

	public void addMessage( Message message )
	{
		RoleData role = new RoleData();
		role.setObject( message );
		role.setObjectUid( message.getUid() );
		role.setObjectType( ObjectType.MESSAGE.name() );
		role.setRole( RelationshipRole.MESSAGE.name() );
		addCollaborator( role );
	}
	
	public RoleData retrieveMessage()
	{
		return getCollaborator( RelationshipRole.MESSAGE.name() );
	}

	public void addSender( Person sender )
	{
		RoleData role = new RoleData();
		role.setObject( sender );
		role.setObjectUid( sender.getUid() );
		role.setObjectType( ObjectType.PERSON.name() );
		role.setRole( RelationshipRole.SENDER.name() );
		addCollaborator( role );
	}
	
	public RoleData retrieveSender()
	{
		return getCollaborator( RelationshipRole.SENDER.name() );
	}
}
