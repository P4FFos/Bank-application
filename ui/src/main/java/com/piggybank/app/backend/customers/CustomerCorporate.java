package com.piggybank.app.backend.customers;

import com.piggybank.app.backend.utils.ContactCard;

public class CustomerCorporate extends Customer {

	// attributes:
    private String orgNumber;
    private String companyName;

    // Bare constructor used by Jackson-Databind for Json deserializing
    public CustomerCorporate() {}

	// Main constructors
    public CustomerCorporate(String orgNumber, String companyName, String userId, String password, ContactCard contactCard) throws Exception {
        super(userId, password, contactCard);
        this.orgNumber = orgNumber;
        this.companyName = companyName;
    }

	//--------------------Getters--------------------
	public String getOrgNumber() {
        return this.orgNumber;
    }
	public String getCompanyName() {
        return this.companyName;
    }

	//--------------------Setters--------------------
    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

	//--------------------Methods--------------------
    public boolean equals(CustomerCorporate anotherObject) { // checks for equal userId
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
