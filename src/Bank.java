package src;

import src.customers.Account;
import src.customers.Customer;
import src.customers.CustomerCorporate;
import src.customers.CustomerPrivate;
import src.customers.Transaction;
import src.employees.BankEmployee;
import src.employees.BankTeller;
import src.exceptions.AccountNotFoundException;
import src.exceptions.DuplicateIdException;
import src.exceptions.UserNotFoundException;
import src.utils.ContactCard;

import java.util.HashMap;
import java.util.Date;

public class Bank {
    private ContactCard contactInfo;
    private HashMap<String, Customer> customers;
    private HashMap<String, BankEmployee> employees;

    public Bank(ContactCard contactInfo) {
        this.contactInfo = contactInfo;
        this.customers = new HashMap<String, Customer>();
        this.employees = new HashMap<String, BankEmployee>();
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
	public Account createAccount(String accountNumber){
		Account newAccount = new Account(accountNumber);
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
            if(accountExists) {
                return customer.getAccount(accountId);
            }
        }
        throw new AccountNotFoundException("Account was not found.");
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
    public String getTransactionHistory(String accountId) throws Exception {
        Account account = getAccountById(accountId);
        return account.getTransactionHistory();

        /* To be used in Account class:
        String transactions = "";

        for(Transaction transaction: account.getTransactionHistory()) {
            transactions = String.format("%s%n%s", transactions, transaction.toString());
        }
        return transactions;
        */
    }

    // returns the balance of the specified account
    public double getBalance(String accountId) throws Exception {
        Account account = getAccountById(accountId);
        return account.getBalance();
    }

    //verify customer login information:
    public boolean verifyCustomer(String userId, String password) {
        boolean correctUserId = false;
        boolean correctPassword = false;
        if (customers.containsKey(userId)) {
            correctUserId = true;
            Customer customer = customers.get(userId);
            if (customer.getPassword().equals(password)) {
                correctPassword = true;
            } else {
                System.out.println("Invalid password. Please try again.");
            }
        } else {
            System.out.println("Invalid username. Please try again");
        }
        return correctUserId && correctPassword;
    }

    //verify employee login information:
    public boolean verifyEmployee(String userId, String password) {
        boolean correctUserId = false;
        boolean correctPassword = false;
        if (employees.containsKey(userId)) {
            correctUserId = true;
            BankEmployee employee = employees.get(userId);
            if (employee.getPassword().equals(password)) {
                correctPassword = true;
            } else {
                System.out.println("Invalid password. Please try again.");
            }
        } else {
            System.out.println("Invalid username. Please try again");
        }
        return correctUserId && correctPassword;
    }
}
