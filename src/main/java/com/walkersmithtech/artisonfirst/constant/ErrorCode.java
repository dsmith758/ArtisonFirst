package com.walkersmithtech.artisonfirst.constant;

import org.springframework.http.HttpStatus;
import com.walkersmithtech.artisonfirst.service.ServiceException;

public enum ErrorCode
{

	// AUTHORIZATION ERRORS
	AUTH_INVALID_CREDENTIALS( new ServiceException( "AUTH_01", "Authorization failed due to invalid user login credentials.", HttpStatus.UNAUTHORIZED ) ),
	AUTH_INVALID_SESSION( new ServiceException( "AUTH_02", "Authorization failed due to expired or invalid session.", HttpStatus.UNAUTHORIZED ) ),
	AUTH_MISSING_DATA( new ServiceException( "AUTH_03", "Authorization failed because of incomplete data.", HttpStatus.UNAUTHORIZED ) ),

	// REGISTRATION ERRORS
	REGISTRATION_MISSING( new ServiceException( "REGIS_01", "User registration is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_ACCOUNT_MISSING( new ServiceException( "REGIS_02", "User account is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_USERNAME_MISSING( new ServiceException( "REGIS_03", "User name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_PASSWORD_MISSING( new ServiceException( "REGIS_04", "User password is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_DISPLAYNAME_MISSING( new ServiceException( "REGIS_05", "User display name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_USERINFO_MISSING( new ServiceException( "REGIS_06", "User info is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_USER_FIRSNAME_MISSING( new ServiceException( "REGIS_07", "User first name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_USER_LASTNAME_MISSING( new ServiceException( "REGIS_08", "User last name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_ORGNAIZTION_MISSING( new ServiceException( "REGIS_09", "Organization is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_ORGNAIZTION_NAME_MISSING( new ServiceException( "REGIS_10", "Organization name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_ACCOUNT_EXISTS( new ServiceException( "REGIS_11", "Account already exists.", HttpStatus.BAD_REQUEST ) ),

	// PERSON PROFILE ERRORS
	PERSON_NOT_FOUND( new ServiceException( "PERS_01", "Person record not found.", HttpStatus.NOT_FOUND ) ),
	PERSON_MISSING_ID( new ServiceException( "PERS_02", "Person ID is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	PERSON_RECORD_NULL( new ServiceException( "PERS_03", "The provided record is null.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	PERSON_FIRSNAME_MISSING( new ServiceException( "PERS_04", "Person's first name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	PERSON_LASTNAME_MISSING( new ServiceException( "PERS_05", "Person's last name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	PERSON_ALREADY_EXISTS( new ServiceException( "PERS_06", "Person record already exists.", HttpStatus.BAD_REQUEST ) ),

	// COMPANY PROFILE ERRORS
	COMPANY_NOT_FOUND( new ServiceException( "COMP_01", "Company record not found.", HttpStatus.NOT_FOUND ) ),
	COMPANY_MISSING_ID( new ServiceException( "COMP_02", "Company ID is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	COMPANY_RECORD_NULL( new ServiceException( "COMP_03", "The provided record is null.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	COMPANY_NAME_MISSING( new ServiceException( "COMP_04", "Coampany name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	COMPANY_ALREADY_EXISTS( new ServiceException( "COMP_05", "Company record already exists.", HttpStatus.BAD_REQUEST ) ),

	// LOCATION PROFILE ERRORS
	LOCATION_NOT_FOUND( new ServiceException( "LOC_01", "Location record not found.", HttpStatus.NOT_FOUND ) ),
	LOCATION_MISSING_ID( new ServiceException( "LOC_02", "Location ID is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	LOCATION_RECORD_NULL( new ServiceException( "LOC_03", "The provided record is null.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	LOCATION_NAME_MISSING( new ServiceException( "LOC_04", "Address name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	LOCATION_ADDRESS_MISSING( new ServiceException( "LOC_05", "Street address is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	LOCATION_CITY_MISSING( new ServiceException( "LOC_06", "City is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	LOCATION_ALREADY_EXISTS( new ServiceException( "LOC_07", "Location record already exists.", HttpStatus.BAD_REQUEST ) ),

	// FILE MANAGEMENT ERRORS
	FILE_MISSING( new ServiceException( "FILE_01", "File was not included in the upload.", HttpStatus.UNPROCESSABLE_ENTITY ) ),

	// SYSTEM ERRORS
	SYSTEM_ERROR( new ServiceException( "SYSTEM_01", "The system has thrown an unexpected error. See logs.", HttpStatus.INTERNAL_SERVER_ERROR ) ),
	SYSTEM_BAD_REQUEST( new ServiceException( "SYSTEM_02", "Cannot process the request.", HttpStatus.BAD_REQUEST ) ),;

	public ServiceException exception;

	private ErrorCode( ServiceException exception )
	{
		this.exception = exception;
	}

}
