package src.customers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import src.customers.loans.Loan;
import src.utils.TruncationUtil;

public class Account {
    // attributes for account class
    private String accountName;
    private final String accountId;
    private double balance;
    private ArrayList<Transaction> transactions;
	private HashMap<Integer, Loan> loans;

    // constructor for the account class, with initialised balance = 0
    public Account(String accountId, String accountName) {
        this.transactions = new ArrayList<>();
		this.loans = new HashMap<>();
        this.accountName = accountName;
        this.accountId = accountId;
        this.balance = 0.0;
		this.transactions = new ArrayList<Transaction>();
    }

    // get method to receive accountName
    public String getAccountName() {
        return this.accountName;
    }

    // get method to receive accountId
    public String getAccountId() {
        return this.accountId;
    }

    // get method to receive balance
    public double getBalance() {
        return TruncationUtil.truncate(this.balance);
    }

    // method to get history of transactions
    public ArrayList<Transaction> getTransactionHistory() {
        // Remove and only return ArrayList transactions, from here:
		String resultString = "";
		for (Transaction transaction : transactions) {
			resultString += transaction.toString() + "\n";
		}
		resultString = resultString.substring(0, resultString.length() - 2);
        // to here.
		return transactions;
    }

    // deposit methods, checks is the message is blank:
    // fill in message field with empty string
    public void deposit(String senderAccountId, double amount, String message, Date date) {
        balance += TruncationUtil.truncate(amount);
        if (message.isBlank()) {
            Transaction withdraw = new Transaction("", senderAccountId, amount, "", date);
            transactions.add(withdraw);
        } else {
            Transaction withdraw = new Transaction("", senderAccountId, amount, message, date);
            transactions.add(withdraw);
        }
    }

    // withdraw method, checks if balance is bigger or equal to the amount to be sent:
    // if bigger -> create new transaction and add it to the list
    // if lower -> throw exception
    public void withdraw(double amount, Date date) throws Exception {
        if (balance >= amount) {
            balance -= TruncationUtil.truncate(amount);
            Transaction withdraw = new Transaction("", "", amount, "", date);
            transactions.add(withdraw);
        } else {
            throw new Exception("");
        }
    }

	public void addLoan(int loanId, Calendar loanDate, double loanAmount) {
		Loan loan = new Loan(loanId, loanDate, loanAmount);
		loans.put(loan.getLoanId(), loan);
	}

	public Loan getLoan(int loanId) {
		return loans.get(loanId);
	}

	public void removeLoan(int loanId) {
		loans.remove(loanId);
	}
}
