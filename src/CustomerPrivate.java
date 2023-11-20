package src;

import java.util.HashMap;

public class CustomerPrivate extends Customer{
    final private int socialSecurityNumber;
    private HashMap<String, Account> accounts;
    public CustomerPrivate(int SSN, String userId, String password, ContactCard contactCard) {
        super(userId, password, contactCard);
        this.socialSecurityNumber = SSN;
        accounts = new HashMap<>();
    }
    public double getBalance(String accountId) throws AccountNotFoundException {
        boolean keyExist = hashMapContainsKey(accountId);
        if(keyExist){
            return getAccount(accountId).getBalance();
        }
        throw new AccountNotFoundException("Account with ID " + accountId + " not found.");
    }
    public void deposit(double amount, String date){
    }
    public void withdraw(double amount, String date){

    }
    public boolean hashMapContainsKey(String accountId){
        return accounts.containsKey(accountId);
    }
    public Account getAccount(String accountId){
        return accounts.get(accountId);
    }
    public void transfer(Bank bank, String targetAccountId, double amount, String message, String date){

    }
}