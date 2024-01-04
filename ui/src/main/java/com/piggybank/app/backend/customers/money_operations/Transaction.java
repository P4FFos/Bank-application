package com.piggybank.app.backend.customers.money_operations;

import java.time.LocalDate;

public class Transaction {

    // attributes:
    private String receiverAccountId;
    private String senderAccountId;
    private double amount;
    private String amountString;
    private String message;
    private LocalDate date;

    // Bare constructor used by Jackson-Databind for Json deserializing
    public Transaction() {}

    // Main constructor
    public Transaction(String receiverAccountId, String senderAccountId, double amount, String message, LocalDate date) {
        this.receiverAccountId = receiverAccountId;
        this.senderAccountId = senderAccountId;
        this.amount = amount;
        this.message = message;
        this.date = date;
        this.amountString = String.format("%.2f", amount);
    }

	//--------------------Getters--------------------
    public String getReceiverAccountId() {
        return this.receiverAccountId;
    }

    public String getSenderAccountId() {
        return this.senderAccountId;
    }

    public double getAmount() {
        return this.amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMessage() {
        return this.message;
    }

    public String getAmountString() {return this.amountString;}

    //--------------------Setters--------------------
    public void setAmountString() {amountString = String.format("%.2f", this.amount);}

    public void setReceiverAccountId(String receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public void setSenderAccountId(String senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

	//--------------------Methods--------------------

    // toString method, which checks and assigns different formats:
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
