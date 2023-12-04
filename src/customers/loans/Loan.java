package src.customers.loans;

import java.util.Calendar;
import java.util.Date;

import src.utils.TruncationUtil;

public class Loan {
    // Calendar type attribute for initialLoanDate
    // two attributes for loanAmount and interestRate
    // constant attribute to convert time in month into milliseconds
    private Calendar initialLoanDate;
    private double loanAmount;
    private double interestRate;
    private final static double MONTH_IN_MILLISECONDS = 1000.0 * 60 * 60 * 24 * 30;

    // Constructor for Loan class
    public Loan(Calendar initialLoanDate, double loanAmount, double interestRate) {
        this.initialLoanDate = initialLoanDate;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
    }

    // get methods to get initialLoanDate, loanAmount and interestRate
    public Calendar getInitialLoanDate() {
        return this.initialLoanDate;
    }

    public double getLoanAmount() {
        return this.loanAmount;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    // getLoanAmountWithInterest method which:
    // creates reference of type Date and assign it to new object
    // counts time elapsed after loan was taken (Subtracts initialLoanDate from currentDate )
    // convert timeInMilliseconds into timeInMonth
    // counts truncated totalLoanAmountWithInterest by adding amount of interestRate to the loanAmount
    public double getLoanAmountWithInterest() {
        Date currentDate = new Date();

        long timeInMilliseconds = currentDate.getTime() - getInitialLoanDate().getTime().getTime();
        double timeInMonths = timeInMilliseconds / MONTH_IN_MILLISECONDS;

        double amountOfLoanInterest = (getLoanAmount() * ((getInterestRate() / 100) * timeInMonths));
        double totalLoanAmountWithInterest = TruncationUtil.truncate(getLoanAmount() + amountOfLoanInterest);
        return totalLoanAmountWithInterest;
    }
}
