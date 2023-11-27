package src.customers;

import java.util.Date;
//import java.time.LocalDate;

public class Transaction {
    // attributes for Transaction class
    private double amount;
    private Date date;
    private String senderAccountId;
    private String message;
//    private LocalDate localDate;

    public Transaction(String senderAccountId, double amount, Date date) {
        this.amount = amount;
        this.date = date;
        this.senderAccountId = senderAccountId;
    }

    public Transaction(double amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    public Transaction(double amount, String message, Date date) {
        this.message = message;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(String senderAccountId, double amount, String message, Date date) {
        this.senderAccountId = senderAccountId;
        this.message = message;
        this.amount = amount;
        this.date = date;
    }
}
