package com.piggybank.app.backend.utils;

import com.piggybank.app.backend.exceptions.InvalidEmailException;

public class ContactCard {
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private String zipCode;
    private String city;

    // Bare constructor used by Jackson-Databind for Json deserializing
    public ContactCard() {
    }

    public ContactCard(String email, String phoneNumber, String streetAddress, String zipCode, String city) throws Exception {
        this.email = validateEmail(email);
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        this.email = validateEmail(email);
    }

    public String validateEmail(String emailAddress) throws Exception {
        boolean isAddressValid = emailAddress.matches("^(.+)@(\\S+)$"); // regex to match email. Checks if @ is present
        if(!isAddressValid) {
            throw new InvalidEmailException("Email address is not valid.");
        }
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String newZip) {
        this.zipCode = newZip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String toString() {
        return String.format("email: %s, phoneNumber: %s, streetAddress: %s, zipCode: %s, city: %s", email, phoneNumber, streetAddress, zipCode, city);
    }
}

