package com.walkersmithtech.artisonfirst.data.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class BaseDao
{
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected JdbcTemplate jdbc;
	protected NamedParameterJdbcTemplate jdbcNamed;

	@Autowired
	protected void setDataSource( DataSource dataSource )
	{
		this.jdbc = new JdbcTemplate( dataSource );
		this.jdbcNamed = new NamedParameterJdbcTemplate( dataSource );
	}

}
