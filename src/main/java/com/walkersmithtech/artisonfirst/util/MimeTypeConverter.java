package com.walkersmithtech.artisonfirst.util;

import javax.activation.MimetypesFileTypeMap;

public class MimeTypeConverter
{
	private final static MimetypesFileTypeMap mapper = new MimetypesFileTypeMap( );

	public static String getMimeTypeByFilenameExtension( String fileName )
	{
		return mapper.getContentType( fileName );
	}
}
