package com.piggybank.app.ui.employee_controllers;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.exceptions.InsufficientBalanceException;
import com.piggybank.app.ui.employee_controllers.customer_operation.EmpCustomerOverviewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmpMakeTransactionController extends EmpCustomerOverviewController implements Initializable{
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
        super.showCurrentCustomer();

        //Populate accountsListView with accountIDs from currentCustomersAccounts
        accountsListView.getItems().addAll(currentCustomer.getAccounts().keySet());

        System.out.println("Employee Make Transaction Page. Logged in as: " + currentEmployee.getInitials());
    }

    public void selectSendingAccount(){ //selectSendingAccountButton
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
            if(senderAccount == null){
                incorrectDetailsLabel.setVisible(true);
                incorrectDetailsLabel.setText("You must select an account.");
            } else {
                incorrectDetailsLabel.setVisible(true);
                incorrectDetailsLabel.setText("Something went wrong. Try again.");
            }

        }
    }
}
