package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.customers.debts.Credit;
import com.piggybank.app.backend.customers.loans.Loan;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class EmpCustomerOverviewController extends EmpMainController implements Initializable {
    @FXML
    private AnchorPane contentAnchorPane;
    @FXML
    private AnchorPane addAccountAnchorPane;
    @FXML
    private AnchorPane creditAnchorPane;
    @FXML
    private AnchorPane loanAnchorPane;
    @FXML
    private AnchorPane privateCustomerInfoAnchorPane;
    @FXML
    private AnchorPane corporateCustomerInfoAnchorPane;
    @FXML
    private AnchorPane selectAccountToCreditAnchorPane;
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
    private Label accountNameLabel;
    @FXML
    private Label accountBalanceLabel;
    @FXML
    private Label creditedAccountLabel;
    @FXML
    private Label creditAmountLabel;
    @FXML
    private Label loanAmountLabel;
    @FXML
    private Button addAccountButton;
    @FXML
    private Button makeTransactionButton;
    @FXML
    private Button saveNewAccountButton;
    @FXML
    private ChoiceBox<String> accountsChoiceBox;
    @FXML
    private TextField newAccountNameField;
    @FXML
    private CheckBox loanCheckBox;
    @FXML
    private CheckBox creditCheckBox;
    @FXML
    private CheckBox standardCheckBox;
    @FXML
    private CheckBox halfMillionCheckBox;
    @FXML
    private CheckBox oneMillionCheckBox;
    @FXML
    private CheckBox twoHalfMillionCheckBox;
    @FXML
    private CheckBox fiveMillionCheckBox;
    @FXML
    private CheckBox fiveKCheckBox;
    @FXML
    private CheckBox tenKCheckBox;
    @FXML
    private CheckBox twentyFiveKCheckBox;
    @FXML
    private CheckBox fiftyKCheckBox;
    @FXML
    private CheckBox inTransactionCheckBox;
    @FXML
    private CheckBox outTransactionCheckBox;
    @FXML
    private ListView<String> accountsListView; //will be list of current customer's accounts
    @FXML
    private ListView<String> transactionsListView; //will be list of the current account's transactions

    private Account currentAccount;
    private double amount;
    private Account accountToIncrement;

    private Calendar initialCreditDate;


    //--------------- METHODS CONNECTED TO FXML ELEMENTS -------------------------
    public void goToEmpStart(ActionEvent event) throws IOException { //empStartButton
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpStart.fxml"));
        Parent root = loader.load();

        EmpMainController controller = loader.getController();
        controller.showCurrentEmployee();
        EmpMainController.currentCustomer = null;

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //-----------------ACCOUNTS MANAGEMENT----------------------------

    public void addAccount(ActionEvent event) {
        contentAnchorPane.setVisible(false);
        addAccountAnchorPane.setVisible(true);
        loanAnchorPane.setVisible(false);
        creditAnchorPane.setVisible(false);
        standardCheckBox.setSelected(true);
    }

    public void saveNewAccount() throws Exception {
        if (standardCheckBox.isSelected()) {
            bank.createAccount(currentCustomer.getUserId(), newAccountNameField.getText());
        } else if (creditCheckBox.isSelected() && !newAccountNameField.getText().isBlank() && amount != 0.0) {
            bank.createCredit(currentCustomer.getUserId(), newAccountNameField.getText(), initialCreditDate, amount);
        } else if (loanCheckBox.isSelected() && !newAccountNameField.getText().isBlank() && amount != 0.0 ) {
            bank.createLoanAccount(currentCustomer.getUserId(), newAccountNameField.getText(), amount);
        } else {
            System.out.println("Wrong data input.");
        }
        contentAnchorPane.setVisible(true);
        addAccountAnchorPane.setVisible(false);
    }

    //------------Add Standard Account--------------
    public void toggleStandard() {
        loanAnchorPane.setVisible(false);
        creditAnchorPane.setVisible(false);
        selectAccountToCreditAnchorPane.setVisible(false);
        creditCheckBox.setSelected(false);
        loanCheckBox.setSelected(false);
    }

    //------------Add Loan Account--------------
    public void toggleLoan() {
        loanAnchorPane.setVisible(true);
        creditAnchorPane.setVisible(false);
        selectAccountToCreditAnchorPane.setVisible(true);
        creditCheckBox.setSelected(false);
        standardCheckBox.setSelected(false);
    }

    public void toggleHalfMillionLoan() {
        oneMillionCheckBox.setSelected(false);
        twoHalfMillionCheckBox.setSelected(false);
        fiveMillionCheckBox.setSelected(false);
        amount = -500000.0;
    }

    public void toggleOneMillionLoan() {
        halfMillionCheckBox.setSelected(false);
        twoHalfMillionCheckBox.setSelected(false);
        fiveMillionCheckBox.setSelected(false);
        amount = -1000000.0;
    }

    public void toggleTwoHalfMillionLoan() {
        halfMillionCheckBox.setSelected(false);
        oneMillionCheckBox.setSelected(false);
        fiveMillionCheckBox.setSelected(false);
        amount = -2500000.0;
    }

    public void toggleFiveMillionLoan() {
        halfMillionCheckBox.setSelected(false);
        oneMillionCheckBox.setSelected(false);
        twoHalfMillionCheckBox.setSelected(false);
        amount = -5000000.0;
    }

    //------------Add Credit Account--------------
    public void toggleCredit() {
        loanAnchorPane.setVisible(false);
        creditAnchorPane.setVisible(true);
        selectAccountToCreditAnchorPane.setVisible(true);
        standardCheckBox.setSelected(false);
        loanCheckBox.setSelected(false);
    }

    public void toggleFiveKCredit() {
        tenKCheckBox.setSelected(false);
        twentyFiveKCheckBox.setSelected(false);
        fiftyKCheckBox.setSelected(false);
        amount = -25000.0;
    }

    public void toggleTenKCredit() {
        fiveKCheckBox.setSelected(false);
        twentyFiveKCheckBox.setSelected(false);
        fiftyKCheckBox.setSelected(false);
        amount = -10000.0;
    }

    public void toggleTwentyFiveKCredit() {
        fiveKCheckBox.setSelected(false);
        tenKCheckBox.setSelected(false);
        fiftyKCheckBox.setSelected(false);
        amount = -25000.0;
    }

    public void toggleFiftyKCredit() {
        fiveKCheckBox.setSelected(false);
        tenKCheckBox.setSelected(false);
        twentyFiveKCheckBox.setSelected(false);
        amount = -50000.0;
    }

    //------------SELECT ACCOUNT TO CREDIT------------------
    public void showAccountToCredit(ActionEvent event) {
            accountToIncrement = currentCustomersAccounts.get(accountsChoiceBox.getValue());
            String amountStr = Double.toString(Math.abs(amount));
            creditedAccountLabel.setText(accountToIncrement.getAccountName());
            creditAmountLabel.setText(amountStr);
    }

    //------------SELECT ACCOUNT TO LOAN------------------
    public void showAccountToLoan(ActionEvent event) {
            accountToIncrement = currentCustomersAccounts.get(accountsChoiceBox.getValue());
            String amountStr = Double.toString(Math.abs(amount));
            loanAmountLabel.setText(accountToIncrement.getAccountName());
            loanAmountLabel.setText(amountStr);
    }
//----------------MANAGE TRANSACTIONS--------------------------

    public void makeTransaction(ActionEvent event) {
        //wait until ui is built for this
    }

//----------------SETUP SCENE-------------------------------------

    public void initialize(URL arg0, ResourceBundle arg1) { //Populates accountsListView with elements in accounts, selection "gets" an account
        accountsListView.getItems().addAll(currentCustomersAccounts.keySet());
        accountsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                String currentAccountId = accountsListView.getSelectionModel().getSelectedItem();
                currentAccount = currentCustomersAccounts.get(currentAccountId);
                accountNameLabel.setText(currentAccount.getAccountName());
                accountBalanceLabel.setText(currentAccount.getBalance() + " SEK");
                transactionsListView.getItems().addAll(String.valueOf(currentAccount.getTransactionHistory()));
            }
        });

        String[] standardAccounts = new String[currentCustomersAccounts.size()]; //not pretty I know...
        int counter = 0;
        for (String key : currentCustomersAccounts.keySet()) {
            if (currentCustomersAccounts.get(key) instanceof Credit || currentCustomersAccounts.get(key) instanceof Loan) {
                //NOTHING
            } else {
                standardAccounts[counter] = key;
                counter++;
            }

        }
        accountsChoiceBox.getItems().addAll(standardAccounts);
        accountsChoiceBox.setOnAction(this::showAccountToCredit);
        accountsChoiceBox.setOnAction(this::showAccountToLoan);
    }

    public void showCurrentEmployee() {
        empIdLabel.setText(currentEmployee.getUserId());
        empInitialsLabel.setText(EmpMainController.currentEmployee.getInitials());
        System.out.println("Customer Overview (Accounts) Page. Logged in as: " + EmpMainController.currentEmployee.getInitials());
    }

    public void showCurrentCustomer() {
        if (EmpMainController.currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate currentPrivate = (CustomerPrivate) EmpMainController.currentCustomer;
            privateCustomerInfoAnchorPane.setVisible(true);
            corporateCustomerInfoAnchorPane.setVisible(false);
            customerSSNLabel.setText(currentPrivate.getSsn());
            customerNameLabel.setText(currentPrivate.getFullName());
            customerIdLabel.setText(currentPrivate.getUserId());
        } else {
            CustomerCorporate currentCorporate = (CustomerCorporate) EmpMainController.currentCustomer;
            privateCustomerInfoAnchorPane.setVisible(false);
            corporateCustomerInfoAnchorPane.setVisible(true);
            companyNameLabel.setText(currentCorporate.getCompanyName());
            companyIdLabel.setText(currentCorporate.getUserId());
            companyOrgNrLabel.setText(currentCorporate.getOrgNumber());
        }

        addAccountAnchorPane.setVisible(false);
        contentAnchorPane.setVisible(true);
    }


}
