package src.employees;

import src.utils.ContactCard;
import src.customers.Customer;
import src.customers.User;

public class Employee extends User {
	private Customer currentCustomer;

	public Employee(String userId, String password, ContactCard contactCard) throws Exception{
		super(userId, password, contactCard);
		this.currentCustomer = null;
	}

	public void setCurrentCustomer(Customer customer) {
		this.currentCustomer = customer;
	}

	public Customer getCurrentCustomer() {
		return this.currentCustomer;
	}
}
