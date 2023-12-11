package com.piggybank.app.backend;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.customers.debts.Credit;
import com.piggybank.app.backend.exceptions.AccountNotFoundException;
import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.Transaction;
import com.piggybank.app.backend.employees.Employee;
import com.piggybank.app.backend.utils.ContactCard;
import com.piggybank.app.backend.utils.IdGenerator;

import java.util.*;

public class Bank {
    private ContactCard contactInfo;
    private HashMap<String, Customer> customers;
    private HashMap<String, Employee> employees;
    private String employeeIdCounter;
    private String customerIdCounter;
    private String accountIdCounter;


    public Bank(ContactCard contactInfo) {
        this.contactInfo = contactInfo;
        customers = new HashMap<String, Customer>();
        employees = new HashMap<String, Employee>();
        employeeIdCounter = "e00000";
        customerIdCounter = "c00000";
        accountIdCounter = "a000000000";
    }

    // To simplify instantiation the Bank class
    public Bank() {
        contactInfo = null;
        customers = new HashMap<>();
        employees = new HashMap<>();
        employeeIdCounter = "e00000";
        customerIdCounter = "c00000";
        accountIdCounter = "a000000000";
    }

    //create methods for updating the bank's contact card info (via forwarding from ContactCard once those methods are in place)
    //public void updateAddress(String newAddress){} ...etc

    public void setEmployeeIdCounter(String employeeId) {employeeIdCounter = employeeId;};
    public void setCustomerIdCounter(String customerId) {customerIdCounter = customerId;};
    public void setAccountIdCounter(String accountId) {accountIdCounter = accountId;};

    //get bank information:
    public ContactCard getBankInfo() {
        return this.contactInfo;
    }

    // create new private customer and add it to the bank's hashmap:
    public void createCustomerPrivate(String SSN, String firstName, String lastName, String password, ContactCard contactCard) throws Exception {
        // Generates a new ID for a customer, then updates customerIdCounter
        String userId = IdGenerator.generateCustomerID(customerIdCounter);
        setCustomerIdCounter(userId);

        CustomerPrivate newCustomer = new CustomerPrivate(SSN, firstName, lastName, userId, password, contactCard);
        this.customers.put(userId, newCustomer);
    }

    // A placeholder method for createCustomerPrivate. IdGenerator needs updated logic (check generateAccountId) /Marcus
    public void createCustomerPrivate(String userId, String SSN, String firstName, String lastName, String password, ContactCard contactCard) throws Exception {
        CustomerPrivate newCustomer = new CustomerPrivate(SSN, firstName, lastName, userId, password, contactCard);
        this.customers.put(userId, newCustomer);
    }

    // create new corporate customer and add it to the bank's hashmap:
    public void createCustomerCorporate(String orgNumber, String companyName, String userId, String password, ContactCard contactCard) throws Exception {
        CustomerCorporate newCustomer = new CustomerCorporate(orgNumber, companyName, userId, password, contactCard);
        this.customers.put(userId, newCustomer);
    }

    // remove customer from bank:
    public void removeCustomer(String employeeID, String customerID) {
        this.customers.remove(customerID);
    }


    // creates a new account for customer:
    public void createAccount(String userID, String accountName) throws Exception {
        Customer customer = customers.get(userID);

        //Generates accountId
        String accountId = IdGenerator.generateAccountId(accountIdCounter);
        setAccountIdCounter(accountId);

        Account newAccount = new Account(accountId, accountName);
        customer.addAccount(newAccount);
    }

    //retrieve customer information:
    public Customer getCustomer(String userId) {
        return this.customers.get(userId);
    }

    //add new employee to bank:
    public void createEmployee(String userId, String password, ContactCard contactCard) throws Exception {
		Employee newEmployee = new Employee(userId, password, contactCard);
		this.employees.put(userId, newEmployee);
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

    //
    public Customer getCustomerByIdOrSSN(String inputString) throws Exception {
        /* Checks for 10 characters, if it is then SSN,
        otherwise if it is 6 characters then it is a userId
        */

        /* Loops over the HashMap of customers if string is 10 characters,
         return Customer if it is a match */
        if(inputString.length() == 10) {
            for(Customer customer : customers.values()) {
                if(customer instanceof CustomerPrivate privateCustomer) {
                    if(privateCustomer.getSSN().equals(inputString)) {
                        return customer;
                    }
                }
            }
        // If a string has length 6 it is a userId, return Customer based on Id
        } else if (inputString.length() == 6) {
            return  customers.get(inputString);
        }
        throw new NoSuchElementException("Customer not found by ID or SSN.");
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
            Employee employee = employees.get(userId);
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
