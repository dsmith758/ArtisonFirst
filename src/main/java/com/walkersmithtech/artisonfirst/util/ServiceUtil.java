package com.walkersmithtech.artisonfirst.util;

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
}
