package src.customers;

import src.utils.ContactCard;
import java.util.HashMap;

public abstract class Customer extends User {
    private HashMap<String, Account> accounts;

    public Customer(String userId, String password, ContactCard contactCard) {
        super(userId, password, contactCard);
        this.accounts = new HashMap<>();
    }

    public Account getAccount(String accountId) throws Exception {
        if (accountId.isBlank()) {
            throw new Exception("");
        } else {
            return accounts.get(accountId);
        }
    }

    //TODO new methods - need to fill logic:
    public void addAccount() {
    }

    public void removeAccount() {
    }

}

