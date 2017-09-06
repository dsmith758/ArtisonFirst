package com.walkersmithtech.artisonfirst.data.model;

import java.util.ArrayList;
import java.util.List;

public class BaseList<T> extends BaseDto
{
	private List<T> list;

	public List<T> getList()
	{
		if ( list == null )
		{
			list = new ArrayList<T>();
		}
		return list;
	}

	public void setList( List<T> list )
	{
		this.list = list;
	}
	
	public void addElement( T element )
	{
		getList();
		list.add( element );
	}
}
