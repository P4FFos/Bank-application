package com.piggybank.app.backend.customers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piggybank.app.backend.utils.ContactCard;

public class CustomerPrivate extends Customer {

	// attributes:
    private String ssn;
    private String firstName;
    private String lastName;
    private String initials;

    // Bare constructor used by Jackson-Databind for Json deserializing
    public CustomerPrivate() {}

    // Main constructor
    public CustomerPrivate(String ssn, String firstName, String lastName, String userId, String password, ContactCard contactCard) throws Exception {
        super(userId, password, contactCard);
		this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        setInitials();
    }

	//--------------------Getters--------------------
	public String getSsn() {
        return ssn;
    }

	public String getFirstName() {
        return firstName;
    }

	public String getLastName() {
        return lastName;
    }

	public String getInitials(){
		return this.initials;
	}

	//--------------------Setters--------------------
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public void setInitials() {
        initials = firstName.substring(0, 1).toUpperCase() + lastName.substring(0, 1).toUpperCase();
    }

	//--------------------Methods--------------------

    @JsonIgnore
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean equals(CustomerPrivate anotherObject) { // checks for equal userId
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

    @Override
    public String toString() {
        return String.format("userId: %s, firstName: %s, lastName: %s, ssn: %s", getUserId(), firstName, lastName, ssn);
    }
}
