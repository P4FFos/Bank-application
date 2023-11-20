package src;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Customer> customers;
    private ArrayList<BankEmployee> employees;

    public Bank(){
        customers = new ArrayList<>();
        employees = new ArrayList<>();
    }

    public void addCustomerPrivate(Customer customer){}
    public void getCustomerPrivate(Customer customer){}
    public void removeCustomerPrivate(Customer customer){}
    public void addEmployee(){

    }
    public void removeEmployee(){}
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

    public static void main(String[] args){

        Bank piggyBank = new Bank();
        piggyBank.addEmployee("emp1", );

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
                "Våmmedalsvägen",
                42831,
                "Kållered");

        CustomerPrivate customer1 = new CustomerPrivate(881023, "MB1", "mb1", contactInfoMB);
        CustomerPrivate customer2 = new CustomerPrivate(890101, "JD1", "jd1", contactInfoJD);

        piggyBank.addCustomerPrivate(customer1);
        piggyBank.addCustomerPrivate(customer2);

        customer1.deposit(1000, "2023-11-20");
        System.out.println(customer1);
        customer1.transfer();


    }
}
