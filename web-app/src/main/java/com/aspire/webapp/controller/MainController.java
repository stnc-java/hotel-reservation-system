package com.aspire.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aspire.webapp.service.BookService;

@Controller
public class MainController {
	@Autowired
    BookService bookService;
	
	@GetMapping({"/home", "/"})
	private String showHome() {
		return "index";
	}
	
	@RequestMapping("/main")
	private String administratorView() {
		return "main-view";
	}
	
	@RequestMapping("/guest-form")
	private String showGuestForm() {
		return "guest-form";
	}
	
    @GetMapping("/logout")
	private String logOut(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	session.invalidate();
    	return "index";
	}
    
    @GetMapping("/error")
    private String errorPage() {
    	return "error-page";
    }
}
