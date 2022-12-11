package com.sanket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanket.exceptions.EmailException;
import com.sanket.exceptions.LoginException;
import com.sanket.exceptions.UserException;
import com.sanket.model.Email;
import com.sanket.model.User;
import com.sanket.service.LoginService;
import com.sanket.service.UserService;

@RestController
public class EmailController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	
	@PostMapping("masaimail/mail")
	public ResponseEntity<Email> sendMail(@RequestParam String key, @RequestBody Email email) throws LoginException, UserException, EmailException{
		
		User user = userService.getUserByUuid(key);
		
		if(user != null) {
			if(loginService.checkUserLoginOrNot(key)) {
				
				
				Email sentEmail =  userService.sendEmail(user, email);
				
				return new ResponseEntity<Email>(sentEmail, HttpStatus.OK);
				
			}else {
				 
				throw new LoginException("Invalid key or please login first");
			}
			
		}else {
			
			throw new LoginException("Invalid key " + key);
			
		}
		
		
	}
	
	@PostMapping("masaimail/starred/{id}")
	public ResponseEntity<Email> makeMailStarred(@RequestParam String key, @PathVariable Integer id) throws LoginException, UserException, EmailException{
		
		User user = userService.getUserByUuid(key);
		
		if(user != null) {
			if(loginService.checkUserLoginOrNot(key)) {
				
				Email starredEmail = userService.makeMailStarred(user, id);
				
				return new ResponseEntity<Email>(starredEmail,HttpStatus.OK);
				
			}else {
				 
				throw new LoginException("Invalid key or please login first");
			}
			
		}else {
			
			throw new LoginException("Invalid key " + key);
			
		}
		
		
	}
	
	@PostMapping("masaimail/delete/{id}")
	public ResponseEntity<Email> deleteMail(@RequestParam String key, @PathVariable Integer id) throws LoginException, UserException, EmailException{
		
		User user = userService.getUserByUuid(key);
		
		if(user != null) {
			if(loginService.checkUserLoginOrNot(key)) {
				
				Email deletedEmail = userService.deleteMail(user, id);
				
				return new ResponseEntity<Email>(deletedEmail,HttpStatus.OK);
				
			}else {
				 
				throw new LoginException("Invalid key or please login first");
			}
			
		}else {
			
			throw new LoginException("Invalid key " + key);
			
		}
		
		
	}
	
	
}


















