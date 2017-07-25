package com.walkersmithtech.artisonfirst.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walkersmithtech.artisonfirst.data.entity.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, Integer>
{
	public UserSession findBySessionId( String sessionId );
	
	public List<UserSession> findByIpAddress( String ipAddress );
	
}
