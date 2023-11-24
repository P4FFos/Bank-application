package src.customers;

import java.util.Date;
//import java.time.LocalDate;

public class Transaction {
    private double amount;
    private Date date;
    private String receiverAccountId;
//    private LocalDate localDate;

    public Transaction(String receiverAccountId, double amount, Date date) {
        this.amount = amount;
        this.date = date;
        this.receiverAccountId = receiverAccountId;
    }

    public Transaction(double amount, Date date) {
        this.amount = amount;
        this.date = date;
    }
}
