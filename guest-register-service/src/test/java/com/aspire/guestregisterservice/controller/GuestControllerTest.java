package com.aspire.guestregisterservice.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aspire.guestregisterservice.models.Guest;

@SpringBootTest
@DisplayName("Test controller")
class GuestControllerTest {

	@Autowired 
	private GuestController guestController;
	
	@Test
	@DisplayName("Test save guest")
	public void saveGuestTest() {
		Guest guestOne = new Guest();
		guestOne.setName("Test test");
		guestOne.setEmail("test1@test.com");
		guestOne.setPhoneNumber("1523689715");
		guestOne.setTypeGuest("basic");
		guestOne.setCheckInDate("2022-08-09");
		guestOne.setCheckOutDate("2022-09-09");
		ResponseEntity<Guest> responseEntity = guestController.saveGuest(guestOne);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Test save guest missing data")
	public void saveGuestTestMissingData() throws Exception {
		ResponseEntity<Guest> responseEntity = guestController.saveGuest(new Guest());
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	@DisplayName("Test get all guests")
	public void getAllGuestsTest() {
		ResponseEntity<List<Guest>> responseEntity = guestController.getAllGuest();
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Test get guest by id")
	public void getGuestByIdTest() {
		ResponseEntity<Guest> responseEntity = guestController.getGuestById(1L);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Test get guest by not existing id")
	public void getGuestByNotExistingIdTest() {
		ResponseEntity<Guest> responseEntity = guestController.getGuestById(100L);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Test
	@DisplayName("Test update guest")
	public void updateGuestTest() {
		Guest guestOne = new Guest();
		guestOne.setName("Test one updated");
		guestOne.setEmail("test1@test.com");
		guestOne.setPhoneNumber("1523689715");
		guestOne.setTypeGuest("basic");
		guestOne.setCheckInDate("2022-08-09");
		guestOne.setCheckOutDate("2022-09-09");
		ResponseEntity<Guest> responseEntity = guestController.updateGuest(5L, guestOne);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Test update guest with no existing id")
	public void updateGuestNoExistingIdTest() {
		Guest guestOne = new Guest();
		guestOne.setName("Test one updated");
		guestOne.setEmail("test1@test.com");
		guestOne.setPhoneNumber("1523689715");
		guestOne.setTypeGuest("basic");
		guestOne.setCheckInDate("2022-08-09");
		guestOne.setCheckOutDate("2022-09-09");
		ResponseEntity<Guest> responseEntity = guestController.updateGuest(100L, guestOne);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	@Test
	@DisplayName("Test update guest with missing data")
	public void updateGuestMissingDataTest() {
		ResponseEntity<Guest> responseEntity = guestController.updateGuest(5L, new Guest());
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	@DisplayName("Test delete guest")
	public void deleteGuestTest() {
		ResponseEntity<Boolean> responseEntity = guestController.deleteGuest(5L);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Test delete guest with no existing id")
	public void deleteGuestNoExistingIdTest() {
		ResponseEntity<Boolean> responseEntity = guestController.deleteGuest(100L);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
	}

}
