package com.piggybank.app.backend.customers;

import com.piggybank.app.backend.utils.ContactCard;

public class CustomerPrivate extends Customer {
    private String ssn;
    private String firstName;
    private String lastName;
    private String initials;

    // Bare constructor used by Jackson-Databind for Json deserializing
    public CustomerPrivate() {}

    // Constructor for CustomerPrivate class
    public CustomerPrivate(String ssn, String firstName, String lastName, String userId, String password, ContactCard contactCard) throws Exception {
        super(userId, password, contactCard);
        this.firstName = firstName;
        this.lastName = lastName;
        setInitials();
        this.ssn = ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // retrieve customer's full name
    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setInitials() {
        initials = firstName.substring(0, 1).toUpperCase() + lastName.substring(0, 1).toUpperCase();

    }

    public String getInitials() {return initials;}

    // retrieve customer's SSN
    public String getSsn() {
        return ssn;
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
            boolean sameUserID = this.getUserId().equals(anotherCustomer.getUserId());
            isEqual = sameUserID;
        }
        return isEqual;
    }
}
