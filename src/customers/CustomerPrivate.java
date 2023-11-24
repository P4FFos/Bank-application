package src.customers;

import src.utils.ContactCard;

public class CustomerPrivate extends Customer {
    CustomerPrivate(String userId, String password, ContactCard contactCardCorporate) {
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
        } else {
            CustomerPrivate anotherCustomer = (CustomerPrivate) anotherObject;
            boolean sameUserID = this.userId.equals(anotherCustomer.getUserId());
        }
        return isEqual;
    }
}
