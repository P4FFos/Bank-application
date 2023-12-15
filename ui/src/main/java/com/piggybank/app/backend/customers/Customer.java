package com.piggybank.app.backend.customers;

import com.piggybank.app.backend.utils.ContactCard;

import java.util.HashMap;

public abstract class Customer extends User {
    // HashMap to store accounts
    private HashMap<String, Account> accounts;

    // constructor for Customer class
    public Customer(String userId, String password, ContactCard contactCard) throws Exception {
        super(userId, password, contactCard);
        this.accounts = new HashMap<>();
    }

    // getAccount method to receive account by ID
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public HashMap<String, Account> getAccounts(){
        return accounts;
    }

    // checks if account exist in HashMap and returns boolean value
    public boolean checkIfAccountExists(String accountId) {
        return accounts.containsKey(accountId);
    }

    // addCustomer method
    public void addAccount(Account account) {
        this.accounts.put(account.getAccountId(), account);
    }

    // removeCustomer method
    public void removeAccount(String accountId) {
        this.accounts.remove(accountId);
    }

    public void setZipCode(String newZip) {
    }
}

