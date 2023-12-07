package src.customers.debts;

import java.util.Calendar;
import src.utils.TruncationUtil;

public class Credit {
    // Calendar type attribute for initialCreditDate

	// attributes: 

	final double INTEREST_RATE = 5;

    private Calendar initialCreditDate;
    private double creditAmount;

    // Constructor for Credit class
    public Credit(Calendar initialCreditDate, double creditAmount) {
        this.initialCreditDate = initialCreditDate;
        this.creditAmount = creditAmount;
    }

    // get methods to get initialCreditDate and creditAmount and interestRate

    public Calendar getInitialCreditDate() {
        return this.initialCreditDate;
    }

    public double getCreditAmount() {
        return this.creditAmount;
    }

    public double getInterestRate() {
        return this.INTEREST_RATE;
    }

    // getCreditAmountWithInterest method which:
    // checks monthDifference between current and initial months
    // checks if currentMonth date smaller than initialMonth
    // counts totalCreditAmountWithInterest
    // returns truncated amount
    public double getCreditAmountWithInterest() {
        Calendar currentMonth = Calendar.getInstance();
        Calendar initialMonth = getInitialCreditDate();

        int monthDifference = (currentMonth.get(Calendar.YEAR) - initialMonth.get(Calendar.YEAR)) * 12 + currentMonth.get(Calendar.MONTH) - initialMonth.get(Calendar.MONTH) + 1;
        if (currentMonth.get(Calendar.DAY_OF_MONTH) < initialMonth.get(Calendar.DAY_OF_MONTH)) {
            monthDifference--;
        }

        double totalCreditAmountWithInterest = getCreditAmount();
        for (long i = 1; i < monthDifference; i++) {
            double interestCreditAmount = totalCreditAmountWithInterest * (getInterestRate() / 100);
            totalCreditAmountWithInterest += interestCreditAmount;
        }
        return TruncationUtil.truncate(totalCreditAmountWithInterest);
    }
}
