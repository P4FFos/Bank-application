package src;

import java.util.HashMap;

public class CustomerPrivate extends Customer{
    final private String socialSecurityNumber;
    private HashMap<String, Account> accounts;
    public CustomerPrivate(String SSN, String userId, String password, ContactCard contactCard) {
        super(userId, password, contactCard);
        this.socialSecurityNumber = SSN;
        accounts = new HashMap<>();
    }
    public String getSSN(){
        return socialSecurityNumber;
    }
    public double getBalance(String accountId) throws AccountNotFoundException {
        boolean keyExist = hashMapContainsKey(accountId);
        if(keyExist){
            return getAccount(accountId).getBalance();
        }
        throw new AccountNotFoundException("Account with ID " + accountId + " not found.");
    }
    public void deposit(String accountId, double amount, String date){
        if(accounts.containsKey(accountId)){
            Account account = accounts.get(accountId);
            account.deposit(amount, date);
        }
    }
    public void withdraw(double amount, String date){

    }
    public boolean hashMapContainsKey(String accountId){
        return accounts.containsKey(accountId);
    }
    public Account getAccount(String accountId){
        return accounts.get(accountId);
    }
    public void createAccount(String accountId){
        Account account = new Account(accountId, 0.0);
        accounts.put(accountId, account);
    }
    public void transfer(Bank bank, String targetAccountId, double amount, String message, String date) throws AccountNotFoundException {
        if(accounts.containsKey(targetAccountId)){
            Account account = accounts.get(targetAccountId);
            account.transfer(bank, targetAccountId, amount, message, date);
        }
    }
    public boolean equals(Object otherObject){
        boolean isEqual = false;
        if(otherObject == this){
            isEqual = true;
        }else if(otherObject == null){
            isEqual = false;
        }else if(otherObject instanceof CustomerPrivate otherCustomerPrivate) {
            isEqual = this.socialSecurityNumber.equals(otherCustomerPrivate.getSSN());
        }
        return isEqual;
    }
}