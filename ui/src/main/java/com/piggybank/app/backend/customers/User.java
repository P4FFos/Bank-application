package com.piggybank.app.backend.customers;

import com.piggybank.app.backend.exceptions.PasswordException;
import com.piggybank.app.backend.utils.ContactCard;

public abstract class User {
    // user attributes
    final String userId;
    private String password;
    private ContactCard contactInfo;

    // user class constructor:
    // checks if password length in bigger than 8 symbols,
    // if it contains at least one capital letter and at least one digit
    public User(String userId, String password, ContactCard contactInfo) throws Exception {
        this.userId = userId;
        this.contactInfo = contactInfo;
        if (password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*\\d.*")) {
            this.password = password;
        } else {
            throw new Exception("Invalid password format");
        }
    }

    // get user Id method
    public String getUserId() {
        return userId;
    }

    // get contact Info method
    public ContactCard getContactInfo() {
        return contactInfo;
    }

    // get password method
    public String getPassword() {
        return this.password;
    }

    // change password method which checks:
    // if new password length in bigger than 8 symbols,
    // if it contains at least one capital letter and at least one digit
    public void changePassword(String newPassword) throws PasswordException {
        if (newPassword.length() >= 8 && newPassword.matches(".*[A-Z].*") && newPassword.matches(".*\\d.*")) {
            this.password = newPassword;
        } else {
            throw new PasswordException("New password has invalid format");
        }
    }

    // updates the user's street address:
    public void setStreet(String newStreet) {
        contactInfo.setStreetAddress();
    }

    // updates the user's email:
    public void setEmail(String newEmail) {
        contactInfo.setEmail();
    }

    //create methods for updating contact info (via forwarding from ContactCard once those methods are in place)
}
