package com.walkersmithtech.artisonfirst.data.model;

public class BaseFile extends BaseModel
{
	protected String docType;
	protected String fileName;
	protected String mimeType;

	public String getDocType()
	{
		return docType;
	}

	public void setDocType( String docType )
	{
		this.docType = docType;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName( String fileName )
	{
		this.fileName = fileName;
	}

	public String getMimeType()
	{
		return mimeType;
	}

	public void setMimeType( String mimeType )
	{
		this.mimeType = mimeType;
	}
}
