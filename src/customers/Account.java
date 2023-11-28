package src.customers;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    // attributes for account class
    private final String accountId;
    private double balance;
    private ArrayList<Transaction> transactions;

    // constructor for the account class
    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    // get methods to receive accountId and Balance
    public String getAccountId() {
        return this.accountId;
    }

    public double getBalance() {
        return this.balance;
    }

    // method to get history of transactions
    public ArrayList<Transaction> getTransactionHistory() {
        //TODO: add a for loop to check
        return this.transactions;
    }

    // deposit and withdraw methods
    public void deposit(String senderAccountId, double amount, String message, Date date) {
        balance += amount;
        if (message.isBlank()) {
            Transaction withdraw = new Transaction("", senderAccountId, amount, "", date);
            transactions.add(withdraw);
        } else {
            Transaction withdraw = new Transaction("", "", amount, message, date);
            transactions.add(withdraw);
        }
    }

    public void withdraw(double amount, Date date) throws Exception {
        if (balance >= amount) {
            balance -= amount;

            Transaction withdraw = new Transaction("", "", amount, "", date);
            transactions.add(withdraw);
        } else {
            throw new Exception("");
        }
    }
}
