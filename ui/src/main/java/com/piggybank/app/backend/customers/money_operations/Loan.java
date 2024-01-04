package com.piggybank.app.backend.customers.money_operations;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.piggybank.app.backend.customers.Account;

import java.time.LocalDate;

@JsonTypeName("loan")
public class Loan extends Account { // loan type where we can give out larger amounts of money (such as mortgages)

    //attributes:
    private double initialAmount; // the initial amount of the loan
    private double interestRate = 5; // the interest rate per month
    private double minPaymentPercent = 1; // the minimum loan percentage the customer must pay each month
    private double minPaymentAmount; // the amount the customer must pay each month

	// Bare constructor used by Jackson-Databind for Json deserializing
    public Loan() {}

    // Main constructor
    public Loan(String accountId, String accountName, double initialAmount) {
        super(accountId, accountName);
        this.initialAmount = initialAmount;
        setBalance(initialAmount);
        setPaymentAmount();
    }

	//--------------------Getters--------------------
	public double getInitialAmount() {
        return this.initialAmount;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public double getMinPaymentPercent() {
        return this.minPaymentPercent;
    }

    public double getMinPaymentAmount() {
        return this.minPaymentAmount;
    }

	//--------------------Setters--------------------
    public void setInitialAmount(double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setMinPaymentPercent(double minPaymentPercent) {
        this.minPaymentPercent = minPaymentPercent;
    }

    public void setMinPaymentAmount(double minPaymentAmount) {
        this.minPaymentAmount = minPaymentAmount;
    }

    public void setPaymentAmount() { // calculating the minimum monthly payment
        // minimum payment amount:
        double minPayment = (this.initialAmount / 100) * this.minPaymentPercent;

        // interest rate:
        final double interestAmount = (this.initialAmount / 100) * this.interestRate;

        // total minimum monthly payment:
        this.minPaymentAmount = minPayment + interestAmount;
    }

	//--------------------Methods--------------------
    public void makePayment() { // make a minimum payment
        updateAmount(this.minPaymentAmount);
    }

    public void makePayment(double amount) throws Exception { // make a custom payment
        if (amount < this.minPaymentAmount) {
            throw new Exception ("Payment must not be less than minimum required amount.");
        } else {
            updateAmount(amount);
        }
    }

    public void updateAmount(double paidAmount) { // updating the current amount of the loan based on payments made
        setBalance(getBalance() - paidAmount);
    }

    @Override
    public void withdraw(String receiverAccountId, double amount, String message, LocalDate date) {
        Transaction withdrawal = new Transaction(receiverAccountId, super.getAccountId(), 0 - amount, message, date);
        addTransaction(withdrawal);
        setBalanceString();
    }

	@Override
	public String toString() {
		return this.getAccountName();
	}
}
