package com.walkersmithtech.artisonfirst.data.model.dto;

public class FileDto extends BaseDto
{
	protected String uid;
	protected String docType;
	protected String fileName;
	
	public FileDto()
	{
		this.docType = "FILE";
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid( String uid )
	{
		this.uid = uid;
	}

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
}
