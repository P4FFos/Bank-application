package src;

import src.customers.Account;
import src.customers.Customer;
import src.customers.Transaction;
import src.employees.BankEmployee;
import src.exceptions.AccountNotFoundException;
import src.exceptions.DuplicateIdException;
import src.utils.ContactCard;

import java.util.HashMap;

public class Bank {
    private ContactCard contactInfo;
    private HashMap<String, Customer> customers;
    private HashMap<String, BankEmployee> employees;

    //2 constructors if we can create a bank object without specifying its contact info upon creation:
    public Bank() {
        this.customers = new HashMap<String, Customer>();
        this.employees = new HashMap<String, BankEmployee>();
    }

    public Bank(ContactCard contactInfo) {
        this.contactInfo = contactInfo;
        this.customers = new HashMap<String, Customer>();
        this.employees = new HashMap<String, BankEmployee>();
    }

    //create methods for updating the bank's contact card info (via forwarding from ContactCard once those methods are in place)
    //public void updateAddress(String newAddress){} ...etc

    public ContactCard getBankInfo() {
        return this.contactInfo;
    }

    public void addCustomer(String userId, Customer customer) throws DuplicateIdException {
        if (customers.containsKey(userId)) {
            throw new DuplicateIdException("An account already exists with this number.");
        }
        this.customers.put(userId, customer);
    }

    public void removeCustomer(String employeeID, String customerID) throws AccountNotFoundException {
        if (!customers.containsKey(customerID)) {
            throw new AccountNotFoundException("Account not found.");
        } else {
            this.customers.remove(customerID);
        }
    }

    public Customer getCustomer(String userId) throws AccountNotFoundException {
        if (!customers.containsKey(userId)) {
            throw new AccountNotFoundException("Account not found.");
        } else {
            return this.customers.get(userId);
        }
    }

    public void createEmployee(String userId, BankEmployee employee) throws DuplicateIdException {
        if(employees.containsKey(userId)) {
            throw new DuplicateIdException("An account already exists with this number.");
        } else {
            this.employees.put(userId, employee);
        }
    }

    public void removeEmployee(String employeeId) throws AccountNotFoundException {
        if(!employees.containsKey(employeeId)) {
            throw new AccountNotFoundException("Account not found.");
        } else {
            this.employees.remove(employeeId);
        }
    }

    public Account getAccountById(String accountId) throws AccountNotFoundException {

    }

    public void deposit(String accountId, double amount, String message, String date) throws AccountNotFoundException{
        Account account = getAccountById(accountId);
        account.deposit(amount, message, date);
    }

    public void deposit(String accountId, double amount, String date) throws AccountNotFoundException{
        Account account = getAccountById(accountId);
        account.deposit(amount, date);
    }

    public void withdraw(String accountId, double amount, String message, String date) throws AccountNotFoundException{
        Account account = getAccountById(accountId);
        account.withdraw(amount, message, date);
    }

    public void withdraw(String accountId, double amount, String date) throws AccountNotFoundException{
        Account account = getAccountById(accountId);
        account.withdraw(amount, date);
    }

    public void transfer(String accountId, String targetAccountId, double amount, String message, String date) throws AccountNotFoundException {
        Account account = getAccountById(accountId);
        Account targetAccount = getAccountById(targetAccountId);

        account.withdraw(amount, message, date);
        targetAccount.deposit(amount, message, date);
    }

    public String getTransactionHistory(String accountId) throws AccountNotFoundException {
        Account account = getAccountById(accountId);
        String transactions = "";

        for(Transaction transaction: account.getTransactionHistory()) {
            transactions = String.format("%s%n%s", transactions, transaction.toString());
        }
        return transactions;
    }

    public double getBalance(String accountId) throws AccountNotFoundException {
        Account account = getAccountById(accountId);
        return account.getBalance();
    }
}
