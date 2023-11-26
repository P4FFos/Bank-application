package src.employees;

import src.Bank;
import src.customers.Account;
import src.customers.CustomerCorporate;
import src.ContactCardCorporate;

public class KeyAccountManager extends BankEmployee{
	public KeyAccountManager (String userId, String password, ContactCardCorporate contactCard){
		super(userId, password, contactCard);
	}

	public void createCustomerCorporate(String userId, String password, ContactCardCorporate contactCard){
		CustomerCorporate newCustomer = new CustomerCorporate(userId, password, contactCard);
		Bank.addCustomer(newCustomer);
	}

	public void removeCustomerCorporate(String userId){
		Bank.removeCustomer(userId);
	}

	public void createAccountCorporate(String userId, String accountNumber){
		Account newAccount = new Account(accountNumber);
		Bank.getCustomer(userId).addAccount(newAccount);
	}

	public void removeAccountCorporate(String userId, String accountNumber){
		Bank.getCustomer(userId).removeAccount(accountNumber);
	}
}
