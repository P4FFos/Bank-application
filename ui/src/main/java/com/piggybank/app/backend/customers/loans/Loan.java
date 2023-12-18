package com.piggybank.app.backend.customers.loans;

import com.piggybank.app.backend.customers.Account;
public class Loan extends Account {
    //loan type where we can give out larger amounts of money (such as mortgages)

    //attributes:
    private double initialAmount;
    private double interestRate = 5; //the interest rate per month
    private double minPaymentPercent = 1; //the minimum loan percentage the customer must pay each month
    private double minPaymentAmount; //the amount the customer must pay each month

    public Loan() {
    }

    //constructor:
    public Loan(String accountId, String accountName, double initialAmount) {
        super(accountId, accountName);
        this.initialAmount = initialAmount;
        setBalance(initialAmount);
        setPaymentAmount();
    }

    public void setInitialAmount(double initialAmount) {
        this.initialAmount = initialAmount;
    }

    //calculating the minimum monthly payment:
    public void setPaymentAmount() {
        //minimum payment amount:
        double minPayment = (this.initialAmount / 100) * this.minPaymentPercent;

        //interest rate:
        final double interestAmount = (this.initialAmount / 100) * this.interestRate;

        //total minimum monthly payment:
        this.minPaymentAmount = minPayment + interestAmount;
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

    //make a minimum payment:
    public void makePayment() {
        updateAmount(this.minPaymentAmount);
    }

    //make a custom payment:
    public void makePayment(double amount) throws Exception {
        if (amount < this.minPaymentAmount) {
            throw new Exception ("Payment must not be less than minimum required amount.");
        } else {
            updateAmount(amount);
        }
    }

    //updating the current amount of the loan based on payments made:
    public void updateAmount(double paidAmount) {
        setBalance(getBalance() - paidAmount);
    }

    //getters for attributes:
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




}
