package src.employees;

import src.Bank;
import src.utils.ContactCard;
import src.customers.Customer;
import src.customers.CustomerPrivate;

public class BankTeller extends BankEmployee {
    public BankTeller(String userId, String password, ContactCard contactCard) {
        super(userId, password, contactCard);
    }

    // Create customer should
    public void createCustomerPrivate(Bank bank, String SSN, String userId, String password, ContactCard contactCard){
        CustomerPrivate customer = new CustomerPrivate(SSN, userId, password, contactCard);
        bank.createCustomerPrivate(customer);
    }
    public void createCustomerPrivate(Bank bank, Customer customer){
        bank.createCustomerPrivate(customer);
    }
    public void createAccount(Bank bank, Customer customer, String accountId){
            bank.createAccount(customer, accountId);
    }
}
