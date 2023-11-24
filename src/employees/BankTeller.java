package src.employees;

import src.Bank;
import src.utils.ContactCard;
import src.utils.ContactCardPrivate;
import src.utils.Account;
import src.customers.Customer;
import src.customers.CustomerPrivate;

public class BankTeller extends BankEmployee {
	public BankTeller (String userId, String password, ContactCardPrivate contactCard){
		super(userId, password, contactCard);
	}

	public void createCustomerPrivate(String userId, String password, ContactCardPrivate contactCard){
		// CustomerPrivate newCustomer = new CustomerPrivate(userId, password, contactCard);
		// Bank.addCustomer(newCustomer);
	}

	public void removeCustomerPrivate(String userId){
		//Bank.removeCustomer(userId);
	}

	public void createAccountPrivate(String userId, String accountNumber){
		//Account newAccount = new Account(accountNumber);
		//Bank.getCustomer(userId).addAccount(newAccount);
	}

	public void removeAccountPrivate(String userId, String accountNumber){
		//Bank.getCustomer(userId).removeAccount(accountNumber);
	}
}
