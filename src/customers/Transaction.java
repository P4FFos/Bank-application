package src.customers;

import java.util.Date;
//import java.time.LocalDate;

public class Transaction {
    // attributes for Transaction class
    private double amount;
    private Date date;
    private String receiverAccountId;
//    private LocalDate localDate;

    // constructor for Deposit, Outgoing and Incoming transactions
    public Transaction(String receiverAccountId, double amount, Date date) {
        this.amount = amount;
        this.date = date;
        this.receiverAccountId = receiverAccountId;
    }

    // constructor for Withdraw
    public Transaction(double amount, Date date) {
        this.amount = amount;
        this.date = date;
    }
}
