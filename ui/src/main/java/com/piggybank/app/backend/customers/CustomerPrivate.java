package com.piggybank.app.backend.customers;

import com.piggybank.app.backend.utils.ContactCard;

public class CustomerPrivate extends Customer {
    private final String SSN;
    private String firstName;
    private String lastName;

    // Constructor for CustomerPrivate class
    public CustomerPrivate(String SSN, String firstName, String lastName, String userId, String password, ContactCard contactCard) throws Exception {
        super(userId, password, contactCard);
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
    }

    public String getSSN() {
        return SSN;
    }

    // equals method to check if two Private Customers
    // are equal by same user ID
    public boolean equals(CustomerPrivate anotherObject) {
        boolean isEqual = false;
        if (anotherObject == this) {
            isEqual = true;
        } else if (anotherObject == null) {
            isEqual = false;
        } else if (anotherObject instanceof CustomerPrivate) {
            CustomerPrivate anotherCustomer = (CustomerPrivate) anotherObject;
            boolean sameUserID = this.userId.equals(anotherCustomer.getUserId());
            isEqual = sameUserID;
        }
        return isEqual;
    }
}
