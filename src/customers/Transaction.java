package src.customers;

import java.util.Date;

public class Transaction {
    // attributes for Transaction class
    private double amount;
    private Date date;
    private String senderAccountId;
    private String message;

    // Constructor for transactions that require senderAccountId
    public Transaction(String senderAccountId, double amount, Date date) {
//        if (amount <= 0 || senderAccountId.isBlank()) {
//            throw new Exception("");
//        } else {
//            this.senderAccountId = senderAccountId;
//            this.amount = amount;
//            this.date = date;
//        }
        this.senderAccountId = senderAccountId;
        this.amount = amount;
        this.date = date;
    }

    // Constructor for usual transactions
    public Transaction(double amount, Date date) {
//        if (amount <= 0) {
//            throw new Exception("");
//        } else {
//            this.amount = amount;
//            this.date = date;
//        }
        this.amount = amount;
        this.date = date;
    }

    // Constructor for transactions that require message
    public Transaction(double amount, String message, Date date) {
//        if (amount <= 0 || message.isBlank()) {
//            throw new Exception("");
//        } else {
//            this.amount = amount;
//            this.message = message;
//            this.date = date;
//        }
        this.amount = amount;
        this.message = message;
        this.date = date;
    }

    // Constructor for transactions that require message and senderAccountId
    public Transaction(String senderAccountId, double amount, String message, Date date) {
//        if (amount <= 0 || message.isBlank() || senderAccountId.isBlank()) {
//            throw new Exception("");
//        } else {
//            this.senderAccountId = senderAccountId;
//            this.amount = amount;
//            this.message = message;
//            this.date = date;
//        }
        this.senderAccountId = senderAccountId;
        this.amount = amount;
        this.message = message;
        this.date = date;
    }
}
