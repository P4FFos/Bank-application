package src.customers;

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
//        if (receiverAccountId.isBlank() && senderAccountId.isBlank()) {
//            this.amount = amount;
//            this.message = message;
//        } else if (message.isBlank()) {
//            this.receiverAccountId = receiverAccountId;
//            this.senderAccountId = senderAccountId;
//            this.amount = amount;
//        } else if (receiverAccountId.isBlank() && message.isBlank()) {
//            this.amount = amount;
//            this.senderAccountId = senderAccountId;
//        } else if (receiverAccountId.isBlank()) {
//            this.senderAccountId = senderAccountId;
//            this.message = message;
//            this.amount = amount;
//        } else {
//        this.receiverAccountId = receiverAccountId;
//        this.senderAccountId = senderAccountId;
//        this.amount = amount;
//        this.message = message;
//        }

        this.receiverAccountId = receiverAccountId;
        this.senderAccountId = senderAccountId;
        this.amount = amount;
        this.message = message;
    }

    // toString method, which checks and assign different formats:
    // if receiverAccID and senderAccID fields are Blank
    // if message field is Blank
    // if receiverAccID and message fields are Blank
    // if receiverAccID is Blank
    public String toString() {
        if (receiverAccountId.isBlank() && senderAccountId.isBlank()) {
            return "Transaction - " + "Amount: " + amount + " Message: " + message + " Date: " + date;
        } else if (message.isBlank()) {
            return "Transaction - " + " Receiver Account ID: " + receiverAccountId + " Sender Account ID: " + senderAccountId + " Amount: " + amount + " Date: " + date;
        } else if (receiverAccountId.isBlank() && message.isBlank()) {
            return "Transaction - " + " Sender Account ID: " + senderAccountId + " Amount: " + amount + " Date: " + date;
        } else if (receiverAccountId.isBlank()) {
            return "Transaction - " + " Sender Account ID: " + senderAccountId + " Amount: " + amount + " Message: " + message + " Date: " + date;
        } else {
            return "Transaction - " + " Receiver Account ID: " + receiverAccountId + " Sender Account ID: " + senderAccountId + " Amount: " + amount + " Message: " + message + " Date: " + date;
        }
    }
}
