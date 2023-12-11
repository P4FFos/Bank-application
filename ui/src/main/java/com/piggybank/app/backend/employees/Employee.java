package com.piggybank.app.backend.employees;

import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.User;
import com.piggybank.app.backend.utils.ContactCard;

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
