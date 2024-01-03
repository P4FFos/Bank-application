package com.piggybank.app.backend.customers;

import com.piggybank.app.backend.utils.ContactCard;

public class CustomerCorporate extends Customer {
    private String orgNumber;
    private String companyName;

    // Bare constructor used by Jackson-Databind for Json deserializing
    public CustomerCorporate() {}

    public CustomerCorporate(String userId, String password, ContactCard contactCard) throws Exception {
        super(userId, password, contactCard);
    }

    // Constructor for CorporateCustomer class
    public CustomerCorporate(String orgNumber, String companyName, String userId, String password, ContactCard contactCard) throws Exception {
        super(userId, password, contactCard);
        this.orgNumber = orgNumber;
        this.companyName = companyName;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    // retrieve company name
    public String getCompanyName() {
        return this.companyName;
    }

    // retrieve organization number
    public String getOrgNumber() {
        return this.orgNumber;
    }

    // equals method to check if two Corporate Customers
    // are equal by same user ID
    public boolean equals(CustomerCorporate anotherObject) {
        boolean isEqual = false;
        if (anotherObject == this) {
            isEqual = true;
        } else if (anotherObject == null) {
            isEqual = false;
        } else if (anotherObject instanceof CustomerCorporate) {
            CustomerCorporate anotherCustomer = (CustomerCorporate) anotherObject;
            boolean sameUserID = this.getUserId().equals(anotherCustomer.getUserId());
            isEqual = sameUserID;
        }
        return isEqual;
    }

}
