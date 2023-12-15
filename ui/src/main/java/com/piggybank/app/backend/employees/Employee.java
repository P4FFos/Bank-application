package com.piggybank.app.backend.employees;

import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.User;
import com.piggybank.app.backend.utils.ContactCard;

public class Employee extends User {
	private Customer currentCustomer;
	private String initials;

	public Employee(String userId, String password, ContactCard contactCard, String initials) throws Exception{
		super(userId, password, contactCard);
		this.initials = initials;
		this.currentCustomer = null;

	}

	public void setCurrentCustomer(Customer customer) {
		this.currentCustomer = customer;
	}

	public Customer getCurrentCustomer() {
		return this.currentCustomer;
	}

	public String getInitials(){
		return initials;
	}
}
