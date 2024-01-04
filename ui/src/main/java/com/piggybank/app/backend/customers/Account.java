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

    // attributes:
    private String accountName;
    private String accountId;
    private double balance;
    private String balanceString;
    private ArrayList<Transaction> transactions;

	// Bare constructor used by Jackson-Databind for Json deserializing
    public Account() {}

    // Main constructor
    public Account(String accountId, String accountName) {
        this.accountName = accountName;
        this.accountId = accountId;
        this.balance = 0.0;
        this.balanceString = "0.00";
		this.transactions = new ArrayList<>();
    }

	//--------------------Getters--------------------
    public String getAccountName() {
        return this.accountName;
    }
    public String getBalanceString() {return this.balanceString;}

    // get method to receive accountId
    public String getAccountId() {
        return this.accountId;
    }

    // get method to receive balance
    public double getBalance() {
        return TruncationUtil.truncate(this.balance);
    }

    public ObservableList<Transaction> getTransactions() {
        return FXCollections.observableArrayList(transactions);
    }

    @JsonProperty("transactions")
    public ArrayList<Transaction> getTransactionHistory() {
        return transactions;
    }

    //--------------------Setters--------------------
    public void setBalanceString() {this.balanceString = String.format("%.2f", this.balance);}

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
        setBalanceString();
    }

    //--------------------Methods--------------------

    // deposit methods, checks is the message is blank:
    // fill in message field with empty string
    public void deposit(String senderAccountId, double amount, String message, LocalDate date) throws Exception {
        if(amount < 0) {
            throw new InsufficientBalanceException("Amount specified cannot be less than 0.");
        }
        balance += TruncationUtil.truncate(amount);
        setBalanceString();
        if (message.isBlank()) {
            Transaction withdraw = new Transaction(accountId, senderAccountId, amount, "", date);
            transactions.add(withdraw);
        } else {
            Transaction withdraw = new Transaction(accountId, senderAccountId, amount, message, date);
            transactions.add(withdraw);
        }
    }


	// withdraw method with receiverAccountId
    public void withdraw(String receiverAccountId, double amount, String message, LocalDate date) throws Exception {
        if (balance >= amount && amount > 0) { // checks if the balance is enough for the operation
            balance -= TruncationUtil.truncate(amount);
            Transaction withdraw = new Transaction(receiverAccountId, accountId, 0 - amount, message, date);
            transactions.add(withdraw);
            setBalanceString();
        } else {
            throw new InsufficientBalanceException("Not enough balance in account for this operation.");
        }
    }

	// withdraw method without receiverAccountId
    public void withdraw(double amount, String message, LocalDate date) throws Exception {
        if (balance >= amount && amount > 0) { // checks if the balance is enough for the operation
            balance -= TruncationUtil.truncate(amount);
            Transaction withdraw = new Transaction("None", accountId, 0 - amount, message, date);
            transactions.add(withdraw);
            setBalanceString();
        } else {
            throw new InsufficientBalanceException("Not enough balance in account for this operation.");
        }
    }
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}