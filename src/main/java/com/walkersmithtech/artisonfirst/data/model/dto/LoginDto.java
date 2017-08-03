package com.walkersmithtech.artisonfirst.data.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.walkersmithtech.artisonfirst.data.model.BaseDto;

@JsonInclude( Include.NON_EMPTY )
public class LoginDto extends BaseDto
{
	
}
