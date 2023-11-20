package com.aspire.webapp.model;

public class Guest {
    private Long idGuest;
    private String name;
    private String email;
    private String phoneNumber;
    private String checkInDate;
    private String checkOutDate;
    private String typeGuest;
    
    public Guest() {
    	
    }
    
    public Guest(String name, String email, String phoneNumber, String checkInDate, String checkOutDate, String typeGuest) {
    	this.name = name;
    	this.email = email;
    	this.phoneNumber = phoneNumber;
    	this.checkInDate = checkInDate;
    	this.checkOutDate = checkOutDate;
    	this.typeGuest = typeGuest;
    }
    
    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }

    public void setTypeGuest(String typeGuest) { this.typeGuest = typeGuest; }

    public String getTypeGuest() { return typeGuest; }

    public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }

    public String getCheckInDate() { return checkInDate; }

    public void setCheckOutDate(String checkOutDate) { this.checkOutDate = checkOutDate; }

    public String getCheckOutDate() { return checkOutDate; }

    public Long getIdGuest() { return idGuest; }

    public void setIdGuest(Long idGuest) { this.idGuest = idGuest; }
}
