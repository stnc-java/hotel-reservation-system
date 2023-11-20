package com.aspire.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.userservice.model.User;
import com.aspire.userservice.service.UserService;
import com.aspire.userservice.utils.exception.APIUnprocessableEntity;
import com.aspire.userservice.utils.exception.UserNotFound;
import com.aspire.userservice.utils.validator.UserValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
    @ApiOperation(value = "Update user's password", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password updated successfully"),
            @ApiResponse(responseCode = "400", description = "Username and 5 digits password are required"),
            @ApiResponse(responseCode = "404", description = "User to update not found"),
            @ApiResponse(responseCode = "500", description = "Something went wrong"),
    })
    @PutMapping("/{username}")
    public ResponseEntity<User> updatePassword(@PathVariable String username, @RequestBody User user){ 
        try {
        	return new ResponseEntity<User>(userService.updatePassword(user), HttpStatus.OK);
		} catch (UserNotFound exception) {
			return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
		} catch (APIUnprocessableEntity exception) {
			return new ResponseEntity<User>(new User(), HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			return new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
		}
    }
    
    @ApiOperation(value = "Get users")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description ="Users found"),
    		@ApiResponse(responseCode = "500", description = "Something went wrong")
    })
    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){
    	try {
    		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    	}catch (Exception exception) {
    		return new ResponseEntity<List<User>>(new ArrayList<User>(), HttpStatus.BAD_GATEWAY);
    	}
    }
}
