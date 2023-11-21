package src;

import src.customers.Customer;
import src.customers.CustomerPrivate;
import src.employees.BankEmployee;
import src.employees.BankTeller;
import src.exceptions.AccountNotFoundException;
import src.utils.Account;
import src.utils.ContactCard;
import src.utils.Transaction;

import java.util.ArrayList;

public class Bank {
    private ContactCard contactInfo;
    private ArrayList<Customer> customers;
    private ArrayList<BankEmployee> employees;

    public Bank(){
        //this.contactInfo = contactInfo;
        customers = new ArrayList<>();
        employees = new ArrayList<>();
    }

    public void createCustomerPrivate(Customer customer){
        customers.add(customer);
    }
    public void getCustomerPrivate(Customer customer){}
    public void removeCustomerPrivate(Customer customer){}
    public void createEmployee(BankEmployee employee){
        employees.add(employee);
    }
    public void removeEmployee(){}
    public void createAccount(Customer customer, String accountId){
        if(customer instanceof CustomerPrivate){
            for(Customer customerObj: customers){
                CustomerPrivate customerObjPrivate = ((CustomerPrivate) customerObj);
                if(customerObjPrivate.equals(customer)){
                    customerObjPrivate.createAccount(accountId);
                }
            }
        }
    }
    public Account findAccountById(String accountId) throws AccountNotFoundException {
        for(Customer customer: customers){
            if(customer instanceof CustomerPrivate customerPrivate){
                if(customerPrivate.hashMapContainsKey(accountId)){
                    return customerPrivate.getAccount(accountId);
                }
            }
        }
        throw new AccountNotFoundException("Account with ID " + accountId + " not found.");
    }
    public void deposit(String accountId, double amount, String message, String date) throws AccountNotFoundException {
        Account account = findAccountById(accountId);
        account.deposit(amount, message, date);
    }
    public void deposit(String accountId, double amount, String date) throws AccountNotFoundException {
        Account account = findAccountById(accountId);
        account.deposit(amount, date);
    }
    public void withdraw(String accountId, double amount, String date) throws AccountNotFoundException {
        Account account = findAccountById(accountId);
        account.withdraw(amount, date);
    }
    public double getBalance(String accountId) throws AccountNotFoundException {
        Account account = findAccountById(accountId);
        return account.getBalance();
    }
    public String getTransactionHistory(String accountId) throws AccountNotFoundException{
        Account account = findAccountById(accountId);
        String transactions = "";

        for(Transaction transaction: account.getTransactionHistory()){
            transactions = String.format("%s%n%s", transactions, transaction.toString());
        }
        return transactions;
    }
    // TODO: Decide on if operations should be done all from bank class.
    public void transfer(String accountId, String targetAccountId, double amount, String message, String date) throws AccountNotFoundException {
        Account account = findAccountById(accountId);
        Account targetAccount = findAccountById(targetAccountId);
        account.withdraw(amount, date);
        targetAccount.deposit(amount, message, date);
    }

    public static void main(String[] args) throws AccountNotFoundException {

        Bank piggyBank = new Bank();

        ContactCard contactInfoEMP = new ContactCard(
                "EMP1",
                "emp1",
                "emp1@student.gu.se",
                "+46700000000",
                "",
                0,
                "");

        ContactCard contactInfoMB = new ContactCard(
                "Marcus",
                "Berggren",
                "mb@student.gu.se",
                "+46701234567",
                "Våmmedalsvägen",
                42831,
                "Kållered");

        ContactCard contactInfoJD = new ContactCard(
                "John",
                "Doe",
                "jd@student.gu.se",
                "+46709876543",
                "",
                0,
                "");

        BankTeller emp1 = new BankTeller("EMP1", "emp1", contactInfoEMP);

        piggyBank.createEmployee(emp1);

        CustomerPrivate customer1 = new CustomerPrivate("88102323", "MB1", "mb1", contactInfoMB);
        CustomerPrivate customer2 = new CustomerPrivate("89010101", "JD1", "jd1", contactInfoJD);

        emp1.createCustomerPrivate(piggyBank, customer1);
        emp1.createAccount(piggyBank, customer1, "881023");
        emp1.createCustomerPrivate(piggyBank, customer2);
        emp1.createAccount(piggyBank, customer2, "890101");

        piggyBank.deposit("881023", 1000, "2023-11-20");
        System.out.println("MB has: " + piggyBank.getBalance("881023"));
        piggyBank.transfer("881023", "890101", 100, "money from MB", "2023-11-21");
        System.out.println("JD has: " + piggyBank.getBalance("890101"));
        piggyBank.withdraw("881023", 500, "2023-11-21");
        System.out.println("MB has now: " + piggyBank.getBalance("881023"));
        System.out.println("MB transactions history: " + piggyBank.getTransactionHistory("881023"));
    }
}
