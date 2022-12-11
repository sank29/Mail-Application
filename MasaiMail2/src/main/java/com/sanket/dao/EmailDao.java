package com.sanket.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanket.model.Email;

public interface EmailDao extends JpaRepository<Email, Integer> {

}
