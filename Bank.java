import src.customers.Account;
import src.customers.Customer;
import src.employees.BankEmployee;
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

    public void deposit(String accountId, double amount, String date) {

    }

    public void withdraw(String accountId, double amount, String date) {

    }

    public void transfer(String accountId, String targetAccountId, double amount, String date) {

    }

    public String getTransactionHistory(String accountId) {
        return "";
    }

    public double getBalance() {
        return 0.0;
    }
}
