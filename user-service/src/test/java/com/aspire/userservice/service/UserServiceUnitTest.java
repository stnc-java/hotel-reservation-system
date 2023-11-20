package com.aspire.userservice.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aspire.userservice.model.User;
import com.aspire.userservice.repository.UserRepository;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test user service")
class UserServiceUnitTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;

	@BeforeEach
    public void init() {
    	MockitoAnnotations.openMocks(this);
    }
	
    @Test
    @DisplayName("Test mock creation")
    public void testMockCreation(){
        assertNotNull(userRepository);
        assertNotNull(userService);
    }
    
	@Test
	@DisplayName("Test update user's password")
	public void updateUserPasswordTest() throws Exception {
		User user = new User("dev", "12345");
		when(userRepository.findByUsername(any(String.class)).get()).thenReturn(user);
	//	when(userRepository.save(any(User.class))).thenReturn(user);
		
	//	User newUser = userService.updatePassword(user);
		Assertions.assertNotNull(userService.updatePassword(user));
	}
}
