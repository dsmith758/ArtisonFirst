package com.walkersmithtech.artisonfirst.data.model.relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;

@JsonInclude( Include.NON_EMPTY )
public abstract class BaseRelation
{
	protected Integer id;
	protected String uid;
	protected RelationshipRole role;
	protected String sourceUid;
	protected String targetUid;

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public RelationshipRole getRole()
	{
		return role;
	}

	public void setRole( RelationshipRole role )
	{
		this.role = role;
	}

	public String getSourceUid()
	{
		return sourceUid;
	}

	public void setSourceUid( String sourceUid )
	{
		this.sourceUid = sourceUid;
	}

	public String getTargetUid()
	{
		return targetUid;
	}

	public void setTargetUid( String targetUid )
	{
		this.targetUid = targetUid;
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
