package com.walkersmithtech.artisonfirst.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.BaseRelationService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.entity.ObjectRelationData;
import com.walkersmithtech.artisonfirst.data.entity.RoleData;
import com.walkersmithtech.artisonfirst.data.model.relation.OrganizationPrincipal;

@Service
public class PersonCompanyService extends BaseRelationService<OrganizationPrincipal>
{
	public PersonCompanyService()
	{
		type = RelationshipType.PERSON_COMPANY;
		modelClass = OrganizationPrincipal.class;
	}

	public OrganizationPrincipal createPersonCompany( OrganizationPrincipal organization ) throws ServiceException
	{
		validate( organization );
		RoleData company = organization.retrieveOrganization();
		RoleData person = organization.retrievePrincipal();
		OrganizationPrincipal match = getRelationsByPrincipleAndOrganization( person.getObjectUid(), company.getObjectUid() );
		if ( match != null )
		{
			updateSender( organization );
		}
		organization = createModel( organization );
		return organization;
	}

	public OrganizationPrincipal updateSender( OrganizationPrincipal organization ) throws ServiceException
	{
		validate( organization );
		organization = updateModel( organization );
		return organization;
	}

	public List<OrganizationPrincipal> getRelationsByPrinciple( String principleUid )
	{
		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( principleUid, type, RelationshipRole.PRINCIPAL );
		return buildList( entities );
	}

	public List<OrganizationPrincipal> getRelationsByOrganization( String organizationUid )
	{
		List<ObjectRelationData> entities = getRelationsByObjectUidAndRole( organizationUid, type, RelationshipRole.ORGANIZATION );
		return buildList( entities );
	}

	public OrganizationPrincipal getRelationsByPrincipleAndOrganization( String principleUid, String organizationUid )
	{
		List<String> objectUids = new ArrayList<>();
		objectUids.add( principleUid );
		objectUids.add( organizationUid );
		return getRelationsByCollaboratorsAndType( objectUids, type );
	}
	
	private List<OrganizationPrincipal> buildList( List<ObjectRelationData> entities )
	{
		List<OrganizationPrincipal> principals = new ArrayList<>();
		if ( entities != null && entities.size() > 0 )
		{
			for ( ObjectRelationData entity : entities )
			{
				principals.add( convertEntityToModel( entity ) );
			}
		}
		return principals;		
	}

	@Override
	protected void createIndex( OrganizationPrincipal model )
	{
		deleteIndex( model.getUid() );
		saveIndexData( model.getUid(), IndexType.PRINCIPLE, model.retrievePrincipal().getObject().getUid() );
		saveIndexData( model.getUid(), IndexType.ORGANIZATION, model.retrieveOrganization().getObject().getUid() );
	}

	@Override
	protected void validate( OrganizationPrincipal model ) throws ServiceException
	{
		RoleData principle = model.retrievePrincipal();
		if ( principle == null )
		{
			throw ErrorCode.ORGRANIZATION_MISSING_PERSON.exception;
		}

		if ( principle.getObject() == null )
		{
			throw ErrorCode.ORGRANIZATION_MISSING_PERSON.exception;
		}

		RoleData ogranization = model.retrieveOrganization();
		if ( ogranization == null )
		{
			throw ErrorCode.ORGRANIZATION_MISSING_COMPANY.exception;
		}

		if ( ogranization.getObject() == null )
		{
			throw ErrorCode.ORGRANIZATION_MISSING_COMPANY.exception;
		}
	}

}
