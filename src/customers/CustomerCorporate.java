package src.customers;

import src.utils.ContactCard;

public class CustomerCorporate extends Customer {
    CustomerCorporate(String userId, String password, ContactCard contactCardCorporate) {
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
        } else {
            CustomerCorporate anotherCustomer = (CustomerCorporate) anotherObject;
            boolean sameUserID = this.userId.equals(anotherCustomer.getUserId());
        }
        return isEqual;
    }
}
