package com.sanket.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanket.model.User;

public interface UserDao extends JpaRepository<User, String> {
	
	public User findByEmail(String emailId);
	
}