package com.walkersmithtech.artisonfirst.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.entity.RelationData;
import com.walkersmithtech.artisonfirst.data.model.Company;
import com.walkersmithtech.artisonfirst.data.model.Person;
import com.walkersmithtech.artisonfirst.data.model.relation.PersonCompany;
import com.walkersmithtech.artisonfirst.service.BaseRelationService;

@Service
public class PersonCompanyServiceImpl extends BaseRelationService<PersonCompany>
{
	public PersonCompanyServiceImpl()
	{
		roleType = RelationshipRole.PERSON_COMPANY;
		relationClass = PersonCompany.class;
		sourceClass = Person.class;
		targetClass = Company.class;
	}

	@Override
	public PersonCompany createModel( PersonCompany relation )
	{
		if ( relation != null )
		{
			RelationData match = dataRepo.findBySourceUidAndTargetUid( relation.getSourceUid(), relation.getTargetUid() );
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
			RelationData match = dataRepo.findBySourceUidAndTargetUid( relation.getSourceUid(), relation.getTargetUid() );
			if ( match == null )
			{
				return createModel( relation );
			}
		}
		return relation;
	}
	
	public PersonCompany getModelBySourceUid( String uid )
	{
		List<RelationData> entities = dataRepo.findBySourceUidAndRole( uid, RelationshipRole.PERSON_COMPANY.name() );
		if ( entities != null && entities.size() > 0 )
		{
			RelationData entity = entities.get( 0 );
			return convertEntityToModel( entity );
		}
		return null;
	}

}
