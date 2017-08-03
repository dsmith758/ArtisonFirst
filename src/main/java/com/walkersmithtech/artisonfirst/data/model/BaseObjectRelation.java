package com.walkersmithtech.artisonfirst.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude( Include.NON_EMPTY )
public abstract class BaseObjectRelation extends BaseRelation
{
	protected String sourceUid;
	protected String targetUid;

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
}
