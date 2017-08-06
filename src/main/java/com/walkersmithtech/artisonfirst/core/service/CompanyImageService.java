package com.walkersmithtech.artisonfirst.component.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.component.ServiceException;
import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.RelationshipRole;
import com.walkersmithtech.artisonfirst.data.model.dto.CompanyDto;
import com.walkersmithtech.artisonfirst.data.model.dto.ImageDto;
import com.walkersmithtech.artisonfirst.data.model.object.Company;

@Service
public class CompanyImageService
{
	@Autowired
	private FileManagerSerivce fileService;
	
	@Autowired
	private CompanyService companyService;

	public CompanyDto addAndSetCompanyLogo( ImageDto auth, String companyUid, byte[] file ) throws ServiceException
	{
		Company company = companyService.getCompanyByUid( companyUid );
		if ( company != null )
		{
			auth = addCompanyImage( auth, companyUid, file );
			auth = setCompanyLogo( auth, companyUid, file );
			CompanyDto dto = new CompanyDto();
			company.setLogoUri( "/images/" + auth.getUid() );
			companyService.updateCompany( company );
			dto.setAccount( auth.getAccount() );
			dto.setCompany( company );
			return dto;
		}
		throw ErrorCode.COMPANY_NOT_FOUND.exception;
	}

	public ImageDto addCompanyImage( ImageDto auth, String companyUid, byte[] file ) throws ServiceException
	{
		auth = ( ImageDto ) fileService.createFileRecord( file, auth );
		auth = setCompanyLogo( auth, companyUid, file );
		auth = ( ImageDto ) fileService.createFileRelation( companyUid, RelationshipRole.COMPANY_IMAGE.name(), auth );
		return auth;
	}

	public ImageDto setCompanyLogo( ImageDto auth, String companyUid, byte[] file ) throws ServiceException
	{
		auth = ( ImageDto ) fileService.createFileRecord( file, auth );
		fileService.deleteFileRelationByObjectAndRole( companyUid, RelationshipRole.COMPANY_LOGO.name() );
		auth = ( ImageDto ) fileService.createFileRelation( companyUid, RelationshipRole.COMPANY_LOGO.name(), auth );
		return auth;
	}

}
