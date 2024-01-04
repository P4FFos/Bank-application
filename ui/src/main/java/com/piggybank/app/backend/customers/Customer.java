package com.piggybank.app.backend.customers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.piggybank.app.backend.utils.ContactCard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

// Used by Jackson-Databind for handling Json files with abstract classes, specifying which subclass an object belong to
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "customerType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerPrivate.class, name = "customerPrivate"),
        @JsonSubTypes.Type(value = CustomerCorporate.class, name = "customerCorporate")
})

public abstract class Customer extends User {

    // attributes:
    private HashMap<String, Account> accounts;

    // Bare constructor used by Jackson-Databind for Json deserializing
    public Customer() {}

    // Main constructor
    public Customer(String userId, String password, ContactCard contactCard) throws Exception {
        super(userId, password, contactCard);
        this.accounts = new HashMap<>();
    }

	//--------------------Getters--------------------
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public HashMap<String, Account> getAccounts(){
        return accounts;
    }

	@JsonIgnore
    public ObservableList<Account> getAccountsList() { // Used for displaying changes in customer accounts layout
        return FXCollections.observableArrayList(accounts.values());
    }

	//--------------------Methods--------------------
    public boolean checkIfAccountExists(String accountId) {
        return accounts.containsKey(accountId);
    }

    public void addAccount(Account account) {
        this.accounts.put(account.getAccountId(), account);
    }

    public void removeAccount(String accountId) {
        this.accounts.remove(accountId);
    }
}

