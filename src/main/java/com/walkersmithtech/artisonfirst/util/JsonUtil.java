package com.walkersmithtech.artisonfirst.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil
{
	private final static ObjectMapper mapper = new ObjectMapper();

	public static String createJsonFromModel( Object model )
	{
		String json = null;
		try
		{
			json = mapper.writeValueAsString( model );
		}
		catch ( JsonProcessingException e )
		{
			e.printStackTrace();
		}
		return json;
	}

	public static <T> T createModelFromJson( String json, Class<T> model ) throws Exception
	{
		try
		{
			return ( T ) mapper.readValue( json, model );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings( "unchecked" )
	public static Map<String, Object> createMapFromJson( String json )
	{
		if ( json == null || json.isEmpty() )
		{
			json = "{}";
		}

		Map<String, Object> map = null;
		try
		{
			map = mapper.readValue( json, HashMap.class );
		}
		catch ( Exception e )
		{
			// this means the JSON is poorly formatted.
			map = new HashMap<String, Object>();
		}
		return map;
	}

	public static String createJsonFromMap( Map<String, Object> map )
	{
		try
		{
			String json = mapper.writeValueAsString( map );
			return json;
		}
		catch ( JsonProcessingException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
