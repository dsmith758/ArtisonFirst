package com.walkersmithtech.artisonfirst.core.builder;

import com.walkersmithtech.artisonfirst.core.BaseBuilder;
import com.walkersmithtech.artisonfirst.data.entity.UserAccount;
import com.walkersmithtech.artisonfirst.data.model.Account;
import com.walkersmithtech.artisonfirst.data.model.object.Company;

public class ProfileBuilder extends BaseBuilder<Account>
{
	public Account createOrUpdateSession( UserAccount user, Company company, String ipAddress, String sessionId, String token, boolean isAuthenticated )
	{
		Account account = new Account();
		account.setDisplayName( user.getDisplayName() );
		account.setLoginName( user.getLoginName() );
		account.setPersonUid( user.getPersonUid() );
		account.setCompanyUid( company != null ? company.getUid() : "" );
		account.setAuthenticated( true );
		account.setSessionId( sessionId );
		account.setToken( token );
		return account;
	}
}
