package src.customers;

import src.utils.ContactCardCorporate;

public class CustomerCorporate extends Customer {
    // Constructor for CorporateCustomer class
    public CustomerCorporate(String userId, String password, ContactCardCorporate contactCardCorporate) {
        super(userId, password, contactCardCorporate);
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
