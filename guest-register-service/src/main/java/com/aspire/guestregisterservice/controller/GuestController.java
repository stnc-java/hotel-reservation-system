package com.aspire.guestregisterservice.controller;

import com.aspire.guestregisterservice.models.Guest;
import com.aspire.guestregisterservice.service.GuestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @ApiOperation(value = "Gets all guests registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guests found"),
            @ApiResponse(responseCode = "404", description = "There are no guests"),
    })
    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuest(){
    	try {
			return new ResponseEntity<List<Guest>>(guestService.getAllGuests(), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<List<Guest>>(new ArrayList<Guest>(), HttpStatus.NOT_FOUND);
		}
    }

    @ApiOperation(value = "Saves new guest", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest was saved successfully"),
            @ApiResponse(responseCode = "400", description = "Guest's data is incomplete"),
            @ApiResponse(responseCode = "500", description = "Something went wrong"),
    })
    @PostMapping
    public ResponseEntity<Guest> saveGuest(@RequestBody Guest newGuest){
        try {
			return new ResponseEntity<Guest>(guestService.saveGuest(newGuest), HttpStatus.OK);
		}catch(DataIntegrityViolationException exception) { 
			return new ResponseEntity<Guest>(new Guest(), HttpStatus.BAD_REQUEST);
    	}catch (Exception exception) {
    		return new ResponseEntity<Guest>(new Guest(), HttpStatus.BAD_GATEWAY);
		}
    }

    @ApiOperation(value = "Finds guest by id", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest found"),
            @ApiResponse(responseCode = "404", description = "Guest not found"),
            @ApiResponse(responseCode = "500", description = "Something went wrong"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@Parameter(description = "id of guest to be searched") @PathVariable Long id){
        try{
        	return new ResponseEntity<Guest>(guestService.getGuestById(id).get(), HttpStatus.OK);
        }catch(NoSuchElementException exception){
            return new ResponseEntity<Guest>(new Guest(), HttpStatus.NOT_FOUND);
        }catch (Exception exception) {
    		return new ResponseEntity<Guest>(new Guest(), HttpStatus.BAD_GATEWAY);
		}
    }

    @ApiOperation(value = "Updates a guest searching by id", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest was updated successfully"),
            @ApiResponse(responseCode = "400", description = "Guest's data is incomplete"),
            @ApiResponse(responseCode = "404", description = "Guest to update not found"),
            @ApiResponse(responseCode = "500", description = "Something went wrong"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@Parameter(description = "id of guest to be updated") @PathVariable Long id, @RequestBody Guest guest) {
        try{
            return new ResponseEntity<Guest>(guestService.updateGuest(id, guest), HttpStatus.OK);
        }catch(NoSuchElementException exception){
            return new ResponseEntity<Guest>(new Guest(), HttpStatus.NOT_FOUND);
        }catch(DataIntegrityViolationException exception) { 
			return new ResponseEntity<Guest>(new Guest(), HttpStatus.BAD_REQUEST);
    	}catch(Exception exception){
    		return new ResponseEntity<Guest>(new Guest(), HttpStatus.BAD_GATEWAY);
        }
    }

    @ApiOperation(value = "Deletes guest by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Guest to delete not found"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteGuest(@Parameter(description = "id of guest to be deleted") @PathVariable Long id){
        if(guestService.deleteGuest(id)) {
        	return new ResponseEntity<Boolean>(guestService.deleteGuest(id), HttpStatus.OK);
		}else{
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
    }
}
