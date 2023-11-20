package com.aspire.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aspire.webapp.model.Guest;

@Service
public class BookService {
	@Autowired
	private RestTemplate restTemplate;
	
    public ResponseEntity<Guest> addGuest(Guest guest) { 
    	return restTemplate.postForEntity("http://guest-register-service/guests", guest, Guest.class);
    }
	
    public ResponseEntity<Guest[]> getGuests() { 
    	return restTemplate.getForEntity("http://guest-register-service/guests", Guest[].class);
    }
    
    public ResponseEntity<Guest> getGuestById(Long id) {
    	return restTemplate.getForEntity("http://guest-register-service/guests/{id}", Guest.class, id);
    }
    
    public ResponseEntity<Guest> updateGuest(Long id, Guest guest) {
    	 HttpHeaders headers = new HttpHeaders();      
         headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<Guest> entity = new HttpEntity<>(guest, headers);       
    	 return restTemplate.exchange("http://guest-register-service/guests/"+id, HttpMethod.PUT, entity, Guest.class);
    }
    
    public ResponseEntity<Boolean> deleteGuest(Long id) {
    	 HttpHeaders headers = new HttpHeaders();      
         headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<Object> entity = new HttpEntity<>(headers); 
    	 return restTemplate.exchange("http://guest-register-service/guests/" + id, HttpMethod.DELETE, entity, Boolean.class);
    }
    

}
