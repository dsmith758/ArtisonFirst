package com.walkersmithtech.artisonfirst.data.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.walkersmithtech.artisonfirst.data.entity.ObjectData;

@Component
public class ObjectDataDao extends BaseDao
{

	public List<ObjectData> findObjectByIndex( String type, String data )
	{
		StringBuilder builder = new StringBuilder();
		builder.append( "SELECT distinct obj.* " );
		builder.append( "FROM object_data obj " );
		builder.append( "LEFT JOIN object_data_index obj_idx on obj_idx.object_uid = obj.object_uid " );
		builder.append( "WHERE " );
		builder.append( "obj_idx.type = :type " );
		builder.append( "AND " );
		builder.append( "obj_idx.data = :data" );

		Query query = entityManager.createNativeQuery( builder.toString(), ObjectData.class );
		query.setParameter( "type", type );
		query.setParameter( "data", data );
		@SuppressWarnings( "unchecked" )
		List<ObjectData> results = ( List<ObjectData> ) query.getResultList();
		return results;
	}

	public List<ObjectData> findObjectByIndexAndRoles( String sourceObjectUid, String sourceRole, String targetRole, String type, String data )
	{
		StringBuilder builder = new StringBuilder();
		builder.append( "SELECT obj1.* " );
		builder.append( "FROM object_data AS obj1 " );
		builder.append( "JOIN object_data_index AS idx ON idx.uid = obj1.uid " );
		builder.append( "JOIN role_data AS role1 ON role1.object_uid = obj1.uid " );
		builder.append( "JOIN object_relation_data AS relation ON role1.object_relation_uid = relation.uid " );
		builder.append( "JOIN role_data AS role2 ON role2.object_relation_uid = relation.uid " );
		builder.append( "WHERE " );
		builder.append( "role1.role = ':targetRole AND " );
		builder.append( "role2.role = :sourceRole AND " );
		builder.append( "role2.object_uid = :sourceObjectUid AND " );
		builder.append( "idx.type = :type AND " );
		builder.append( "idx.`data` = :data " );

		Query query = entityManager.createNativeQuery( builder.toString(), ObjectData.class );
		query.setParameter( "type", type );
		query.setParameter( "data", data );
		query.setParameter( "sourceObjectUid", sourceObjectUid );
		query.setParameter( "sourceRole", sourceRole );
		query.setParameter( "targetRole", targetRole );

		@SuppressWarnings( "unchecked" )
		List<ObjectData> results = ( List<ObjectData> ) query.getResultList();
		return results;
	}

}
