package com.piggybank.app.backend;

import java.time.LocalDate;

import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.utils.ContactCard;

public class BackendMain {
    public static void main(String[] args) throws Exception {
        ContactCard CCJohnD = new ContactCard(
                "johnd@student.gu.se",
                "+46709876543",
                "Kungsgatan 1",
                "41102",
                "Gothenburg");

        ContactCard CCJaneD = new ContactCard(
                "janed@student.gu.se",
                "+4670756634",
                "Kungsgatan 1",
                "41102",
                "Gothenburg");

        Bank piggyBank = new Bank();

        piggyBank.createCustomerPrivate(
                "8801011122",
                "John",
                "Doe",
                "Password1",
                CCJohnD);
        piggyBank.createCustomerPrivate(
                "8801013344",
                "Jane",
                "Doe",
                "Password22",
                CCJaneD);

        piggyBank.createAccount("C001", "Daily account");
        piggyBank.createAccount("C001", "Savings account");

        piggyBank.createAccount("C002", "Savings account");

        LocalDate todaysDate = LocalDate.now();
        // Deposit 1000
        piggyBank.deposit("C001", "A00001", 1000.0, "", todaysDate);

        // Transfer 400 to another account
        piggyBank.transfer("A00001", "A00003", 400.0, "here you go!/JD", todaysDate);

        System.out.println(piggyBank.getCustomer("C001").getAccount("A00001").getBalance());
        System.out.println(piggyBank.getCustomer("C002").getAccount("A00003").getBalance());

        // Two ways of operating, either in Bank methods or digging through methods trees
        //System.out.println(piggyBank.getCustomer(janeDoe).getAccount(janeDoeAccount1).getTransactionHistory());
        System.out.println(piggyBank.getTransactionHistory("A00003"));

        // Print city of userId C001
        Customer customer_found = piggyBank.getCustomerByIdOrSSN("C001");
        System.out.println(piggyBank.getCity(customer_found));

    }
}

