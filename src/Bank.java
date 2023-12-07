package src;

import src.customers.Account;
import src.customers.Customer;
import src.customers.CustomerCorporate;
import src.customers.CustomerPrivate;
import src.customers.Transaction;
import src.customers.debts.Credit;
import src.employees.BankEmployee;
import src.employees.BankTeller;
import src.exceptions.AccountNotFoundException;
import src.exceptions.DuplicateIdException;
import src.exceptions.UserNotFoundException;
import src.utils.ContactCard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Date;

public class Bank {
    private ContactCard contactInfo;
    private HashMap<String, Customer> customers;
    private HashMap<String, BankEmployee> employees;

    public Bank(ContactCard contactInfo) {
        this.contactInfo = contactInfo;
        customers = new HashMap<String, Customer>();
        employees = new HashMap<String, BankEmployee>();
    }

    public Bank() {
        contactInfo = null;
        customers = new HashMap<>();
        employees = new HashMap<>();
    }

    //create methods for updating the bank's contact card info (via forwarding from ContactCard once those methods are in place)
    //public void updateAddress(String newAddress){} ...etc

    //get bank information:
    public ContactCard getBankInfo() {
        return this.contactInfo;
    }

    // create new private customer and add it to the bank's hashmap:
    public void createCustomerPrivate(String SSN, String firstName, String lastName, String userId, String password, ContactCard contactCard) {
        CustomerPrivate newCustomer = new CustomerPrivate(SSN, firstName, lastName, userId, password, contactCard);
        this.customers.put(userId, newCustomer);
    }

    // create new corporate customer and add it to the bank's hashmap:
    public void createCustomerCorporate(String orgNumber, String companyName, String userId, String password, ContactCard contactCard) {
        CustomerCorporate newCustomer = new CustomerCorporate(orgNumber, companyName, userId, password, contactCard);
        this.customers.put(userId, newCustomer);
    }

    // remove customer from bank:
    public void removeCustomer(String employeeID, String customerID) {
        this.customers.remove(customerID);
    }

    // create new account for customer:
    public Account createAccount(String accountNumber, String accountName) {
        Account newAccount = new Account(accountNumber, accountName);
        return newAccount;
    }

    // add account to customer:
    public void addAccount(String userID, String accountId, Account newAccount) {
        Customer customer = customers.get(userID);
        customer.addAccount(newAccount);
    }

    //retrieve customer information:
    public Customer getCustomer(String userId) {
        return this.customers.get(userId);
    }

    //add new employee to bank:
    public void createEmployee(String userId, BankEmployee employee) {
        this.employees.put(userId, employee);
    }

    //remove employee from bank:
    public void removeEmployee(String employeeId) {
        this.employees.remove(employeeId);
    }

    /* iterates through HashMap of customers to find the owner of specified account,
    so it can be used in other methods in the class */
    public Account getAccountById(String accountId) throws Exception {
        for (Customer customer : customers.values()) {
            boolean accountExists = customer.checkIfAccountExists(accountId);
            if (accountExists) {
                return customer.getAccount(accountId);
            }
        }
        throw new AccountNotFoundException("Account ID " + accountId + " was not found.");
    }

    public Customer getCustomerByIdOrSSN(String inputString) throws Exception {
        /* Checks for 10 characters, if it is then SSN,
        otherwise if it is 6 characters then it is a userId
        */
        // add return
    }

    // deposits money into specified account
    public void deposit(String senderId, String accountId, double amount, String message, Date date) throws Exception {
        Account account = getAccountById(accountId);
        account.deposit(senderId, amount, message, date);
    }

    // withdraw money from specified account
    public void withdraw(String accountId, double amount, String message, Date date) throws Exception {
        Account account = getAccountById(accountId);
        account.withdraw(amount, date);
    }

    // transfer money between accounts; the actual account and a target account
    public void transfer(String accountId, String targetAccountId, double amount, String message, Date date) throws Exception {
        Account account = getAccountById(accountId);
        Account targetAccount = getAccountById(targetAccountId);

        account.withdraw(amount, date);
        targetAccount.deposit(accountId, amount, message, date);
    }

    // returns a string of all transactions in specified account
    public ArrayList<Transaction> getTransactionHistory(String accountId) throws Exception {
        Account account = getAccountById(accountId);
        return account.getTransactionHistory();
    }

    // returns the balance of the specified account
    public double getBalance(String accountId) throws Exception {
        Account account = getAccountById(accountId);
        return account.getBalance();
    }

    //verify customer login information:
    public boolean verifyCustomer(String userId, String password) throws Exception {
        boolean correctUserId = false;
        boolean correctPassword = false;
        if (!customers.containsKey(userId)) {
            throw new Exception("Invalid user ID or password. Please try again.");
        } else {
            correctUserId = true;
            Customer customer = customers.get(userId);
            if (!customer.getPassword().equals(password)) {
                throw new Exception("Invalid user ID or password. Please try again.");
            } else {
                correctPassword = true;
            }
            return correctUserId && correctPassword;
        }
    }

    //verify employee login information:
    public boolean verifyEmployee(String userId, String password) throws Exception {
        boolean correctUserId = false;
        boolean correctPassword = false;
        if (!employees.containsKey(userId)) {
            throw new Exception("Invalid user ID or password. Please try again.");
        } else {
            correctUserId = true;
            BankEmployee employee = employees.get(userId);
            if (!employee.getPassword().equals(password)) {
                throw new Exception("Invalid user ID or password. Please try again.");
            } else {
                correctPassword = true;
            }
        }
        return correctUserId && correctPassword;
    }

    // getCredit method
    public Credit getCredit(String userId, String accountId) {
        Customer customer = customers.get(userId);
		Account account = customer.getAccount(accountId);
		return account.getCredit();
    }

    // addCredit method
    public void addCredit(String userId, String accountId, Calendar initialCreditDate, double amount) throws Exception{
        Customer customer = customers.get(userId);
		Account account = customer.getAccount(accountId);
		account.addCredit(initialCreditDate, amount);
    }

    // removeCredit method
    public void removeCredit(String userId, String accountId) throws Exception{
        Customer customer = customers.get(userId);
		Account account = customer.getAccount(accountId);
		account.removeCredit();
    }
}
