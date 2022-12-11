package com.sanket.service;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanket.dao.EmailDao;
import com.sanket.dao.SessionDao;
import com.sanket.dao.UserDao;
import com.sanket.exceptions.EmailException;
import com.sanket.exceptions.LoginException;
import com.sanket.exceptions.UserException;
import com.sanket.model.CurrentUserSession;
import com.sanket.model.Email;
import com.sanket.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userdao;
	
	@Autowired
	SessionDao sessionDao;
	
	@Autowired
	EmailDao emailDao;

	@Override
	public User createCustomer(User user) throws UserException {
		
		User existinguser= userdao.findByEmail(user.getEmail());
		
		
		
		if(existinguser != null) 
			throw new UserException("User Already Registered with Mobile number");
			
		
		
		
			return userdao.save(user);
			
			
		}


	@Override
	public User updateCustomer(User user, String key) throws UserException {
		
		CurrentUserSession loggedInUser= sessionDao.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new UserException("Please provide a valid key to update a user");
		}
	
		if(user.getEmail().equals(loggedInUser.getEmail()) ) {
			
			return userdao.save(user);
			
		}
		else
			throw new UserException("Invalid user Details, please login first");
	}
	
	@Override
	public User getUserByUuid(String uuid) throws UserException, LoginException {
		
		CurrentUserSession user = sessionDao.findByUuid(uuid);
		
		if(user != null) {
			User user2 = userdao.findByEmail(user.getEmail());
			
			if(user2 != null) {
				
				return user2;
			
			}else {
				
				throw new UserException("User not present by this uuid " + uuid);
			}
		}else {
			
			throw new LoginException("Please enter valid uuid " + uuid);
		}
		
		
		
	}


	@Override
	public Email sendEmail(User user, Email email) throws EmailException {
		
		email.setLocalDateTime(LocalDateTime.now());
		
		user.getListOfEmail().add(email);
		user.getListOfEmail().forEach(eachEmail -> eachEmail.setUser(user));
		
		userdao.save(user);
		
		return email;
		
	}


	@Override
	public Email makeMailStarred(User user, Integer id) throws EmailException {
		
		Optional<Email> email = emailDao.findById(id);
		
		if(email.isPresent()) {
			
			email.get().setStarMail(true);
			
			return emailDao.save(email.get());
			
			
		}else {
			
			throw new EmailException("No email found with this id " + id);
		}
		
	}


	@Override
	public Email deleteMail(User user, Integer id) throws EmailException {
		
		
		
		Optional<Email> email = emailDao.findById(id);
		
		
		
		if(email.isPresent()) {
			
			Email deletedEmail = null;
			
			List<Email> listOfEmails = user.getListOfEmail();
			
			for(int i=0; i<=listOfEmails.size()-1; i++) {
				
				if(listOfEmails.get(i).getId() == id) {
					deletedEmail = listOfEmails.get(i);
					listOfEmails.remove(i);
				}
			}
			
			
			listOfEmails.forEach(eachEmail -> eachEmail.setUser(user));
			
			user.setListOfEmail(listOfEmails);
			
			userdao.save(user);
			
			return deletedEmail;
			
		}else {
			
			throw new EmailException("No email found with this id " + id);
		}
	}

}


























