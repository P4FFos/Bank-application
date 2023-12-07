package src.customers.loans;

import src.customers.Account;
public class BigLoan extends Account {
    //this is the new loan type where we can give out larger amounts of money and can calculate interest, rent, etc
    //once the other Loan class is renamed to Credit, we can rename this to Loan

    private double amount; //the size of the loan
    final double interestRate = 5; //the interest rate per month, for now final but could vary if we choose
    private double paymentAmount; //the amount the customer must pay each month, might vary from customer to customer

    //constructor:
    public BigLoan(String accountId, String accountName, double amount) {
        super(accountId, accountName);
        this.amount = amount;
    }




}
