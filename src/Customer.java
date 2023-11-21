package src;

public abstract class Customer extends User {
    public Customer(String userId, String password, ContactCard contactCard) {
        super(userId, password, contactCard);
    }
    public abstract boolean equals(Object otherObject);
}
