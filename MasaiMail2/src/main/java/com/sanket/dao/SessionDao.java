package com.sanket.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanket.model.CurrentUserSession;

public interface SessionDao extends JpaRepository<CurrentUserSession, String> {
	
	public CurrentUserSession findByEmail(String emailId);
	
	public  CurrentUserSession  findByUuid(String uuid);
	
}