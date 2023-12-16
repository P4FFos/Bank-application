package com.piggybank.app.backend.customers;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.piggybank.app.backend.employees.Employee;
import com.piggybank.app.backend.exceptions.PasswordException;
import com.piggybank.app.backend.utils.ContactCard;

// Used by Jackson-Databind for handling Json files with abstract classes, specifying which subclass an object belong to
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Employee.class, name = "employee"),
        @JsonSubTypes.Type(value = Customer.class, name = "customer")
})
public abstract class User {
    // user attributes:
    private String userId;
    private String password;
    private ContactCard contactInfo;


    public User() {
    }

    // user object constructor:
    public User(String userId, String password, ContactCard contactInfo) throws PasswordException {
        this.userId = userId;
        this.contactInfo = contactInfo;
        setPassword(password);
    }

    // getters for user information:
    public String getUserId() {
        return userId;
    }
    public ContactCard getContactInfo() {
        return contactInfo;
    }
    public String getPassword() {
        return this.password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContactInfo(ContactCard contactInfo) {
        this.contactInfo = contactInfo;
    }

    // getters for ContactCard information
    public String getEmail() {return contactInfo.getEmail();}
    public String getPhoneNumber() {return contactInfo.getPhoneNumber();}
    public String getStreet() {return contactInfo.getStreetAddress();}
    public String getZipCode() {return contactInfo.getZipCode();}
    public String getCity() {return contactInfo.getCity();}

    // change password method which checks:
    // new password must be longer than 8 symbols,
    // new password must contain at least one capital letter and at least one digit
    public void setPassword(String newPassword) throws PasswordException {
        if (newPassword.length() >= 8 && newPassword.matches(".*[A-Z].*") && newPassword.matches(".*\\d.*")) {
            this.password = newPassword;
        } else {
            throw new PasswordException("Password has invalid format");
        }
    }

    // setters for ContactCard information
    public void setEmail(String newEmail) {
        contactInfo.setEmail(newEmail);
    }
    public void setPhoneNumber(String newPhoneNr) {contactInfo.setPhoneNumber(newPhoneNr);}
    public void setStreet(String newStreet) {
        contactInfo.setStreetAddress(newStreet);
    }
    public void setZipCode(String newZip) {contactInfo.setZipCode(newZip);}
    public void setCity(String newCity) {contactInfo.setCity(newCity);}
}
