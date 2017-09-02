package com.walkersmithtech.artisonfirst.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServiceUtil
{
	public static Gson getGson( )
	{
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson;
	}
	
	public static boolean isListEmpty( List<?> list )
	{
		return ( list != null && list.size() > 0 );
	}
}
