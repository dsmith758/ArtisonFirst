package com.walkersmithtech.artisonfirst.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walkersmithtech.artisonfirst.data.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer>
{
	public UserAccount findByLoginName( String loginName );
}
