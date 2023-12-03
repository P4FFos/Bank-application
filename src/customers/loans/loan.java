package src.customers.loans;

import java.util.Calendar;

public class Loan {
	private Calendar initialLoanDate;
	private double loanAmount;
	private double interestRate;

	public Loan(Calendar initialLoanDate, double loanAmount, double interestRate) {
		this.initialLoanDate = initialLoanDate;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
	}

	public Calendar getInitialLoanDate() {
		return this.initialLoanDate;
	}

	public double getLoanAmount() {
		return this.loanAmount;
	}

	public double getInterestRate() {
		return this.interestRate;
	}



}
