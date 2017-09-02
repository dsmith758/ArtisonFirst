package com.walkersmithtech.artisonfirst.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.BaseRelationService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.model.relation.PersonCompany;

@Service
public class PersonCompanyService extends BaseRelationService<PersonCompany>
{
	public PersonCompanyService()
	{
		type = RelationshipType.PERSON_COMPANY;
		modelClass = PersonCompany.class;
	}

	public PersonCompany getModelByPersonUid( String uid )
	{
		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( uid, type, RelationshipRole.PRINCIPLE );
		if ( entities != null && entities.size() > 0 )
		{
			ObjectRelationData entity = entities.get( 0 );
			PersonCompany personCompany = convertEntityToModel( entity );
			return personCompany;
		}
		return null;
	}
	
	public PersonCompany getModelByCompanyUid( String uid )
	{
		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( uid, type, RelationshipRole.ORGANIZATION );
		if ( entities != null && entities.size() > 0 )
		{
			ObjectRelationData entity = entities.get( 0 );
			return convertEntityToModel( entity );
		}
		return null;
	}

	@Override
	protected void createIndex( PersonCompany model )
	{
	}

	@Override
	protected void validate( PersonCompany model ) throws ServiceException
	{
		// TODO Auto-generated method stub
		
	}
	
}
