package com.walkersmithtech.artisonfirst.data.model.dto;

import com.walkersmithtech.artisonfirst.util.MimeTypeConverter;

public class FileDto extends BaseDto
{
	protected String uid;
	protected String docType;
	protected String fileName;
	protected String mimeType;

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
		if ( fileName != null )
		{
			setMimeType( MimeTypeConverter.getMimeTypeByFilenameExtension( fileName ) );
		}
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
