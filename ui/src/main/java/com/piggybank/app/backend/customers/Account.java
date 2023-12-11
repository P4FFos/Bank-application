package com.piggybank.app.backend.customers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.piggybank.app.backend.customers.debts.Credit;
import com.piggybank.app.backend.utils.TruncationUtil;

public class Account {
    // attributes for account class
    private String accountName;
    private final String accountId;
    private double balance;
    private ArrayList<Transaction> transactions;
	private Credit credit;

    // constructor for the account class, with initialised balance = 0
    public Account(String accountId, String accountName) {
        this.transactions = new ArrayList<>();
        this.accountName = accountName;
        this.accountId = accountId;
        this.balance = 0.0;
		this.transactions = new ArrayList<Transaction>();
		this.credit = null;
    }

    // get method to receive accountName
    public String getAccountName() {
        return this.accountName;
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
    public ArrayList<Transaction> getTransactionHistory() {
		return transactions;
    }

    // deposit methods, checks is the message is blank:
    // fill in message field with empty string
    public void deposit(String senderAccountId, double amount, String message, Date date) {
        balance += TruncationUtil.truncate(amount);
        if (message.isBlank()) {
            Transaction withdraw = new Transaction("", senderAccountId, amount, "", date);
            transactions.add(withdraw);
        } else {
            Transaction withdraw = new Transaction("", senderAccountId, amount, message, date);
            transactions.add(withdraw);
        }
    }

    // withdraw method, checks if balance is bigger or equal to the amount to be sent:
    // if bigger -> create new transaction and add it to the list
    // if lower -> throw exception
    public void withdraw(double amount, Date date) throws Exception {
        if (balance >= amount) {
            balance -= TruncationUtil.truncate(amount);
            Transaction withdraw = new Transaction("", "", amount, "", date);
            transactions.add(withdraw);
        } else {
            throw new Exception("");
        }
    }

	public void addCredit(Calendar initialCreditDate, double creditAmount) throws Exception{
		if(this.credit != null) {
			throw new Exception("Credit already exists");
		}
		this.credit = new Credit(accountId, accountName, initialCreditDate, creditAmount);
	}

	public Credit getCredit() {
		return this.credit;
	}

	public void removeCredit() throws Exception{
		if(this.credit == null){
			throw new Exception("Credit does not exist");
		}
		if(this.credit.getCreditAmountWithInterest() > balance){
			throw new Exception("Not enough money to pay off credit");
		}
		this.credit = null;
	}
}
