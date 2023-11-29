package src.customers;

import src.utils.ContactCard;
import java.util.HashMap;

public abstract class Customer extends User {
    // HashMap to store accounts
    private HashMap<String, Account> accounts;

    // constructor for Customer class
    public Customer(String userId, String password, ContactCard contactCard) {
        super(userId, password, contactCard);
        this.accounts = new HashMap<>();
    }

    // getAccount method to receive account by ID
    // checks if account ID is blank
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

