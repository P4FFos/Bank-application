package com.piggybank.app.backend.employees;

import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.User;
import com.piggybank.app.backend.utils.ContactCard;

public class Employee extends User {

	private String firstName;
	private String lastName;
	private Customer currentCustomer;
	private String initials;

	// Bare constructor used by Jackson-Databind for Json deserializing
	public Employee() {
	}

	public Employee(String firstName, String lastName, String userId, String password, ContactCard contactCard) throws Exception{
		super(userId, password, contactCard);
		this.firstName = firstName;
		this.lastName = lastName;
		setInitials();
		this.currentCustomer = null;

	}

	public void setInitials() {
		initials = firstName.substring(0, 1).toUpperCase() + lastName.substring(0, 1).toUpperCase();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
