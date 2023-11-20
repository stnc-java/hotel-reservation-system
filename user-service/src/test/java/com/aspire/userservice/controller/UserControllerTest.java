package com.aspire.userservice.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aspire.userservice.model.User;

@SpringBootTest
@DisplayName("Test controller")
class UserControllerTest {

	@Autowired
	private UserController userController; 
	
	@Test
	@DisplayName("Update user's password test")
	public void updatePasswordTest() {
		User user = new User("dev", "12345");
		ResponseEntity<User> responseEntity = userController.updatePassword(user.getUsername(), user);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Test update user's password with invalid password")
	public void updateInvalidUserPasswordTest() {
		User user = new User("dev", "123");
		ResponseEntity<User> responseEntity = userController.updatePassword(user.getUsername(), user);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	@DisplayName("Test update user's password with missing data")
	public void updateUserPasswordMissingDataTest() {
		User user = new User();
		ResponseEntity<User> responseEntity = userController.updatePassword(user.getUsername(), user);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	@DisplayName("Update user's password with no existing username test")
	public void updatePasswordNoExistingUsernameTest() {
		User user = new User("devvv", "12345");
		ResponseEntity<User> responseEntity = userController.updatePassword(user.getUsername(), user);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
	}

}
