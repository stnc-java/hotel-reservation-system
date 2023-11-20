package com.aspire.webapp.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aspire.webapp.model.Guest;


@SpringBootTest
@DisplayName("Test booking service")
class BookServiceIntegrationTest {
	
	@Autowired
	private BookService bookService;
	
	@Test
	@DisplayName("Test add guest")
	public void addGuestTest() throws Exception{
		Guest guestOne = new Guest();
		guestOne.setName("Test One");
		guestOne.setEmail("test1@test.com");
		guestOne.setPhoneNumber("1523689715");
		guestOne.setTypeGuest("basic");
		guestOne.setCheckInDate("2022-08-09");
		guestOne.setCheckOutDate("2022-09-09");
		ResponseEntity<Guest> responseEntity = bookService.addGuest(guestOne);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Test add guest with missing data")
	public void addGuestMissingDataTest() throws Exception{
		ResponseEntity<Guest> responseEntity = bookService.addGuest(new Guest());
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	//	assertThrows(Exception.class, () -> bookService.addGuest(new Guest()));
	}
	
	@Test
	@DisplayName("Test display all guests")
	public void getAllGuestTest() {
		ResponseEntity<Guest[]> response = bookService.getGuests();
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	@DisplayName("Test get guest by id")
	public void getGuestByIdTest(){
		ResponseEntity<Guest> responseEntity = bookService.getGuestById(1L);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
		
	@Test
	@DisplayName("Test update guest")
	public void updateGuestTest(){
		Guest guest = new Guest();
		guest.setName("Test two");
		guest.setEmail("test1@test.com");
		guest.setPhoneNumber("1523689715");
		guest.setTypeGuest("plus");
		guest.setCheckInDate("2022-08-09");
		guest.setCheckOutDate("2022-08-09");
		ResponseEntity<Guest> responseEntity = bookService.updateGuest(4L, guest);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertThrows(Exception.class, () -> bookService.updateGuest(80L, guest)); 
	}
			
	@Test 
	@DisplayName("Test delete guest")
	public void testDeleteGuest() throws Exception {
		ResponseEntity<Boolean> responseEntity = bookService.deleteGuest(4L);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertThrows(Exception.class, () -> bookService.deleteGuest(40L)); 
	}
}
