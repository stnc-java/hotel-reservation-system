package com.aspire.webapp.utils;

import org.springframework.stereotype.Service;

import com.aspire.webapp.exceptions.APIUnprocessableEntity;
import com.aspire.webapp.model.User;


@Service
public class UserValidator {
	public void validate(User user) throws APIUnprocessableEntity {
        if(user.getUsername() == null || user.getUsername().isEmpty()){
            setMessage("Username is required.");
        }
        
        if(user.getPassword() == null || user.getPassword().isEmpty()) {
        	setMessage("Password is required");
        }else if (user.getPassword().length() < 5) {
            setMessage("Your password must contain at least 5 digits.");
        }
    }

    private void setMessage(String message) throws APIUnprocessableEntity{
        throw new APIUnprocessableEntity(message);
    }
}

