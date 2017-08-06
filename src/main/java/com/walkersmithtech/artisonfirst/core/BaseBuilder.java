package com.walkersmithtech.artisonfirst.component;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseBuilder<T>
{
	@Autowired
	protected BaseService baseService;
}
