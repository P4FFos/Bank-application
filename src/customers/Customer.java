package src.customers;

import src.utils.ContactCard;
import java.util.HashMap;

public abstract class Customer extends User {
    private HashMap<String, Account> accounts;

    public Customer(String userId, String password, ContactCard contactCard) {
        super(userId, password, contactCard);
        this.accounts = new HashMap<>();
        userId = getUserId();
        contactCard = getContactInfo();
    }

    public Account getAccount(String accountId) throws Exception {
        if (accountId.equals(null)) {
            throw new Exception("");
        } else {
            return accounts.get(accountId);
        }
    }

}

