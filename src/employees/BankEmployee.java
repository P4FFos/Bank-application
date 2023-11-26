package src.employees;

import src.ContactCard;
import src.customers.User;

public abstract class BankEmployee extends User {
	public BankEmployee(String userId, String password, ContactCard contactCard){
		super(userId, password, contactCard);
	}
}
