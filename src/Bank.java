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
import src.utils.ContactCardCorporate;
import src.utils.ContactCardPrivate;

import java.util.HashMap;

public class Bank {
    private ContactCardCorporate contactInfo;
    private HashMap<String, Customer> customers;
    private HashMap<String, BankEmployee> employees;

    public Bank(ContactCardCorporate contactInfo) {
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

	// create new private customer:
	public Customer createCustomerPrivate(String userId, String password, ContactCardPrivate contactCard) {
		CustomerPrivate newCustomer = new CustomerPrivate(userId, password, contactCard);
		return newCustomer;
	}

	// create new corporate customer:
	public Customer createCustomerCorporate(String userId, String password, ContactCardCorporate contactCard) {
		CustomerCorporate newCustomer = new CustomerCorporate(userId, password, contactCard);
		return newCustomer;
	}

	// add new customer to bank:
	public void addCustomer(String userId, Customer customer) throws DuplicateIdException{
		if (customers.containsKey(userId)) // check if customer exists
			throw new DuplicateIdException("An account already exists with this number.");
		this.customers.put(userId, customer);
	}

   // remove customer from bank:
	public void removeCustomer(String employeeID, String customerID) throws UserNotFoundException {
		if (!customers.containsKey(customerID)) // check if customer exists
			throw new UserNotFoundException("Account not found.");
		this.customers.remove(customerID);
	}

	// create new account for customer:
	public Account createAccount(String accountNumber){
		Account newAccount = new Account(accountNumber);
		return newAccount;
	}
	
	// add account to customer:
	public void addAccount(String userID, Account newAccount) throws AccountNotFoundException {
		if (!customers.containsKey(userID)) // check if customer exists 
			throw new AccountNotFoundException("Account not found.");
		Customer customer = customers.get(userID);
		customer.addAccount(newAccount);
	}

    //retrieve customer information:
    public Customer getCustomer(String userId) throws AccountNotFoundException {
        if (!customers.containsKey(userId)) {
            throw new AccountNotFoundException("Account not found.");
        } else {
            return this.customers.get(userId);
        }
    }

    //add new employee to bank:
    public void createEmployee(String userId, BankEmployee employee) throws DuplicateIdException {
        if(employees.containsKey(userId)) {
            throw new DuplicateIdException("An account already exists with this number.");
        } else {
            this.employees.put(userId, employee);
        }
    }

    //remove employee from bank:
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
