package src.customers;

import src.utils.ContactCard;

public class CustomerPrivate extends Customer {
    CustomerPrivate(String userId, String password, ContactCard contactCardCorporate) {
        super(userId, password, contactCardCorporate);
    }

    public boolean isEqual(CustomerPrivate anotherObject) {
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
