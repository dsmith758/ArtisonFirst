package com.walkersmithtech.artisonfirst.data.model;

public abstract class BaseFileRelation extends BaseRelation
{
	protected String objectUid;
	protected String fileUid;

	public String getObjectUid()
	{
		return objectUid;
	}

	public void setObjectUid( String objectUid )
	{
		this.objectUid = objectUid;
	}

	public String getFileUid()
	{
		return fileUid;
	}

	public void setFileUid( String fileUid )
	{
		this.fileUid = fileUid;
	}

}
