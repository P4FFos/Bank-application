package com.piggybank.app.backend.customers;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.piggybank.app.backend.customers.money_operations.Credit;
import com.piggybank.app.backend.customers.money_operations.Loan;
import com.piggybank.app.backend.customers.money_operations.Transaction;
import com.piggybank.app.backend.exceptions.InsufficientBalanceException;
import com.piggybank.app.backend.utils.TruncationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "accountType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Loan.class, name = "loan"),
        @JsonSubTypes.Type(value = Credit.class, name = "credit"),
        @JsonSubTypes.Type(value = Account.class, name = "account")
})
public class Account {
    // attributes for account class
    private String accountName;
    private String accountId;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account() {
    }

    // constructor for the account class, with initialised balance = 0
    public Account(String accountId, String accountName) {
        this.accountName = accountName;
        this.accountId = accountId;
        this.balance = 0.0;
		this.transactions = new ArrayList<>();
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    // get method to receive accountName
    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ObservableList<Transaction> getTransactions() {
        return FXCollections.observableArrayList(transactions);
    }

    // get method to receive accountId
    public String getAccountId() {
        return this.accountId;
    }

    // get method to receive balance
    public double getBalance() {
        return TruncationUtil.truncate(this.balance);
    }

    // method to get history of transactions
    @JsonProperty("transactions")
    public ArrayList<Transaction> getTransactionHistory() {
		return transactions;
    }

    // deposit methods, checks is the message is blank:
    // fill in message field with empty string
    public void deposit(String senderAccountId, double amount, String message, LocalDate date) throws Exception {
        if(amount < 0) {
            throw new InsufficientBalanceException("Amount specified cannot be less than 0.");
        }
        balance += TruncationUtil.truncate(amount);
        if (message.isBlank()) {
            Transaction withdraw = new Transaction(accountId, senderAccountId, amount, "", date);
            transactions.add(withdraw);
        } else {
            Transaction withdraw = new Transaction(accountId, senderAccountId, amount, message, date);
            transactions.add(withdraw);
        }
    }

    // withdraw method, checks if balance is bigger or equal to the amount to be sent:
    // if bigger -> create new transaction and add it to the list
    // if lower -> throw exception

    //used in conjunction with transfer, when you want receiverAccountId to show
    public void withdraw(String receiverAccountId, double amount, String message, LocalDate date) throws Exception {
        if (balance >= amount && amount > 0) {
            balance -= TruncationUtil.truncate(amount);
            Transaction withdraw = new Transaction(receiverAccountId, accountId, 0 - amount, message, date);
            transactions.add(withdraw);
        } else {
            throw new InsufficientBalanceException("Not enough balance in account for this operation.");
        }
    }
    // used for only withdrawing
    public void withdraw(double amount, String message, LocalDate date) throws Exception {
        if (balance >= amount && amount > 0) {
            balance -= TruncationUtil.truncate(amount);
            Transaction withdraw = new Transaction("None", accountId, 0 - amount, message, date);
            transactions.add(withdraw);
        } else {
            throw new InsufficientBalanceException("Not enough balance in account for this operation.");
        }
    }
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}