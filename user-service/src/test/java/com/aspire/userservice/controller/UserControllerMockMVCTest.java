package com.aspire.userservice.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.aspire.userservice.model.User;
import com.aspire.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(UserController.class)
public class UserControllerMockMVCTest {

    @Autowired
    private MockMvc mockMvc;
    
	@MockBean
    private UserService userService;
	
	@Autowired
    private ObjectMapper objectMapper;
   
    @Test
    @DisplayName("Update user's password test")
    public void updateUserPasswordTest() throws Exception {
    	User userToUpdate = new User("dev", "12345");
    	when(userService.updatePassword(userToUpdate)).thenReturn(new User());
    	mockMvc.perform(MockMvcRequestBuilders.put("/users/{username}", userToUpdate.getUsername())
    			.content(objectMapper.writeValueAsString(userToUpdate))
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(MockMvcResultMatchers.status().isOk())
    			.andDo(MockMvcResultHandlers.print());
    	
    }
    
}
