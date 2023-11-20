package com.aspire.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aspire.webapp.exceptions.APIUnprocessableEntity;
import com.aspire.webapp.model.User;
import com.aspire.webapp.utils.UserValidator;

@Service
public class UserService {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	UserValidator userValidator;
	
	public ResponseEntity<User> updatePassword(User user) throws Exception {
		try {
			userValidator.validate(user);		
			HttpHeaders headers = new HttpHeaders();      
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<User> entity = new HttpEntity<>(user, headers);       
	    	return restTemplate.exchange("http://user-service/users/" + user.getUsername(), HttpMethod.PUT, entity, User.class);
			
		   // restTemplate.put("http://user-service/users/{username}", user, user.getUsername());
		}catch(APIUnprocessableEntity exception) {
			throw new APIUnprocessableEntity(exception.getMessage());
		}catch(Exception exception) {
			throw new NotFoundException();
		}
	}
}
