import src.customers.Account;
import src.customers.Customer;
import src.customers.Transaction;
import src.employees.BankEmployee;
import src.exceptions.AccountNotFoundException;
import src.utils.ContactCard;

import java.util.HashMap;

public class Bank {
    private ContactCard contactInfo;
    private HashMap<String, Customer> customers;
    private HashMap<String, BankEmployee> employees;

    public Bank(ContactCard contactInfo) {
        this.contactInfo = contactInfo;
        this.customers = new HashMap<String, Customer>();
        this.employees = new HashMap<String, BankEmployee>();
    }

    public ContactCard getBankInfo() {

    }

    public void addCustomer(Customer Customer) {

    }

    public void removeCustomer(String employeeID, String customerID) {

    }

    public Customer getCustomer(String userId) {

    }

    public void createEmployee(BankEmployee employee) {

    }

    public void removeEmployee(String employeeId) {

    }

    public Account getAccountById(String accountId) {

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
