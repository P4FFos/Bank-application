package com.piggybank.app.backend.customers;

import com.piggybank.app.backend.exceptions.PasswordException;
import com.piggybank.app.backend.utils.ContactCard;

public abstract class User {
    // user attributes:
    final String userId;
    private String password;
    private ContactCard contactInfo;

    // user class constructor:
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

    // getters for ContactCard information
    public String getEmail() {return contactInfo.getEmail();}
    public String getPhoneNumber() {return contactInfo.getPhoneNumber();}
    public String getStreet() {return contactInfo.getStreetAddress();}
    public String getZipCode() {return contactInfo.getZipCode();}
    public String getCity() {return contactInfo.getCity();}

    // change password method which checks:
    // if new password length in bigger than 8 symbols,
    // if it contains at least one capital letter and at least one digit
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
