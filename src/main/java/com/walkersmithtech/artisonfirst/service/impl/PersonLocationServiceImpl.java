package com.walkersmithtech.artisonfirst.service.impl;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.model.Location;
import com.walkersmithtech.artisonfirst.data.model.Person;
import com.walkersmithtech.artisonfirst.data.model.relation.PersonLocation;
import com.walkersmithtech.artisonfirst.service.BaseRelationService;

@Service
public class PersonLocationServiceImpl extends BaseRelationService< PersonLocation >
{
	public PersonLocationServiceImpl()
	{
		roleType = RelationshipRole.PERSON_LOCATION.name();
		relationClass = PersonLocation.class;
		sourceClass = Person.class;
		targetClass = Location.class;
	}


	@Override
	public PersonLocation createModel( PersonLocation relation )
	{
		if ( relation != null )
		{
			ObjectRelationData entity = dataRepo.findBySourceUidAndTargetUid( relation.getSourceUid(), relation.getTargetUid() );
			if ( entity == null )
			{
				relation = createData( relation );
			}
		}
		return relation;
	}

	@Override
	public PersonLocation updateModel( PersonLocation relation )
	{
		if ( relation != null )
		{
			ObjectRelationData entity = dataRepo.findBySourceUidAndTargetUid( relation.getSourceUid(), relation.getTargetUid() );
			if ( entity == null )
			{
				relation = createData( relation );
			}
		}
		return relation;
	}

}
