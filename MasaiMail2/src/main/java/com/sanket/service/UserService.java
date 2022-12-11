package com.sanket.service;


import com.sanket.exceptions.EmailException;
import com.sanket.exceptions.LoginException;
import com.sanket.exceptions.UserException;
import com.sanket.model.Email;
import com.sanket.model.User;

public interface UserService {
	
	public User createCustomer(User user)throws UserException;
	
	public User updateCustomer(User customer,String key)throws UserException;
	
	public User getUserByUuid(String uuid) throws UserException,LoginException;
	
	public Email sendEmail(User user, Email email) throws EmailException;
	
	public Email makeMailStarred(User user, Integer id) throws EmailException;
	
	public Email deleteMail(User user, Integer id) throws EmailException;

}
