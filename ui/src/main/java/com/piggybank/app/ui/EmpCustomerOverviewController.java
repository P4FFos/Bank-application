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

    //--------------
    //Placeholders: current customer should be of type Customer when possible
    private String currentCustomerName;
    private String currentCustomerSSN;
    private String currentCustomerId;
    //______________
    //Placeholder: will be derived from current customer when possible
    private String[] accounts = {"main_account", "savings", "emergency", "travel"};
    //--------------
    private String currentAccount = "";

    public void initialize(URL arg0, ResourceBundle arg1){
        accountsListView.getItems().addAll(accounts);
        accountsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2){
                currentAccount = accountsListView.getSelectionModel().getSelectedItem();
                System.out.println("Current account: " + currentAccount);
            }
        });
    } //Initializes accountsListView with elements in accounts, selection sets currentAccount

    public void displayCurrentCustomer(String id, String name, String ssn){ //change parameter to Customer and modify implementation accordingly
        customerIdLabel.setText(id);
        customerSSNLabel.setText(ssn);
        customerNameLabel.setText(name);
    }

    //--------------- METHODS CONNECTED TO FXML ELEMENTS -------------------------
    public void setCurrentAccount(ActionEvent event){

    }

    public void addAccount(ActionEvent event){

    }

    public void makeTransaction(ActionEvent event){

    }



}
