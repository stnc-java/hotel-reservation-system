package com.aspire.guestregisterservice.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idGuest;
    @Column(nullable = false)
    @NotBlank(message="Name cannot be null.")
    private String name;
    @Column(nullable = false)
    @NotBlank(message="Email cannot be null.")
    private String email;
    @Column(nullable = false)
    @NotBlank(message="Phone number cannot be null.")
    private String phoneNumber;
    @Column(nullable = false)
    @NotBlank(message="Check in date cannot be null.")
    private String checkInDate;
    @Column(nullable = false)
    @NotBlank(message="Check out date cannot be null.")
    private String checkOutDate;
    @Column(nullable = false)
    @NotBlank(message="Type guest cannot be null.")
    private String typeGuest;

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
