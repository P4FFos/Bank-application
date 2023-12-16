package com.piggybank.app.backend;

import com.piggybank.app.backend.customers.*;
import com.piggybank.app.backend.customers.debts.*;
import com.piggybank.app.backend.employees.*;
import com.piggybank.app.backend.exceptions.*;
import com.piggybank.app.backend.utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import java.time.LocalDate;
import java.util.NoSuchElementException;

public class Bank {
    private ContactCard contactInfo;
    private HashMap<String, Customer> customers;
    private HashMap<String, Employee> employees;
    private String employeeIdCounter;
    private String customerIdCounter;
    private String accountIdCounter;

    // Bare constructor used by Jackson-Databind for Json deserializing
    public Bank() {}

    public Bank(ContactCard contactInfo) {
        this.contactInfo = contactInfo;
        customers = new HashMap<>();
        employees = new HashMap<>();
        employeeIdCounter = "E000";
        customerIdCounter = "C000";
        accountIdCounter = "A00000";
    }

    //-----------------------GETTERS-----------------------
    public String getCustomerIdCounter() {return customerIdCounter;}
    public String getEmployeeIdCounter() {return employeeIdCounter;}
    public String getAccountIdCounter() {return accountIdCounter;}

    //retrieve customer information:
    public Customer getCustomer(String userId) {
        return this.customers.get(userId);
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
    public Customer getCustomerByIdOrSsn(String inputString) throws Exception {
        /* Checks for 10 characters, if it is then SSN,
        otherwise if it is 6 characters then it is a userId
        */

        /* Loops over the HashMap of customers if string is 10 characters,
         return Customer if it is a match */
        if(inputString.length() == 10) {
            for(Customer customer : customers.values()) {
                if(customer instanceof CustomerPrivate privateCustomer) {
                    if(privateCustomer.getSsn().equals(inputString)) {
                        return customer;
                    }
                }
            }
            // If a string has length 4 it is a userId, return Customer based on ID
        } else if (inputString.length() == 4) {
            return  customers.get(inputString);
        }
        throw new NoSuchElementException("Customer not found by ID or SSN.");
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

    //get bank information:
    public ContactCard getBankInfo() {
        return contactInfo;
    }

    // below methods get information from user's ContactCard
    public ContactCard getContactInfo(User user) {return user.getContactInfo();}
    public String getEmail(User user) {return user.getEmail();}
    public String getPhoneNumber(User user) {return user.getPhoneNumber();}
    public String getStreetAddress(User user) {return user.getStreet();}
    public String getZipCode(User user) {return user.getZipCode();}
    public String getCity(User user) {return user.getCity();}

    //get employee
    public Employee getEmployee(String userId){
        return employees.get(userId);
    }

    public HashMap<String, Employee> getEmployees() {
        return employees;
    }

    public HashMap<String, Customer> getCustomers(){
        return customers;
    }

    //-----------------------SETTERS-----------------------
    public void setCustomerIdCounter(String customerId) {customerIdCounter = customerId;}
    public void setEmployeeIdCounter(String employeeId) {employeeIdCounter = employeeId;}
    public void setAccountIdCounter(String accountId) {accountIdCounter = accountId;}
    public void setStreetAddress(String newStreet, User user) {user.setStreet(newStreet);}
    public void setZipCode(String newZipCode, User user) {user.setZipCode(newZipCode);}
    public void setPhoneNumber(String newPhoneNr, User user) {user.setPhoneNumber(newPhoneNr);}
    public void setCity(String newCity, User user) {user.setCity(newCity);}

    public ContactCard getContactInfo() {
        return contactInfo;
    }

    public void setCustomers(HashMap<String, Customer> customers) {
        this.customers = customers;
    }

    public void setEmployees(HashMap<String, Employee> employees) {
        this.employees = employees;
    }

    public void setContactInfo(ContactCard contactInfo) {
        this.contactInfo = contactInfo;
    }

//-----------------------CREATOR METHODS-----------------------

    // creates new private customer and add it to customers hashmap:
    public void createCustomerPrivate(String ssn, String firstName, String lastName, String password, ContactCard contactCard) throws Exception {
        // Generates a new ID for a customer, then updates customerIdCounter
        String userId = IdGenerator.generateCustomerID(customerIdCounter);
        setCustomerIdCounter(userId);

        CustomerPrivate newCustomer = new CustomerPrivate(ssn, firstName, lastName, userId, password, contactCard);
        this.customers.put(userId, newCustomer);
    }
    // creates new corporate customer and add it to customers hashmap:
    public void createCustomerCorporate(String orgNumber, String companyName, String password, ContactCard contactCard) throws Exception {
        // Generates a new ID for a corporate customer, then updates customerIdCounter
        String userId = IdGenerator.generateCustomerID(customerIdCounter);
        setCustomerIdCounter(userId);

        CustomerCorporate newCustomer = new CustomerCorporate(orgNumber, companyName, userId, password, contactCard);
        this.customers.put(userId, newCustomer);
    }

    // creates new private employee and add it to employees hashmap:
    public void createEmployee(String firstName, String lastName, String password, ContactCard contactCard) throws Exception {
        // Generates a new ID for an employee, then updates employeeIdCounter
        String userId = IdGenerator.generateEmployeeID(employeeIdCounter);
        setEmployeeIdCounter(userId);

        Employee newEmployee = new Employee(firstName, lastName, userId, password, contactCard);
        this.employees.put(userId, newEmployee);
        System.out.println(userId);
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

    //-----------------------REMOVAL METHODS-----------------------
    // remove customer from bank:
    public void removeCustomer(String customerId) {
        this.customers.remove(customerId);
    }

    //remove employee from bank:
    public void removeEmployee(String employeeId) {
        this.employees.remove(employeeId);
    }

    // removes account from customer
    public void removeAccount(String customerId, String accountToRemove) {
        customers.get(customerId).removeAccount(accountToRemove);
    }


    //-----------------------VARIOUS-----------------------

    // deposits money into specified account
    public void deposit(String senderId, String accountId, double amount, String message, LocalDate date) throws Exception {
        Account account = getAccountById(accountId);
        account.deposit(senderId, amount, message, date);
    }

    // withdraw money from specified account
    public void withdraw(String accountId, double amount, String message, LocalDate date) throws Exception {
        Account account = getAccountById(accountId);
        account.withdraw(amount, date);
    }

    // transfer money between accounts; the actual account and a target account
    public void transfer(String accountId, String targetAccountId, double amount, String message, LocalDate date) throws Exception {
        Account account = getAccountById(accountId);
        Account targetAccount = getAccountById(targetAccountId);

        account.withdraw(amount, date);
        targetAccount.deposit(accountId, amount, message, date);
    }

    //verify customer login information:
    public boolean verifyCustomer(String userId, String password) throws PasswordException {
        boolean correctUserId = false;
        boolean correctPassword = false;
        if (!customers.containsKey(userId)) {
            throw new PasswordException("Invalid user ID or password. Please try again.");
        } else {
            correctUserId = true;
            Customer customer = customers.get(userId);
            if (!customer.getPassword().equals(password)) {
                throw new PasswordException("Invalid user ID or password. Please try again.");
            } else {
                correctPassword = true;
            }
            return correctUserId && correctPassword;
        }
    }

    //verify employee login information:
    public boolean verifyEmployee(String userId, String password) throws PasswordException {
        boolean correctUserId = false;
        boolean correctPassword = false;
        if (!employees.containsKey(userId)) {
            throw new PasswordException("Invalid user ID or password. Please try again.");
        } else {
            correctUserId = true;
            Employee employee = employees.get(userId);
            if (!employee.getPassword().equals(password)) {
                throw new PasswordException("Invalid user ID or password. Please try again.");
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

    @Override
    public String toString() {
        return "Bank{" +
                "contactInfo=" + contactInfo +
                ", customers=" + customers +
                ", employees=" + employees +
                ", employeeIdCounter='" + employeeIdCounter + '\'' +
                ", customerIdCounter='" + customerIdCounter + '\'' +
                ", accountIdCounter='" + accountIdCounter + '\'' + '}';
    }
}
