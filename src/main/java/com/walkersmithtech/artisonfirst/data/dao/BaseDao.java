package com.walkersmithtech.artisonfirst.data.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDao
{
	@PersistenceContext
	protected EntityManager entityManager;
}
