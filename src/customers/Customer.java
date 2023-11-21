package src.customers;

import src.utils.ContactCard;

public abstract class Customer extends User {
    private ContactCard contactInfo;
    public Customer(String userId, String password, ContactCard contactCard) {
        super(userId, password, contactCard);

    }
    public abstract boolean equals(Object otherObject);
}
