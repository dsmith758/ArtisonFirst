package com.walkersmithtech.artisonfirst.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.walkersmithtech.artisonfirst.data.model.BaseObject;

@Entity
@Table( name = "role_data" )
@NamedQuery( name = "RoleData.findAll", query = "SELECT r FROM RoleData r" )
public class RoleData
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Integer id;

	@Column( name = "uid", unique = true, nullable = false, length = 64 )
	private String uid;

	@Column( name = "object_uid", unique = true, nullable = false, length = 64 )
	private String objectUid;

	@Column( name = "object_relation_uid", unique = true, nullable = false, length = 64 )
	private String objectRelationUid;

	@Column( name = "role", nullable = false, length = 64 )
	private String role;

	@Column( name = "object_type", nullable = false, length = 64 )
	private String objectType;

	@Transient
	private BaseObject object;

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid( String uid )
	{
		this.uid = uid;
	}

	public String getObjectUid()
	{
		return objectUid;
	}

	public void setObjectUid( String objectUid )
	{
		this.objectUid = objectUid;
	}

	public String getObjectRelationUid()
	{
		return objectRelationUid;
	}

	public void setObjectRelationUid( String objectRelationUid )
	{
		this.objectRelationUid = objectRelationUid;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole( String role )
	{
		this.role = role;
	}

	public BaseObject getObject()
	{
		return object;
	}

	public void setObject( BaseObject object )
	{
		this.object = object;
	}

	public String getObjectType()
	{
		return objectType;
	}

	public void setObjectType( String objectType )
	{
		this.objectType = objectType;
	}

}
