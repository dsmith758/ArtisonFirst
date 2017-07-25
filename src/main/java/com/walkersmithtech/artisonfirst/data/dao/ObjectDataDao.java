package com.walkersmithtech.artisonfirst.data.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.walkersmithtech.artisonfirst.data.entity.ObjectData;

@Component
public class ObjectDataDao extends BaseDao
{

	public List< ObjectData > findObjectByIndex( String type, String data )
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
		query.setParameter( "data", data );
		@SuppressWarnings( "unchecked" )
		List< ObjectData > results = ( List< ObjectData > ) query.getResultList();
		return results;
	}

	public List< ObjectData > findObjectByProfileData( String key, String value )
	{
		StringBuilder builder = new StringBuilder();
		builder.append( "SELECT distinct obj.* " );
		builder.append( "FROM object_data obj " );
		builder.append( "LEFT JOIN object_profile profile on profile.object_uid = obj.object_uid " );
		builder.append( "WHERE " );
		builder.append( "profile.key = :key" );
		builder.append( "AND " );
		builder.append( "profile.value = :value" );

		Query query = entityManager.createNativeQuery( builder.toString(), ObjectData.class );
		query.setParameter( "key", key );
		query.setParameter( "value", value );
		@SuppressWarnings( "unchecked" )
		List< ObjectData > results = ( List< ObjectData > ) query.getResultList();
		return results;
	}

	public List< ObjectData > findObjectByRole( String role )
	{
		StringBuilder builder = new StringBuilder();
		builder.append( "SELECT distinct obj.* " );
		builder.append( "FROM object_data obj " );
		builder.append( "LEFT JOIN relation_data relation on relation.source_uid = obj.object_uid or relation.target_uid = obj.uid" );
		builder.append( "WHERE " );
		builder.append( "relation.role = :role" );

		Query query = entityManager.createNativeQuery( builder.toString(), ObjectData.class );
		query.setParameter( "role", role );
		@SuppressWarnings( "unchecked" )
		List< ObjectData > results = ( List< ObjectData > ) query.getResultList();
		return results;
	}
	
	public List< ObjectData > findObjectBySourceRelation( String uid )
	{
		StringBuilder builder = new StringBuilder();
		builder.append( "SELECT obj.*, relation.role " );
		builder.append( "FROM object_data obj " );
		builder.append( "JOIN relation_data relation on relation.target_uid = obj.object_uid " );
		builder.append( "WHERE " );
		builder.append( "relation.source_uid = :uid " );

		Query query = entityManager.createNativeQuery( builder.toString(), ObjectData.class );
		query.setParameter( "uid", uid );
		@SuppressWarnings( "unchecked" )
		List< ObjectData > results = ( List< ObjectData > ) query.getResultList();
		return results;
	}
	
	public List< ObjectData > findObjectByTargetRelation( String uid )
	{
		StringBuilder builder = new StringBuilder();
		builder.append( "SELECT obj.*, relation.role " );
		builder.append( "FROM object_data obj " );
		builder.append( "JOIN relation_data relation on relation.source_uid = obj.object_uid " );
		builder.append( "WHERE " );
		builder.append( "relation.target_uid = :uid " );

		Query query = entityManager.createNativeQuery( builder.toString(), ObjectData.class );
		query.setParameter( "uid", uid );
		@SuppressWarnings( "unchecked" )
		List< ObjectData > results = ( List< ObjectData > ) query.getResultList();
		return results;
	}
}
