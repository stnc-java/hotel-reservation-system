package com.aspire.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.aspire.webapp.exceptions.APIUnprocessableEntity;
import com.aspire.webapp.model.User;
import com.aspire.webapp.service.UserService;

@Controller
public class PasswordController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/change-password")
	private String updatePassword(){
		return "change-password";
	}
	
	@PostMapping("/change-password")  
	private String changePasswrod(@ModelAttribute User user, Model model) {	
		if(!user.getPassword().equals(user.getRepassword())) {
			model.addAttribute("error", "Passwords must match");
		}else {
			try{
				userService.updatePassword(user).getBody();
				model.addAttribute("successful", "Password changed successfully. /nPlease login.");
			}catch(NotFoundException e) {
				model.addAttribute("errorUsername", "User not found");
			}catch(APIUnprocessableEntity e) {
				model.addAttribute("errorPassword", "Password must contain at least 5 digits.");
			}catch(Exception e) {
				model.addAttribute("errorMessage", "Something went wrong, try again.");
			}
		}
		return "change-password";
	}
}
