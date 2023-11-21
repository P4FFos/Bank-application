package src;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Customer> customers;
    private ArrayList<BankEmployee> employees;

    public Bank(){
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
            Account account = new Account(accountId, 0.0);

            for(Customer listObj: customers){
                String listObjSSN = ((CustomerPrivate) listObj).getSSN();
                String SSN = ((CustomerPrivate) customer).getSSN();

                if(listObjSSN.equals(SSN)){
                    ((CustomerPrivate) listObj).createAccount(accountId);
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

        customer1.deposit("881023", 1000, "2023-11-20");
        System.out.println("MB has: " + customer1.getBalance("881023"));
        customer1.transfer(piggyBank, "890101", 100, "money from MB", "2023-11-21");
        System.out.println("JD has: " + customer2.getBalance("890101"));


    }
}
