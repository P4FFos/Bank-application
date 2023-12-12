package com.piggybank.app.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class EmpCustomerOverviewController extends EmpMainController implements Initializable {
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
    private CheckBox inTransactionCheckBox;
    @FXML
    private CheckBox outTransactionCheckBox;
    @FXML
    private ListView<String> accountsListView; //will be list of current customer's accounts
    @FXML
    private ListView<String> transactionsListView; //will be list of the current account's transactions

    //----------------VARIABLES------------------
    //Placeholders. Use "private Customer currentCustomer;" instead.
    private String currentCustomerName;
    private String currentCustomerSSN;
    private String currentCustomerId;
    //Change to HashMap<String, Account> and initialize as currentCustomer.getAccounts(). If no such method exists in Customer, create it and make it return the hashmap of accounts.
    private String[] accounts = {"main_account", "savings", "emergency", "travel"};
    //Change type to Account, don't initialize.
    private String currentAccount = "";

    //-----------------METHODS-------------------

    public void displayCurrentCustomer(String id, String name, String ssn){ //change parameter to "Customer currentCustomer" and modify implementation accordingly
        customerIdLabel.setText(id);
        customerSSNLabel.setText(ssn);
        customerNameLabel.setText(name);
    }

    public void initialize(URL arg0, ResourceBundle arg1){ //Populates accountsListView with elements in accounts, selection "gets" an account
        accountsListView.getItems().addAll(accounts);
        accountsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2){
                currentAccount = accountsListView.getSelectionModel().getSelectedItem();
                //System.out.println("Current account: " + currentAccount);
            }
        });
    }

    //--------------- METHODS CONNECTED TO FXML ELEMENTS -------------------------
    public void setCurrentAccount(ActionEvent event){
        //get selection from accountsListView, set this account as current account and
        //load transactionsListView with the transaction list of current account
    }

    public void addAccount(ActionEvent event){
        //create an account, set initial balance to 0. add to customer's account list.
        //reload accountsListView
    }

    public void makeTransaction(ActionEvent event){
        //wait until ui is built for this
    }



}
