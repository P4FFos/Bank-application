package com.piggybank.app.backend.customers.money_operations;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.utils.TruncationUtil;

@JsonTypeName("credit")
public class Credit extends Account {

    // attributes:
    private double interestRate = 5;
    private Calendar initialCreditDate;

    // Constructor for Credit class
    public Credit() {}
    public Credit(String accountId, String accountName, Calendar initialCreditDate, double creditAmount) {
        super(accountId, accountName);
        super.setBalance(creditAmount);
        this.initialCreditDate = initialCreditDate;
    }

    // get methods to get initialCreditDate and creditAmount and interestRate
    public Calendar getInitialCreditDate() {
        return this.initialCreditDate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInitialCreditDate(Calendar initialCreditDate) {
        this.initialCreditDate = initialCreditDate;
    }

    @JsonIgnore
    public double getCreditAmount() {
        return super.getBalance();
    }

    // getCreditAmountWithInterest method which:
    // checks monthDifference between current and initial months
    // checks if currentMonth date smaller than initialMonth
    // counts totalCreditAmountWithInterest
    // returns truncated amount
    @JsonIgnore
    public double getCreditAmountWithInterest() {
        Calendar currentMonth = Calendar.getInstance();
        Calendar initialMonth = getInitialCreditDate();

        int monthDifference = (currentMonth.get(Calendar.YEAR) - initialMonth.get(Calendar.YEAR)) * 12 + currentMonth.get(Calendar.MONTH) - initialMonth.get(Calendar.MONTH) + 1;
        if (currentMonth.get(Calendar.DAY_OF_MONTH) < initialMonth.get(Calendar.DAY_OF_MONTH)) {
            monthDifference--;
        }

        double totalCreditAmountWithInterest = getCreditAmount();
        for (long i = 1; i < monthDifference; i++) {
            double interestCreditAmount = totalCreditAmountWithInterest * (interestRate / 100);
            totalCreditAmountWithInterest += interestCreditAmount;
        }
        return TruncationUtil.truncate(totalCreditAmountWithInterest);
    }

    @Override
    public String toString() {
        return this.getAccountName();
    }
}
