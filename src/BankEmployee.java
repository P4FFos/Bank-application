package src;

public abstract class BankEmployee extends User {
    public BankEmployee(String userId, String password, ContactCard contactCard) {
        super(userId, password, contactCard);
    }
}
