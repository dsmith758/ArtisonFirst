package com.walkersmithtech.artisonfirst.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.joda.time.DateTime;

public class DateUtil
{

	public static String generateUuid()
	{
		return UUID.randomUUID().toString();
	}

	public static String generateIV()
	{
		return generateUuid().substring( 0, 16 );
	}

	public static Date getCurrentDate()
	{
		DateTime dateTime = new DateTime();
		return dateTime.toDate();
	}

	public static String convertDateToTimestamp( Date date )
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss" );
		String string = dateFormat.format( date );
		return string;
	}

	public static String hashText( String plainText )
	{
		String hashtext = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			byte[] digest = md.digest( plainText.getBytes( "UTF-8" ) );
			BigInteger bigInt = new BigInteger( 1, digest );
			hashtext = bigInt.toString( 16 );
			while ( hashtext.length() < 32 )
			{
				hashtext = "0" + hashtext;
			}
		}
		catch ( NoSuchAlgorithmException | UnsupportedEncodingException e )
		{
			e.printStackTrace();
		}

		return hashtext;
	}

	public static boolean validatePassword( String plainText, String hashedText )
	{
		String newHash = hashText( plainText );
		if ( newHash.equals( hashedText ) )
		{
			return true;
		}
		return false;
	}

}
