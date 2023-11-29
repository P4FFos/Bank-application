package src.customers;

import src.utils.ContactCardPrivate;

public class CustomerPrivate extends Customer {
    // Constructor for CustomerPrivate class
    public CustomerPrivate(String userId, String password, ContactCardPrivate contactCardCorporate) {
        super(userId, password, contactCardCorporate);
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
