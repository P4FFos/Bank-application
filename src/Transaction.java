package src;

public class Transaction {
    private String accountId;
    private double value;
    private String message;
    private String date;

    public Transaction(String accountId, double value, String message, String date){
        this.accountId = accountId;
        this.value = value;
        this.message = message;
        this.date = date;
    }
    public Transaction(String accountId, double value, String date){
        this.accountId = accountId;
        this.value = value;
        this.message = "";
        this.date = date;
    }
    public String toString(){
        return String.format("%s, %.2f, %s, %s", accountId, value, message, date);
    }
}
