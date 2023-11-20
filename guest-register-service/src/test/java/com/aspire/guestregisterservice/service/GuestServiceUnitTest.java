package com.aspire.guestregisterservice.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aspire.guestregisterservice.models.Guest;
import com.aspire.guestregisterservice.repository.GuestRepository;

@ExtendWith(MockitoExtension.class)
public class GuestServiceUnitTest {
	
	@Mock
	private GuestRepository guestRepository;

	@InjectMocks
    private GuestService guestService;
    
	@BeforeEach
    public void init() {
    	MockitoAnnotations.openMocks(this);
    }
	
    @Test
    @DisplayName("Test mock creation")
    public void testMockCreation(){
        assertNotNull(guestRepository);
        assertNotNull(guestService);
    }
   
    @Test
    @DisplayName("Get all guest test")
    public void getAllGuestTest() throws Exception {
    	ArrayList<Guest> guests = new ArrayList<Guest>();
    	when(guestRepository.findAll()).thenReturn(guests);
    	guestService.getAllGuests();
        verify(guestRepository).findAll();
    }

    @Test
    @DisplayName("Save new guest test")
    public void saveGuestTest() throws Exception {
    	when(guestRepository.save(any(Guest.class))).thenReturn(new Guest());
    	guestService.saveGuest(new Guest());
    	verify(guestRepository).save(any(Guest.class));
    }
    
    @Test
    @DisplayName("Delete guest test")
    public void deleteGuestTest() throws Exception {
    	guestService.deleteGuest(5L);
    	verify(guestRepository, times(1)).deleteById(any());
    }
    
    @Test
    @DisplayName("Get guest by id test")
    public void getGuestByIdTest() throws Exception {
    	Guest demoGuest = new Guest();
    	when(guestRepository.findById(any())).thenReturn(Optional.of(demoGuest));
    	guestService.getGuestById(1L);
    	verify(guestRepository).findById(any());
    }
   
    @Test
    @DisplayName("Update guest test")
    public void updateGuestTest() throws Exception {
    	Guest demoGuest = new Guest();
    	when(guestRepository.findById(any())).thenReturn(Optional.of(demoGuest));
    	when(guestRepository.save(any(Guest.class))).thenReturn(demoGuest);
    	guestService.updateGuest(5L, demoGuest);
    	verify(guestRepository, times(1)).save(any(Guest.class));
    }
    
}
