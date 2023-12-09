package src;

import java.util.Date;

import src.utils.ContactCard;

public class BackendMain {
    public static void main(String[] args) throws Exception {
        ContactCard CCJohnD = new ContactCard(
                "johnd@student.gu.se",
                "+46709876543",
                "Kungsgatan 1",
                41102,
                "Gothenburg");

        ContactCard CCJaneD = new ContactCard(
                "janed@student.gu.se",
                "+4670756634",
                "Kungsgatan 1",
                41102,
                "Gothenburg");

        Bank piggyBank = new Bank();

        piggyBank.createCustomerPrivate(
                "c00001",
                "8801011122",
                "John",
                "Doe",
                "password",
                CCJohnD);
        piggyBank.createCustomerPrivate(
                "c00002",
                "8801013344",
                "Jane",
                "Doe",
                "password",
                CCJaneD);

        piggyBank.createAccount("c00001", "Daily account");
        piggyBank.createAccount("c00001", "Savings account");

        piggyBank.createAccount("c00002", "Savings account");

        // Deposit 1000
        piggyBank.deposit("c00001", "a000000001", 1000.0, "", new Date());

        // Transfer 400 to another account
        piggyBank.transfer("a000000001", "a000000003", 400.0, "here you go!/JD", new Date());

        System.out.println(piggyBank.getCustomer("c00001").getAccount("a000000001").getBalance());
        System.out.println(piggyBank.getCustomer("c00002").getAccount("a000000003").getBalance());

        // Two ways of operating, either in Bank methods or digging through methods trees
        //System.out.println(piggyBank.getCustomer(janeDoe).getAccount(janeDoeAccount1).getTransactionHistory());
        System.out.println(piggyBank.getTransactionHistory("a000000003"));

    }
}

