package com.piggybank.app.backend;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.piggybank.app.backend.customers.*;
import com.piggybank.app.backend.customers.debts.*;
import com.piggybank.app.backend.customers.loans.Loan;
import com.piggybank.app.backend.employees.*;
import com.piggybank.app.backend.exceptions.*;
import com.piggybank.app.backend.utils.*;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import java.time.LocalDate;

public class Bank {
    private ContactCard contactInfo;
    private HashMap<String, Customer> customers;
    private HashMap<String, Employee> employees;

    // Bare constructor used by Jackson-Databind for Json deserializing
    public Bank() {
    }

    public Bank(ContactCard contactInfo) {
        this.contactInfo = contactInfo;
        customers = new HashMap<>();
        employees = new HashMap<>();
    }

    //-----------------------GETTERS-----------------------

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
        throw new Exception("Customer not found by ID or SSN.");
    }

    // returns a string of all transactions in specified account
    //@JsonProperty("transactions")
    public ArrayList<Transaction> getTransactionHistory(String accountId) throws Exception {
        Account account = getAccountById(accountId);
        return account.getTransactionHistory();
    }

    // returns the balance of the specified account
    public double getBalance(String accountId) throws Exception {
        Account account = getAccountById(accountId);
        return account.getBalance();
    }

    // below methods get information from user's ContactCard
    public ContactCard getContactInfo(User user) {return user.getContactInfo();}

    // information is available in ContactCard, so let serializer ignore these below attributes with @JsonIgnore
    @JsonIgnore
    public String getEmail(User user) {return user.getEmail();}
    @JsonIgnore
    public String getPhoneNumber(User user) {return user.getPhoneNumber();}
    @JsonIgnore
    public String getStreetAddress(User user) {return user.getStreet();}
    @JsonIgnore
    public String getZipCode(User user) {return user.getZipCode();}
    @JsonIgnore
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

    public ContactCard getContactInfo() {
        return contactInfo;
    }

    //-----------------------SETTERS-----------------------
    @JsonIgnore
    public void setStreetAddress(String newStreet, User user) {user.setStreet(newStreet);}
    @JsonIgnore
    public void setEmail(String newEmail, User user) {user.setStreet(newEmail);}
    @JsonIgnore
    public void setZipCode(String newZipCode, User user) {user.setZipCode(newZipCode);}
    @JsonIgnore
    public void setPhoneNumber(String newPhoneNr, User user) {user.setPhoneNumber(newPhoneNr);}
    @JsonIgnore
    public void setCity(String newCity, User user) {user.setCity(newCity);}

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
    public String createCustomerPrivate(String ssn, String firstName, String lastName, String password, ContactCard contactCard) throws Exception {
        // finds the highest customerId in the HashMap customers
        String customerHighestId = findHighestCustomerId();

        // generates a new ID for a customer, then updates customerIdCounter
        String userId = IdGenerator.generateCustomerID(customerHighestId);

        CustomerPrivate newCustomer = new CustomerPrivate(ssn, firstName, lastName, userId, password, contactCard);
        this.customers.put(userId, newCustomer);
        System.out.println("Private customer created: " + userId + " " + firstName + " " + lastName);
        return userId;
    }
    // creates new corporate customer and add it to customers hashmap:
    public String createCustomerCorporate(String orgNumber, String companyName, String password, ContactCard contactCard) throws Exception {
        // finds the highest customerId in the HashMap customers
        String customerHighestId = findHighestCustomerId();

        // Generates a new ID for a corporate customer, then updates customerIdCounter
        String userId = IdGenerator.generateCustomerID(customerHighestId);

        CustomerCorporate newCustomer = new CustomerCorporate(orgNumber, companyName, userId, password, contactCard);
        this.customers.put(userId, newCustomer);
        System.out.println("Corporate customer created: " + userId + " " + companyName);
        return userId;
    }

    // creates new private employee and add it to employees hashmap:
    public void createEmployee(String firstName, String lastName, String password, ContactCard contactCard) throws Exception {
        // finds the highest employeeId in the HashMap employees
        String employeeHighestId = findHighestEmployeeId();

        // Generates a new ID for an employee
        String userId = IdGenerator.generateEmployeeID(employeeHighestId);

        Employee newEmployee = new Employee(firstName, lastName, userId, password, contactCard);
        this.employees.put(userId, newEmployee);
        System.out.println("Employee created: " + userId);
    }

    // creates a new account for customer:
    public void createAccount(String userId, String accountName) throws Exception {
        Customer customer = customers.get(userId);

        // finds the highest accountId in the HashMap customers
        String accountHighestId = findHighestAccountId();

        //Generates accountId
        String accountId = IdGenerator.generateAccountId(accountHighestId);

        Account newAccount = new Account(accountId, accountName);
        customer.addAccount(newAccount);
        System.out.println("New account: " + accountId + " " + accountName);
    }

    public void createLoanAccount(String userId, String accountName, double loanAmount) throws Exception {
        Customer customer = customers.get(userId);

        // finds the highest accountId in the HashMap customers
        String accountHighestId = findHighestAccountId();

        //Generates accountId
        String accountId = IdGenerator.generateAccountId(accountHighestId);

        Loan newLoan = new Loan(accountId, accountName, loanAmount);
        customer.addAccount(newLoan);
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

    // loops through HashMap customers for their userIds and returns which is highest
    public String findHighestCustomerId() {
        int highestIdCount = 0;
        String highestCustomerId = "C000";
        for(Customer customer : customers.values()) {
            int tempIdCount = Integer.parseInt(customer.getUserId().substring(1));
            if(tempIdCount > highestIdCount) {
                highestIdCount = tempIdCount;
                highestCustomerId = customer.getUserId();
            }
        }
        return highestCustomerId;
    }

    // loops through HashMap employees for their userIds and returns which is highest
    public String findHighestEmployeeId() {
        int highestIdCount = 0;
        String highestEmployeeId = "E000";
        for(Employee employee : employees.values()) {
            int tempIdCount = Integer.parseInt(employee.getUserId().substring(1));
            if(tempIdCount > highestIdCount) {
                highestIdCount = tempIdCount;
                highestEmployeeId = employee.getUserId();
            }
        }
        return highestEmployeeId;
    }

    // loops through HashMap employees for their HashMap of accounts, then gets the highest accountId of them all
    public String findHighestAccountId() {
        int highestIdCount = 0;
        String highestAccountId = "A00000";
        for(Customer customer : customers.values()) {
            HashMap<String, Account> customerAccounts = customer.getAccounts();
            for(Account account : customerAccounts.values()) {
                int tempIdCount = Integer.parseInt(account.getAccountId().substring(1));
                if(tempIdCount > highestIdCount) {
                    highestIdCount = tempIdCount;
                    highestAccountId = account.getAccountId();
                }
            }
        }
        return highestAccountId;
    }

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

        account.withdraw(targetAccountId, amount, date);
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
    public Credit getCredit(String userId, String accountId) throws Exception {
        Customer customer = customers.get(userId);
        Account account = customer.getAccount(accountId);
        if(account instanceof Credit credit) {
            return credit;
        }
        throw new Exception("No credit account " + accountId + " found for userID: " + userId);
    }

    // method to create credit in HashMap accounts in customer
    public void createCredit(String userId, String accountName, Calendar initialCreditDate, double amount) throws Exception{
        Customer customer = customers.get(userId);

        // finds the highest accountId in the HashMap customers
        String accountHighestId = findHighestAccountId();

        //Generates accountId
        String accountId = IdGenerator.generateAccountId(accountHighestId);

        Credit newAccount = new Credit(accountId, accountName, initialCreditDate, amount);
        customer.addAccount(newAccount);
        System.out.println("New credit account: " + accountId + " " + accountName);
    }

    // removeCredit method
    public void removeCredit(String userId, String accountId) throws Exception{
        Customer customer = customers.get(userId);
        customer.removeAccount(accountId);
        System.out.println("Account " + accountId + " removed for customer " + userId + ".");
    }

    @Override
    public String toString() {
        return String.format("Bank{ contactInfo: %s, customers: %s, employees: %s", contactInfo, customers, employees);
    }
}
