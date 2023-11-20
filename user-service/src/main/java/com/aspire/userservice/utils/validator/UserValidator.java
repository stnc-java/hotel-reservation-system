package com.aspire.userservice.utils.validator;

import org.springframework.stereotype.Service;

import com.aspire.userservice.model.User;
import com.aspire.userservice.utils.exception.APIUnprocessableEntity;

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
