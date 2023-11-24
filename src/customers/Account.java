package src.customers;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    // attributes for account class
    final String accountId;
    private double balance;
    private ArrayList<Transaction> transactions;

    // constructor for the account class
    Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    // get methods to receive accountId and Balance
    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    // method to get history of transactions
    public ArrayList<Transaction> getHistory() {
        return transactions;
    }

    // deposit and withdraw methods
    public void deposit(String receiverAccountId, Date date, double amount) {
        balance += amount;
        Transaction deposit = new Transaction(receiverAccountId, amount, date);
        transactions.add(deposit);
    }

    public void withdraw(Date date, double amount) throws Exception {
        if (balance >= amount) {
            balance -= amount;
            Transaction withdraw = new Transaction(amount, date);
            transactions.add(withdraw);
        } else {
            throw new Exception("");
        }
    }

    // outgoing and incoming transactions methods
    public void outgoingTransaction(String receiverAccountId, double amount, Date date) throws Exception {
        if (balance >= amount) {
            balance -= amount;
            Transaction outgoing = new Transaction(receiverAccountId, amount, date);
            transactions.add(outgoing);
        } else {
            throw new Exception("");
        }
    }

    public void incomingTransaction(double amount, Date date) {
        balance += amount;
        Transaction incoming = new Transaction(amount, date);
        transactions.add(incoming);
    }
}
