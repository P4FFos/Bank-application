package com.piggybank.app.ui.employee_controllers;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.exceptions.InsufficientBalanceException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmpMakeTransactionController extends EmpMainController implements Initializable{
    @FXML
    private AnchorPane privateCustomerInfoAnchorPane;
    @FXML
    private AnchorPane corporateCustomerInfoAnchorPane;
    @FXML
    private Button selectSendingAccountButton;
    @FXML
    private CheckBox oneHundredCheckBox;
    @FXML
    private CheckBox twoHundredCheckBox;
    @FXML
    private CheckBox fiveHundredCheckBox;
    @FXML
    private CheckBox oneThousandCheckBox;
    @FXML
    private Button okButton;
    @FXML
    private Label customerSSNLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label customerIdLabel;
    @FXML
    private Label companyNameLabel;
    @FXML
    private Label companyIdLabel;
    @FXML
    private Label companyOrgNrLabel;
    @FXML
    private ListView<String> accountsListView;
    @FXML
    private TextField recieverAccountTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private Label transferDoneLabel;
    @FXML
    private Label incorrectDetailsLabel;
    @FXML
    private Label insufficientBalanceLabel;

    private Account senderAccount;

    public void initialize(URL arg0, ResourceBundle arg1) {
        transferDoneLabel.setVisible(false);
        incorrectDetailsLabel.setVisible(false);

        super.showCurrentEmployee();
        showCurrentCustomer();

        //Populate accountsListView with accountIDs from currentCustomersAccounts
        accountsListView.getItems().addAll(currentCustomer.getAccounts().keySet());

        System.out.println("Employee Make Transaction Page. Logged in as: " + currentEmployee.getInitials());
    }

    public void selectSendingAccount(){
        String accountId = accountsListView.getSelectionModel().getSelectedItem();
        if(accountId != null) {
            senderAccount = currentCustomer.getAccount(accountId);
        }
    }
    public void toggleOneHundred(){
        if(oneHundredCheckBox.isSelected()) {
            amountTextField.setText("100.0");

            twoHundredCheckBox.setSelected(false);
            fiveHundredCheckBox.setSelected(false);
            oneThousandCheckBox.setSelected(false);
        } else {
            amountTextField.clear();
        }
    }
    public void toggleTwoHundred(){
        if(twoHundredCheckBox.isSelected()) {
            amountTextField.setText("200.0");

            oneHundredCheckBox.setSelected(false);
            fiveHundredCheckBox.setSelected(false);
            oneThousandCheckBox.setSelected(false);
        } else {
            amountTextField.clear();
        }
    }
    public void toggleFiveHundred(){
        if(fiveHundredCheckBox.isSelected()) {
            amountTextField.setText("500.0");

            oneHundredCheckBox.setSelected(false);
            twoHundredCheckBox.setSelected(false);
            oneThousandCheckBox.setSelected(false);
        } else {
            amountTextField.clear();
        }
    }
    public void toggleOneThousand(){
        if(oneThousandCheckBox.isSelected()) {
            amountTextField.setText("1000.0");

            oneHundredCheckBox.setSelected(false);
            twoHundredCheckBox.setSelected(false);
            fiveHundredCheckBox.setSelected(false);
        } else {
            amountTextField.clear();
        }

    }
    public void transferFunds() {
        transferDoneLabel.setVisible(false);
        incorrectDetailsLabel.setVisible(false);
        insufficientBalanceLabel.setVisible(false);

        try {
            // prepare parameters for transfer between accounts
            String accountId = senderAccount.getAccountId();
            String targetAccountId = recieverAccountTextField.getText();
            double amount = Double.parseDouble(amountTextField.getText());
            String message = String.format("Handled by employee: %s", currentEmployee.getUserId());
            LocalDate date = LocalDate.now();

            bank.transfer(accountId, targetAccountId, amount, message, date);
            transferDoneLabel.setVisible(true);
        } catch(InsufficientBalanceException e) {
            insufficientBalanceLabel.setVisible(true);
            insufficientBalanceLabel.setText(String.format("Insufficient balance on account. Only %.2f available.", senderAccount.getBalance()));
        } catch (Exception e) {
            incorrectDetailsLabel.setVisible(true);
        }
    }

    public void showCurrentCustomer() {
        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate currentPrivate = (CustomerPrivate) currentCustomer;
            privateCustomerInfoAnchorPane.setVisible(true);
            corporateCustomerInfoAnchorPane.setVisible(false);
            customerSSNLabel.setText(currentPrivate.getSsn());
            customerNameLabel.setText(currentPrivate.getFullName());
            customerIdLabel.setText(currentPrivate.getUserId());
        } else {
            CustomerCorporate currentCorporate = (CustomerCorporate) currentCustomer;
            privateCustomerInfoAnchorPane.setVisible(false);
            corporateCustomerInfoAnchorPane.setVisible(true);
            companyNameLabel.setText(currentCorporate.getCompanyName());
            companyIdLabel.setText(currentCorporate.getUserId());
            companyOrgNrLabel.setText(currentCorporate.getOrgNumber());
        }
    }

}
