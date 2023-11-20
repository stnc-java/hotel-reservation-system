package com.aspire.userservice.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aspire.userservice.model.User;
import com.aspire.userservice.repository.UserRepository;
import com.aspire.userservice.utils.exception.APIUnprocessableEntity;
import com.aspire.userservice.utils.exception.UserNotFound;
import com.aspire.userservice.utils.validator.UserValidator;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserValidator userValidator;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    public User updatePassword(User user) throws Exception {
		try{
        	userValidator.validate(user);
			User userToUpdate = userRepository.findByUsername(user.getUsername()).get();
	    	userToUpdate.setPassword(passwordEncoder().encode(user.getPassword()));
	    	return userRepository.save(userToUpdate);
		}catch(NoSuchElementException exception) {
			throw new UserNotFound("User to update not found.");
		}catch (APIUnprocessableEntity exception) {
			throw new APIUnprocessableEntity(exception.getMessage());
    	}catch(Exception exception) {
			exception.printStackTrace();
			throw new Exception("Something went wrong");
		}  
    }
    
    public List<User> getUsers() throws Exception{
    	try {
    		return  userRepository.findAll();
    	}catch (Exception exception) {
    		throw new Exception("Something went wrong");
    	}
    	
    }
}
