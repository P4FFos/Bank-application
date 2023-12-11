package com.piggybank.app.backend.customers;

import java.util.Date;

public class Transaction {
    // attributes for Transaction class
    private String receiverAccountId;
    private String senderAccountId;
    private double amount;
    private String message;
    private Date date;

    // Constructor for Transaction class
    public Transaction(String receiverAccountId, String senderAccountId, double amount, String message, Date date) {
        this.receiverAccountId = receiverAccountId;
        this.senderAccountId = senderAccountId;
        this.amount = amount;
        this.message = message;
    }

    // getReceiverAccountId method
    public String getReceiverAccountId() {
        return this.receiverAccountId;
    }

    // getSenderAccountId method
    public String getSenderAccountId() {
        return this.senderAccountId;
    }

    // getAmount method
    public double getAmount() {
        return this.amount;
    }

    // getMessage method
    public String getMessage() {
        return this.message;
    }

    // toString method, which checks and assign different formats:
    // if receiverAccID and senderAccID fields are Blank
    // if message field is Blank
    // if receiverAccID and message fields are Blank
    // if receiverAccID is Blank
    public String toString() {
        if (receiverAccountId.isBlank() && senderAccountId.isBlank()) {
            return String.format("Transaction - Amount: %.2f, Message: %s, Date: %s", amount, message, date);
        } else if (message.isBlank()) {
            return String.format("Transaction - Receiver Account ID: %s, Sender Account ID: %s, Amount: %.2f, Date: %s"
                    , receiverAccountId, senderAccountId, amount, date);
        } else if (receiverAccountId.isBlank() && message.isBlank()) {
            return String.format("Transaction - Sender Account ID: %s, Amount: %.2f, Date: %s", senderAccountId, amount, date);
        } else if (receiverAccountId.isBlank()) {
            return String.format("Transaction - Sender Account ID: %s, Amount: %.2f, Message: %s, Date: %s"
                    , senderAccountId, amount, message, date);
        } else {
            return String.format("Transaction - Receiver Account ID: %s, Sender Account ID: %s, Amount: %.2f, Message: %s, Date: %s"
                    , receiverAccountId, senderAccountId, amount, message, date);
        }
    }
}
