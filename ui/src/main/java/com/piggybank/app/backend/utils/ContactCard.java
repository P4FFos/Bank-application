package com.piggybank.app.backend.utils;

public class ContactCard {
    private String name;
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private String zipCode;
    private String city;

    // Bare constructor used by Jackson-Databind for Json deserializing
    public ContactCard() {
    }

    public ContactCard(String email, String phoneNumber, String streetAddress, String zipCode, String city) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s", name, email, phoneNumber, streetAddress, zipCode, city);
    }
}

