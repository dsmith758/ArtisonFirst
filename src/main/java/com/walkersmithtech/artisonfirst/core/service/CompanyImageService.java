package com.walkersmithtech.artisonfirst.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.core.builder.OrganizationBuilder;
import com.walkersmithtech.artisonfirst.data.model.dto.ImageDto;
import com.walkersmithtech.artisonfirst.data.model.dto.OrganizationDto;
import com.walkersmithtech.artisonfirst.data.model.object.Company;

@Service
public class CompanyImageService
{
	@Autowired
	private FileManagerSerivce fileService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private OrganizationBuilder organizationBuilder;


	public OrganizationDto addAndSetCompanyLogo( ImageDto auth, String companyUid, byte[] file ) throws ServiceException
	{
		Company company = companyService.getCompanyByUid( companyUid );
		if ( company != null )
		{
			auth = addCompanyImage( auth, companyUid, file );
			auth = setCompanyLogo( auth, companyUid, file );
			company.setLogoUri( "/images/" + auth.getUid() );
			companyService.updateCompany( company );

			OrganizationDto dto = new OrganizationDto();
			dto.setAccount( auth.getAccount() );
			dto.setCompany( company );
			dto = organizationBuilder.getOrganizationByCompanyUid( dto );
			return dto;
		}
		throw ErrorCode.COMPANY_NOT_FOUND.exception;
	}

	public ImageDto addCompanyImage( ImageDto auth, String companyUid, byte[] file ) throws ServiceException
	{
		auth = ( ImageDto ) fileService.createFileRecord( file, auth );
		auth = setCompanyLogo( auth, companyUid, file );
		auth = ( ImageDto ) fileService.createFileRelation( companyUid, RelationshipType.COMPANY_IMAGE.name(), auth );
		return auth;
	}

	public ImageDto setCompanyLogo( ImageDto auth, String companyUid, byte[] file ) throws ServiceException
	{
		auth = ( ImageDto ) fileService.createFileRecord( file, auth );
		fileService.deleteFileRelationByObjectAndRole( companyUid, RelationshipType.COMPANY_LOGO.name() );
		auth = ( ImageDto ) fileService.createFileRelation( companyUid, RelationshipType.COMPANY_LOGO.name(), auth );
		return auth;
	}

}
