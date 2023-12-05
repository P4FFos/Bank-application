package src.customers.loans;

import java.util.Calendar;
import src.utils.TruncationUtil;

public class Loan {
    // Calendar type attribute for initialLoanDate
    // two attributes for loanAmount and interestRate
    private Calendar initialLoanDate;
    private double loanAmount;
    private double interestRate;

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
    // checks monthDifference between current and initial months
    // checks if currentMonth date smaller than initialMonth
    // counts totalLoanAmountWithInterest
    // returns truncated amount
    public double getLoanAmountWithInterest() {
        Calendar currentMonth = Calendar.getInstance();
        Calendar initialMonth = getInitialLoanDate();

        int monthDifference = (currentMonth.get(Calendar.YEAR) - initialMonth.get(Calendar.YEAR)) * 12 + currentMonth.get(Calendar.MONTH) - initialMonth.get(Calendar.MONTH) + 1;
        if (currentMonth.get(Calendar.DAY_OF_MONTH) < initialMonth.get(Calendar.DAY_OF_MONTH)) {
            monthDifference--;
        }

        double totalLoanAmountWithInterest = getLoanAmount();
        for (long i = 1; i < monthDifference; i++) {
            double interestLoanAmount = totalLoanAmountWithInterest * (getInterestRate() / 100);
            totalLoanAmountWithInterest += interestLoanAmount;
        }
        return TruncationUtil.truncate(totalLoanAmountWithInterest);
    }
}
