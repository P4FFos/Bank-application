package com.piggybank.app.backend.utils;

public class ContactCard {
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private String zipCode;
    private String city;

    // Bare constructor used by Jackson-Databind for Json deserializing
    public ContactCard() {
    }
    //Contact card constructor
    public ContactCard(String email, String phoneNumber, String streetAddress, String zipCode, String city) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
    }
    //"Getters" methods
    public String getEmail() {
        return email;    
    }
     public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
     public String getZipCode() {
        return zipCode;
    }
     public String getCity() {
        return city;
    }
    //"Setters" methods
    public void setEmail(String email) throws Exception {
        boolean isAddressValid = email.matches("^(.+)@(\\S+)$"); // regex to match email. Checks if @ is present
        if(isAddressValid) {
            this.email = email;
        } else {
            throw new Exception("Email address is not valid.");
        }
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public void setZipCode(String newZip) {
        this.zipCode = newZip;
    }
    public void setCity(String city) {
        this.city = city;
    }
    //ToString method for ContactCard
    public String toString() {
        return String.format("email: %s, phoneNumber: %s, streetAddress: %s, zipCode: %s, city: %s", email, phoneNumber, streetAddress, zipCode, city);
    }
}

