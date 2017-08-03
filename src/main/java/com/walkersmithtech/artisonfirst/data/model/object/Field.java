package com.walkersmithtech.artisonfirst.data.model.object;

import java.util.List;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.data.model.BaseObject;
import com.walkersmithtech.artisonfirst.data.model.fragment.KeyValue;

public class Field extends BaseObject
{
	private String label;
	private String inputType;
	private List<KeyValue> answerTemplate;
	private Boolean isGlobal;
	private String defaultValue;
	private String localeKey;

	public void initType()
	{
		this.type = DataType.FIELD.name();
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel( String label )
	{
		this.label = label;
	}

	public String getInputType()
	{
		return inputType;
	}

	public void setInputType( String inputType )
	{
		this.inputType = inputType;
	}

	public List<KeyValue> getAnswerTemplate()
	{
		return answerTemplate;
	}

	public void setAnswerTemplate( List<KeyValue> answerTemplate )
	{
		this.answerTemplate = answerTemplate;
	}

	public Boolean getIsGlobal()
	{
		return isGlobal;
	}

	public void setIsGlobal( Boolean isGlobal )
	{
		this.isGlobal = isGlobal;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue( String defaultValue )
	{
		this.defaultValue = defaultValue;
	}

	public String getLocaleKey()
	{
		return localeKey;
	}

	public void setLocaleKey( String localeKey )
	{
		this.localeKey = localeKey;
	}
}
