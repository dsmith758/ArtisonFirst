package com.walkersmithtech.artisonfirst.core.service;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.BaseRelationService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.model.relation.PersonLocation;

@Service
public class PersonLocationService extends BaseRelationService<PersonLocation>
{
	public PersonLocationService()
	{
		type = RelationshipType.PERSON_LOCATION;
		modelClass = PersonLocation.class;
	}

	@Override
	protected void createIndex( PersonLocation model )
	{
	}

	@Override
	protected void validate( PersonLocation model ) throws ServiceException
	{
	}

}
