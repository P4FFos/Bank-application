package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.employees.Employee;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EmpCustomerOverviewController extends EmpMainController implements Initializable {
    @FXML
    private AnchorPane contentAnchorPane;
    @FXML
    private AnchorPane addAccountAnchorPane;
    @FXML
    private AnchorPane privateCustomerInfoAnchorPane;
    @FXML
    private AnchorPane corporateCustomerInfoAnchorPane;
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
    private Label companyNameLabel;
    @FXML
    private Label companyIdLabel;
    @FXML
    private Label companyOrgNrLabel;
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

    public void showCurrentEmployee(){
        empIdLabel.setText(EmpMainController.currentEmployee.getUserId());
        empInitialsLabel.setText(EmpMainController.currentEmployee.getInitials());
        System.out.println("Customer Overview (Accounts) Page. Logged in as: " + EmpMainController.currentEmployee.getInitials());
    }

    public void showCurrentCustomer() {
        if(EmpMainController.currentCustomer instanceof CustomerPrivate){
            CustomerPrivate currentPrivate = (CustomerPrivate) EmpMainController.currentCustomer;
            privateCustomerInfoAnchorPane.setVisible(true);
            corporateCustomerInfoAnchorPane.setVisible(false);
            customerSSNLabel.setText(currentPrivate.getSsn());
            customerNameLabel.setText(currentPrivate.getFullName());
            customerIdLabel.setText(currentPrivate.getUserId());
        } else {
            CustomerCorporate currentCorporate = (CustomerCorporate) EmpMainController.currentCustomer;
            privateCustomerInfoAnchorPane.setVisible(true);
            corporateCustomerInfoAnchorPane.setVisible(false);
            companyNameLabel.setText(currentCorporate.getCompanyName());
            companyIdLabel.setText(currentCorporate.getUserId());
            companyOrgNrLabel.setText(currentCorporate.getOrgNumber());
        }
        addAccountAnchorPane.setVisible(false);
        contentAnchorPane.setVisible(true);
    }



    public void initialize(URL arg0, ResourceBundle arg1) { //Populates accountsListView with elements in accounts, selection "gets" an account
        accountsListView.getItems().addAll(currentCustomersAccounts.keySet());
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

    public void goToEmpStart(ActionEvent event) throws IOException { //empStartButton
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpStart.fxml"));
        Parent root = loader.load();

        EmpMainController controller = loader.getController();
        controller.showCurrentEmployee();
        EmpMainController.currentCustomer = null;

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
