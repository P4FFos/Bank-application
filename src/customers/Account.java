package src.customers;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    final String accountId;
    private double balance;
    private ArrayList<Transaction> transactions;

    Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getHistory() {
        return transactions;
    }

    public void deposit(String receiverAccountId, Date date, double amount) {
        balance += amount;
        Transaction deposit = new Transaction(receiverAccountId, amount, date);
        transactions.add(deposit);
    }

    public void withdraw(Date date, double amount) throws Exception {
        if (balance >= amount) {
            balance = balance - amount;
            Transaction withdraw = new Transaction(amount, date);
            transactions.add(withdraw);
        } else {
            throw new Exception("");
        }
    }

    public void outgoingTransaction(String receiverAccountId, double amount, String date) {

    }

    public void incomingTransaction(double amount, String date) {

    }
}
