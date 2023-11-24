package src.customers;

import src.utils.ContactCard;

import java.util.HashMap;

public abstract class Customer extends User {
    private HashMap<accountId, Account> accounts;

    Customer(String userId, String password, ContactCard contactCard) {
        super(userId, password, contactCard);
        this.accounts = new HashMap<>;
        userId = getUserId();
        contactCard = getContactInfo();
    }

    public Account getAccount(accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

}
