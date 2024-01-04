
package src.customers;

import src.utils.ContactCard;

public class CustomerCorporate extends Customer {
    CustomerCorporate(String userId, String password, ContactCard contactCardCorporate) {
        super(userId, password, contactCardCorporate);
    }

    public boolean isEqual(CustomerCorporate anotherObject) {
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