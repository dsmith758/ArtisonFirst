package com.walkersmithtech.artisonfirst.data.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;

@Component
public class ObjectRelationDao extends BaseDao
{

	public List<ObjectRelationData> findObjectRelationByCollaborators( List<RoleData> collaborators )
	{
		List<String> objectUids = convertToObjectUids( collaborators );
		return findObjectRelationByObjectUids( objectUids );
	}
	
	public List<ObjectRelationData> findObjectRelationByObjectUids( List<String> objectUids )
	{
		if ( objectUids != null && objectUids.size() > 0 )
		{
			StringBuilder builder = new StringBuilder();
			builder.append( "SELECT " );
			builder.append( "	relation.* " );
			builder.append( "FROM " );
			builder.append( " 	object_relation_data relation " );

			for ( int line = 0; line < objectUids.size(); line++ )
			{
				builder.append( "	JOIN role_data rd" ).append( line ).append( " ON rd" ).append( line ).append( ".object_relation_uid = relation.uid " );
			}

			builder.append( "WHERE " );

			boolean firstPass = true;
			for ( int line = 0; line < objectUids.size(); line++ )
			{
				if ( !firstPass )
				{
					builder.append( "AND " );
				}
				builder.append( "	rd" ).append( line ).append( ".object_uid = ? " );
			}

			@SuppressWarnings( { "unchecked", "rawtypes" } )
			List<ObjectRelationData> results = ( List<ObjectRelationData> ) jdbc.query( builder.toString(), new BeanPropertyRowMapper( ObjectRelationData.class ), objectUids );
			return results;
		}
		return null;
	}
	
	public List<ObjectRelationData> findObjectRelationByCollaboratorsAndType( List<RoleData> collaborators, RelationshipType type )
	{
		List<String> objectUids = convertToObjectUids( collaborators );
		return findObjectRelationByObjectUidAndType( objectUids, type );
	}
	
	public List<ObjectRelationData> findObjectRelationByObjectUidAndType( List<String> objectUids, RelationshipType type )
	{
		if ( objectUids != null && objectUids.size() > 0 )
		{
			List<String> values = new ArrayList<>();
			StringBuilder builder = new StringBuilder();
			builder.append( "SELECT " );
			builder.append( "	relation.* " );
			builder.append( "FROM " );
			builder.append( " 	object_relation_data relation " );

			for ( int line = 0; line < objectUids.size(); line++ )
			{
				builder.append( "	JOIN role_data rd" ).append( line ).append( " ON rd" ).append( line ).append( ".object_relation_uid = relation.uid " );
			}

			builder.append( "WHERE " );
			builder.append( "	relation.type = ? " );
			builder.append( "AND " );
			values.add( type.name() );

			boolean firstPass = true;
			for ( int line = 0; line < objectUids.size(); line++ )
			{
				if ( !firstPass )
				{
					builder.append( "AND " );
				}
				builder.append( "	rd" ).append( line ).append( ".object_uid = ? " );
				values.add( objectUids.get( line ) );
				firstPass = false;
			}
			
			System.out.println( builder.toString() );
			@SuppressWarnings( { "unchecked", "rawtypes" } )
			List<ObjectRelationData> results = ( List<ObjectRelationData> ) jdbc.query( builder.toString(), new BeanPropertyRowMapper( ObjectRelationData.class ), values.toArray() );
			return results;
		}
		return null;
	}
	
	public List<ObjectRelationData> findObjectRelationByCollaborators( String objectUid, RelationshipType type, RelationshipRole role )
	{
		if ( objectUid != null )
		{
			StringBuilder builder = new StringBuilder();
			builder.append( "SELECT " );
			builder.append( "	relation.* " );
			builder.append( "FROM " );
			builder.append( " 	object_relation_data relation " );
			builder.append( "	JOIN role_data rd  ON rd.object_relation_uid = relation.uid " );
			builder.append( "WHERE " );
			builder.append( "	rd.object_uid = ? " );
			builder.append( "AND " );
			builder.append( "	rd.role = ? " );
			builder.append( "AND " );
			builder.append( "	relation.type = ? " );

			@SuppressWarnings( { "unchecked", "rawtypes" } )
			List<ObjectRelationData> results = ( List<ObjectRelationData> ) jdbc.query( builder.toString(), new BeanPropertyRowMapper( ObjectRelationData.class ), objectUid, role.name(), type.name() );
			return results;
		}
		return null;
	}

	private List<String> convertToObjectUids( List<RoleData> collaborators )
	{
		if ( collaborators != null && collaborators.size() > 0 )
		{
			List<String> objectUids = new ArrayList<>();
			for ( RoleData role : collaborators )
			{
				if ( role.getObjectUid() != null )
				{
					objectUids.add( role.getObjectUid() );
				}
			}
		}
		return null;
	}
}
