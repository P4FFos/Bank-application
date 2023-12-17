package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.employees.Employee;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EmpCustomerOverviewController extends EmpMainController implements Initializable {
    @FXML
    private AnchorPane contentAnchorPane;
    @FXML
    private AnchorPane addAccountAnchorPane;
    @FXML
    private Label empIdLabel;
    @FXML
    private Label empInitialsLabel;
    @FXML
    private Label customerIdLabel;
    @FXML
    private Label customerSSNLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label accountIdLabel;
    @FXML
    private Label accountBalanceLabel;
    @FXML
    private Button selectAccountButton;
    @FXML
    private Button addAccountButton;
    @FXML
    private Button makeTransactionButton;
    @FXML
    private Button saveNewAccountButton;
    @FXML
    private TextField newAccountNameField;
    @FXML
    private CheckBox inTransactionCheckBox;
    @FXML
    private CheckBox outTransactionCheckBox;
    @FXML
    private ListView<String> accountsListView; //will be list of current customer's accounts
    @FXML
    private ListView<String> transactionsListView; //will be list of the current account's transactions

    //----------------VARIABLES------------------
    private Customer currentCustomer;
    //Change to HashMap<String, Account> and initialize as currentCustomer.getAccounts(). If no such method exists in Customer, create it and make it return the hashmap of accounts.
    private HashMap<String, Account> accounts;
    private Account currentAccount;
    private Employee currentEmployee;

    //-----------------METHODS-------------------

    public void setCurrentEmployee(){
        empIdLabel.setText(EmpMainController.currentEmployee.getUserId());
        empInitialsLabel.setText(EmpMainController.currentEmployee.getInitials());
        System.out.println("Customer Overview Page. Logged in as: " + EmpMainController.currentEmployee.getInitials());
    }

    public void setCurrentCustomer(Customer customer) { //change parameter to "Customer currentCustomer" and modify implementation accordingly
        currentCustomer = customer;
        customerIdLabel.setText(customer.getUserId());
        if(customer instanceof CustomerPrivate customerPrivate){
            customerSSNLabel.setText(customerPrivate.getSsn());
            customerNameLabel.setText(customerPrivate.getFullName());
        }
        accounts = customer.getAccounts();
    }

    public void initialize(URL arg0, ResourceBundle arg1) { //Populates accountsListView with elements in accounts, selection "gets" an account

        accountsListView.getItems().addAll(accounts.keySet());
        accountsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                String currentAccountId = accountsListView.getSelectionModel().getSelectedItem();
                currentAccount = accounts.get(currentAccountId);
                System.out.println("Balance of Current account: " + currentAccount.getBalance());
            }
        });
    }

    //--------------- METHODS CONNECTED TO FXML ELEMENTS -------------------------
    public void setCurrentAccount(ActionEvent event) {
        //get selection from accountsListView, set this account as current account and
        //load transactionsListView with the transaction list of current account
        //currentAccount = accountsListView.getSelectionModel().getSelectedItem();
        //Account selectedAccount = currentCustomer.getAccount(currentAccount);
        transactionsListView.getItems().addAll(String.valueOf(currentAccount.getTransactionHistory()));
    }

    public void addAccount(ActionEvent event) {
        //create an account, set initial balance to 0. add to customer's account list.
        //reload accountsListView
        //Account createdAccount = new Account(currentCustomer.getUserId(), currentCustomerName);
        //accountsListView.getItems().add(createdAccount.getAccountName());
        //accountsListView.getItems().addAll(accounts);

        contentAnchorPane.setVisible(false);
        addAccountAnchorPane.setVisible(true);
    }

    public void saveNewAccount() throws Exception {
        bank.createAccount(currentCustomer.getUserId(), newAccountNameField.getText());
        contentAnchorPane.setVisible(true);
        addAccountAnchorPane.setVisible(false);
    }

    public void makeTransaction(ActionEvent event) {
        //wait until ui is built for this
    }
}
