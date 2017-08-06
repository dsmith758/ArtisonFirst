package com.walkersmithtech.artisonfirst.component.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.component.BaseRelationService;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.model.object.Company;
import com.walkersmithtech.artisonfirst.data.model.object.Person;
import com.walkersmithtech.artisonfirst.data.model.relation.PersonCompany;

@Service
public class PersonCompanyService extends BaseRelationService<PersonCompany>
{
	public PersonCompanyService()
	{
		roleType = RelationshipRole.PERSON_COMPANY.name();
		relationClass = PersonCompany.class;
		sourceClass = Person.class;
		targetClass = Company.class;
	}

	@Override
	public PersonCompany createModel( PersonCompany relation )
	{
		if ( relation != null )
		{
			ObjectRelationData match = dataRepo.findBySourceUidAndTargetUid( relation.getSourceUid(), relation.getTargetUid() );
			if ( match == null )
			{
				relation = createData( relation );
			}
		}
		return relation;
	}

	@Override
	public PersonCompany updateModel( PersonCompany relation )
	{
		if ( relation != null )
		{
			ObjectRelationData match = dataRepo.findBySourceUidAndTargetUid( relation.getSourceUid(), relation.getTargetUid() );
			if ( match == null )
			{
				return createModel( relation );
			}
		}
		return relation;
	}
	
	public PersonCompany getModelBySourceUid( String uid )
	{
		List<ObjectRelationData> entities = dataRepo.findBySourceUidAndRole( uid, RelationshipRole.PERSON_COMPANY.name() );
		if ( entities != null && entities.size() > 0 )
		{
			ObjectRelationData entity = entities.get( 0 );
			return convertEntityToModel( entity );
		}
		return null;
	}

}
