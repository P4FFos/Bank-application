package src.customers;

import java.util.Date;

public class Transaction {
    // attributes for Transaction class
    private String receiverAccountId;
    private String senderAccountId;
    private double amount;
    private String message;
    private Date date;

    public Transaction(String receiverAccountId, String senderAccountId, double amount, String message, Date date) {
        if (receiverAccountId.isBlank() && senderAccountId.isBlank()) {
            this.amount = amount;
            this.message = message;
        } else if (message.isBlank()) {
            this.receiverAccountId = receiverAccountId;
            this.senderAccountId = senderAccountId;
            this.amount = amount;
        } else if (receiverAccountId.isBlank() && message.isBlank()) {
            this.amount = amount;
            this.senderAccountId = senderAccountId;
        } else if (receiverAccountId.isBlank()) {
            this.senderAccountId = senderAccountId;
            this.message = message;
            this.amount = amount;
        } else {
            this.receiverAccountId = receiverAccountId;
            this.senderAccountId = senderAccountId;
            this.amount = amount;
            this.message = message;
        }
    }

    //TODO: add a toString method, check if withdrawal or deposit or transfer
    //if receiverAccount/senderAccount is empty ... etc

}
