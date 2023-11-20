package com.aspire.guestregisterservice.service;

import com.aspire.guestregisterservice.models.Guest;
import com.aspire.guestregisterservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public Guest saveGuest(Guest guest) throws Exception{
    	try {
    		return guestRepository.save(guest);
    	}catch(DataIntegrityViolationException exception) {
    		throw new DataIntegrityViolationException("Unregistered guest, all fields are required. Verify the guest's information.");
    	}catch (Exception exception) {
    		throw new Exception("Something went wrong, try again.");	
    	}
        
    }
    
    public ArrayList<Guest> getAllGuests() throws Exception{
    	return (ArrayList<Guest>) guestRepository.findAll();	
    }

    public Optional<Guest> getGuestById(Long id) throws Exception {
        try {
     	   return guestRepository.findById(id);
        }catch(NoSuchElementException exception) {
     	   throw new NoSuchElementException("Guest with ID " + id + " doesn't exist");
        }catch(Exception exception) {
     	   throw new Exception("Something went wrong, try again.");
        }
     }

    public Guest updateGuest(Long id, Guest guest) throws Exception {
    	try {
    		Guest guestToUpdate = guestRepository.findById(id).get();
            guestToUpdate.setName(guest.getName());
            guestToUpdate.setEmail(guest.getEmail());
            guestToUpdate.setPhoneNumber(guest.getPhoneNumber());
            guestToUpdate.setTypeGuest(guest.getTypeGuest());
            guestToUpdate.setCheckInDate(guest.getCheckInDate());
            guestToUpdate.setCheckOutDate(guest.getCheckOutDate());
            return guestRepository.save(guestToUpdate);
    	}catch(NoSuchElementException exception) {
    		throw new NoSuchElementException("Guest to update doesn't exist.");
    	}catch(DataIntegrityViolationException exception) {
    		throw new DataIntegrityViolationException("All fields are required to update. Verify the guest's information.");
    	}catch(Exception exception) {
    		throw new Exception("Something went wrong");
    	}  
    }

    public boolean deleteGuest(Long id){
        try {
            guestRepository.deleteById(id);
            return true;
        }catch (Exception exception){
        	return false;
        }
    }


}
