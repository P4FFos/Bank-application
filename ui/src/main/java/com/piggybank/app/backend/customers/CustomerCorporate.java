package com.piggybank.app.backend.customers;

import com.piggybank.app.backend.utils.ContactCard;

public class CustomerCorporate extends Customer {
    private final String orgNumber;
    private String companyName;

    // Constructor for CorporateCustomer class
    public CustomerCorporate(String orgNumber, String companyName, String userId, String password, ContactCard contactCard) throws Exception {
        super(userId, password, contactCard);
        this.orgNumber = orgNumber;
        this.companyName = companyName;
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
            boolean sameUserID = this.userId.equals(anotherCustomer.getUserId());
            isEqual = sameUserID;
        }
        return isEqual;
    }

}
