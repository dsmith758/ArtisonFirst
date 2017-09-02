package com.walkersmithtech.artisonfirst.data.model;

import com.walkersmithtech.artisonfirst.constant.RelationshipType;

public class BaseRelation extends BaseModel
{
	protected RelationshipType relationship;
	protected String type;
	protected String data;

	public RelationshipType getRelationship()
	{
		return relationship;
	}

	public void setRelationship( RelationshipType relationship )
	{
		if ( relationship != null )
		{
			this.type = relationship.name();
		}
		this.relationship = relationship;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public String getData()
	{
		return data;
	}

	public void setData( String data )
	{
		this.data = data;
	}
}
