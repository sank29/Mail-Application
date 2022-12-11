package com.sanket.service;

import com.sanket.exceptions.LoginException;
import com.sanket.model.LoginDTO;

public interface LoginService {
	
	public String logIntoAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;
	
	public Boolean checkUserLoginOrNot(String key) throws LoginException;
}
