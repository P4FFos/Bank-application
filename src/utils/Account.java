package src.utils;

import src.Bank;
import src.exceptions.AccountNotFoundException;

import java.util.ArrayList;
public class Account {
    private final String accountId;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        transactions = new ArrayList<>();
    }

    public String getAccountId(){
        return accountId;
    }

    public double getBalance(){
        return balance;
    }

    public ArrayList<Transaction> getTransactionHistory(){
        return transactions;
    }

    public void withdraw(double amount, String date){
        if(balance >= amount){
            balance = balance - amount;
            Transaction withdrawal = new Transaction(accountId, amount, date);
            transactions.add(withdrawal);
        }
    }
    public void deposit(double amount, String message, String date){
        balance += amount;
        Transaction deposit = new Transaction(accountId, amount, message, date);
        transactions.add(deposit);
    }
    public void deposit(double amount, String date){
        balance += amount;
        Transaction deposit = new Transaction(accountId, amount, date);
        transactions.add(deposit);
    }
    
    /* TODO: Decide what to do.
        Should all operations go through Bank class or to use aggregation in each method?
        - Aggregation feels clunky.*/
    public void transfer(Bank bank, String targetAccountId, double amount, String message, String date) throws AccountNotFoundException {
        if(balance >= amount){
            this.withdraw(amount, date);
            Account targetAccount = bank.findAccountById(targetAccountId);
            targetAccount.deposit(amount, message, date);
        }
    }
}
