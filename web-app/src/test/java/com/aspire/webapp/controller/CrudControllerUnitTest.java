package com.aspire.webapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.aspire.webapp.model.Guest;
import com.aspire.webapp.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CrudController.class)
class CrudControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@MockBean
	private DataSource dataSource;
	
	@Test
	@DisplayName("Get all guests test")
	public void getGuestTest() throws Exception {
		Guest[] guestArray = {new Guest(), new Guest()};
		ResponseEntity<Guest[]> guests = new ResponseEntity<Guest[]>(guestArray, HttpStatus.OK);
		when(bookService.getGuests()).thenReturn(guests);
		mockMvc.perform(MockMvcRequestBuilders.get("/guests"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().attribute("guestList", guests.getBody()))
        .andExpect(MockMvcResultMatchers.view().name("show-all-guest"));
	}
	
	@Test
	@DisplayName("Add guest test")
	public void addGuestTest() throws Exception {
		ResponseEntity<Guest> newGuest = new ResponseEntity<Guest>(new Guest(), HttpStatus.OK);
		when(bookService.addGuest(any(Guest.class))).thenReturn(newGuest);
		mockMvc.perform(MockMvcRequestBuilders.post("/add-guest")
				.content(objectMapper.writeValueAsString(new Guest()))
    			.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("/guests"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@DisplayName("Delete guest test")
	public void deleteGuestTest() throws Exception{
		ResponseEntity<Boolean> deleteResponse = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		when(bookService.deleteGuest(5L)).thenReturn(deleteResponse);
		mockMvc.perform(MockMvcRequestBuilders.delete("/delete-guest-{id}", 5L)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("/guests"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@DisplayName("Edit guest's information test")
	public void editGuestInformationTest() throws Exception {
		ResponseEntity<Guest> guestToEdit = new ResponseEntity<Guest>(new Guest(), HttpStatus.OK);
		when(bookService.getGuestById(5L)).thenReturn(guestToEdit);
		mockMvc.perform(MockMvcRequestBuilders.get("/edit-guest-{id}", 5L)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attribute("guest", guestToEdit.getBody()))
				.andExpect(MockMvcResultMatchers.view().name("/guest-form"))
				.andDo(MockMvcResultHandlers.print());
	}
	

}
