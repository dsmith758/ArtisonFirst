package com.walkersmithtech.artisonfirst.constant;

import org.springframework.http.HttpStatus;

import com.walkersmithtech.artisonfirst.core.ServiceException;

public enum ErrorCode
{

	// AUTHORIZATION ERRORS
	AUTH_INVALID_CREDENTIALS( new ServiceException( "AUTH_01", "Authorization failed due to invalid user login credentials.", HttpStatus.UNAUTHORIZED ) ),
	AUTH_INVALID_SESSION( new ServiceException( "AUTH_02", "Authorization failed due to expired or invalid session.", HttpStatus.UNAUTHORIZED ) ),
	AUTH_MISSING_DATA( new ServiceException( "AUTH_03", "Authorization failed because of incomplete data.", HttpStatus.UNAUTHORIZED ) ),

	// COMPANY PROFILE ERRORS
	COMPANY_NOT_FOUND( new ServiceException( "COMPANY_01", "Company record not found.", HttpStatus.NOT_FOUND ) ),
	COMPANY_MISSING_ID( new ServiceException( "COMPANY_02", "Company ID is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	COMPANY_RECORD_NULL( new ServiceException( "COMPANY_03", "The provided record is null.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	COMPANY_NAME_MISSING( new ServiceException( "COMPANY_04", "Coampany name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	COMPANY_ALREADY_EXISTS( new ServiceException( "COMPANY_05", "Company record already exists.", HttpStatus.BAD_REQUEST ) ),
	
	// FIELD ERRORS
	FIELD_NOT_FOUND( new ServiceException( "FIELD_01", "Field record not found.", HttpStatus.NOT_FOUND ) ),
	FIELD_MISSING_ID( new ServiceException( "FIELD_02", "Field ID is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	FIELD_RECORD_NULL( new ServiceException( "FIELD_03", "The provided record is null.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	FIELD_LABEL_MISSING( new ServiceException( "FIELD_04", "Field label is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	FIELD_ALREADY_EXISTS( new ServiceException( "FIELD_05", "Field record already exists.", HttpStatus.BAD_REQUEST ) ),
	

	// FILE MANAGEMENT ERRORS
	FILE_MISSING( new ServiceException( "FILE_01", "File was not included in the upload.", HttpStatus.UNPROCESSABLE_ENTITY ) ),

	// LOCATION PROFILE ERRORS
	LOCATION_NOT_FOUND( new ServiceException( "LOCATION_01", "Location record not found.", HttpStatus.NOT_FOUND ) ),
	LOCATION_MISSING_ID( new ServiceException( "LOCATION_02", "Location ID is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	LOCATION_RECORD_NULL( new ServiceException( "LOCATION_03", "The provided record is null.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	LOCATION_NAME_MISSING( new ServiceException( "LOCATION_04", "Address name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	LOCATION_ADDRESS_MISSING( new ServiceException( "LOCATION_05", "Street address is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	LOCATION_CITY_MISSING( new ServiceException( "LOCATION_06", "City is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	LOCATION_ALREADY_EXISTS( new ServiceException( "LOCATION_07", "Location record already exists.", HttpStatus.BAD_REQUEST ) ),

	// PERSON PROFILE ERRORS
	PERSON_NOT_FOUND( new ServiceException( "PERSON_01", "Person record not found.", HttpStatus.NOT_FOUND ) ),
	PERSON_MISSING_ID( new ServiceException( "PERSON_02", "Person ID is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	PERSON_RECORD_NULL( new ServiceException( "PERSON_03", "The provided record is null.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	PERSON_FIRSNAME_MISSING( new ServiceException( "PERSON_04", "Person's first name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	PERSON_LASTNAME_MISSING( new ServiceException( "PERSON_05", "Person's last name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	PERSON_ALREADY_EXISTS( new ServiceException( "PERSON_06", "Person record already exists.", HttpStatus.BAD_REQUEST ) ),

	// PERSON PROFILE ERRORS
	PRODUCT_NOT_FOUND( new ServiceException( "PRODUCT_01", "Product record not found.", HttpStatus.NOT_FOUND ) ),
	PRODUCT_MISSING_ID( new ServiceException( "PRODUCT_02", "Product ID is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	PRODUCT_RECORD_NULL( new ServiceException( "PRODUCT_03", "The provided record is null.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	PRODUCT_DESCRIPTION_MISSING( new ServiceException( "PRODUCT_04", "Product description is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	PRODUCT_COMPANY_MISMATCH( new ServiceException( "PRODUCT_05", "The company doesn't have access to this product.", HttpStatus.NOT_FOUND ) ),

	// REGISTRATION ERRORS
	REGISTRATION_MISSING( new ServiceException( "REG_01", "User registration is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_ACCOUNT_MISSING( new ServiceException( "REG_02", "User account is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_USERNAME_MISSING( new ServiceException( "REG_03", "User name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_PASSWORD_MISSING( new ServiceException( "REG_04", "User password is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_DISPLAYNAME_MISSING( new ServiceException( "REG_05", "User display name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_USERINFO_MISSING( new ServiceException( "REG_06", "User info is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_USER_FIRSNAME_MISSING( new ServiceException( "REG_07", "User first name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_USER_LASTNAME_MISSING( new ServiceException( "REG_08", "User last name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_ORGNAIZTION_MISSING( new ServiceException( "REG_09", "Organization is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_ORGNAIZTION_NAME_MISSING( new ServiceException( "REG_10", "Organization name is missing.", HttpStatus.UNPROCESSABLE_ENTITY ) ),
	REGISTRATION_ACCOUNT_EXISTS( new ServiceException( "REG_11", "Account already exists.", HttpStatus.BAD_REQUEST ) ),

	// SYSTEM ERRORS
	SYSTEM_ERROR( new ServiceException( "SYSTEM_01", "The system has thrown an unexpected error. See logs.", HttpStatus.INTERNAL_SERVER_ERROR ) ),
	SYSTEM_BAD_REQUEST( new ServiceException( "SYSTEM_02", "Cannot process the request.", HttpStatus.BAD_REQUEST ) ),;

	public ServiceException exception;

	private ErrorCode( ServiceException exception )
	{
		this.exception = exception;
	}

}
