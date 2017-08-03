package com.walkersmithtech.artisonfirst.component.service;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.component.BaseRelationService;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.model.object.Location;
import com.walkersmithtech.artisonfirst.data.model.object.Person;
import com.walkersmithtech.artisonfirst.data.model.relation.PersonLocation;

@Service
public class PersonLocationService extends BaseRelationService< PersonLocation >
{
	public PersonLocationService()
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
