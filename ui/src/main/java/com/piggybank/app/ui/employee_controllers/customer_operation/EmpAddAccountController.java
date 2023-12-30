package com.piggybank.app.ui.employee_controllers.customer_operation;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.money_operations.Credit;
import com.piggybank.app.backend.customers.money_operations.Loan;
import com.piggybank.app.ui.employee_controllers.EmpMainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class EmpAddAccountController extends EmpCustomerOverviewController implements Initializable {
    @FXML
    private AnchorPane loanAnchorPane;
    @FXML
    private AnchorPane creditAnchorPane;
    @FXML
    private Button saveNewAccountButton;
    @FXML
    private CheckBox creditCheckBox;
    @FXML
    private CheckBox fiftyKCheckBox;
    @FXML
    private CheckBox fiveKCheckBox;
    @FXML
    private CheckBox fiveMillionCheckBox;
    @FXML
    private CheckBox oneMillionCheckBox;
    @FXML
    private CheckBox halfMillionCheckBox;
    @FXML
    private CheckBox loanCheckBox;
    @FXML
    private CheckBox standardCheckBox;
    @FXML
    private CheckBox tenKCheckBox;
    @FXML
    private CheckBox twentyFiveKCheckBox;
    @FXML
    private CheckBox twoHalfMillionCheckBox;
    @FXML
    private ChoiceBox<String> accountsChoiceBox;
    @FXML
    private Label toAccountLabel;
    @FXML
    private TextField newAccountNameField;
    @FXML
    private Label wrongDetailsLabel;

    private double amount;
    private Account accountToIncrement;

    public void initialize(URL arg0, ResourceBundle arg1) {
        super.showCurrentEmployee();
        super.showCurrentCustomer();
        populateAccountsChoiceBox();

        System.out.println("Employee Add Account Page. Logged in as: " + currentEmployee.getInitials());
    }

    public void populateAccountsChoiceBox(){
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
        accountsChoiceBox.setOnAction(this::setAccountToIncrement);
        accountsChoiceBox.setOnAction(this::setAccountToIncrement);
    }

    public void setAccountToIncrement(ActionEvent event) {
        accountToIncrement = currentCustomersAccounts.get(accountsChoiceBox.getValue());
    }

    public void adjustFunds(Account account, double amount) { // okButton
        double currentBalance = account.getBalance();
        account.setBalance(currentBalance + Math.abs(amount));
        System.out.println("New balance: " + account.getBalance());
    }

    public void saveNewAccount(ActionEvent event) throws Exception {
        try {
            if (standardCheckBox.isSelected()) {
                bank.createAccount(currentCustomer.getUserId(), newAccountNameField.getText());
                backToOverview(event);
            } else if (creditCheckBox.isSelected()) {
                bank.createCredit(currentCustomer.getUserId(), newAccountNameField.getText(), Calendar.getInstance(), amount);
                adjustFunds(accountToIncrement, amount);
                backToOverview(event);
            } else if (loanCheckBox.isSelected()) {
                bank.createLoanAccount(currentCustomer.getUserId(), newAccountNameField.getText(), amount);
                adjustFunds(accountToIncrement, amount);
                backToOverview(event);
            }
        } catch (Exception e) {
            wrongDetailsLabel.setText(wrongDetailsLabel.getText());
            wrongDetailsLabel.setVisible(true);
        }
    }

    public void backToOverview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/employee_scenes/customer_operation/EmpCustomerOverview.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //------------Standard Account--------------
    public void toggleStandard() {
        loanAnchorPane.setVisible(false);
        creditAnchorPane.setVisible(false);
        toAccountLabel.setVisible(false);
        accountsChoiceBox.setVisible(false);
        creditCheckBox.setSelected(false);
        loanCheckBox.setSelected(false);
    }

    //------------Loan Account--------------
    public void toggleLoan() {
        loanAnchorPane.setVisible(true);
        creditAnchorPane.setVisible(false);
        creditCheckBox.setSelected(false);
        standardCheckBox.setSelected(false);
        toAccountLabel.setVisible(true);
        accountsChoiceBox.setVisible(true);
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

    //------------Credit Account--------------
    public void toggleCredit() {
        loanAnchorPane.setVisible(false);
        creditAnchorPane.setVisible(true);
        standardCheckBox.setSelected(false);
        loanCheckBox.setSelected(false);
        toAccountLabel.setVisible(true);
        accountsChoiceBox.setVisible(true);
    }

    public void toggleFiveKCredit() {
        tenKCheckBox.setSelected(false);
        twentyFiveKCheckBox.setSelected(false);
        fiftyKCheckBox.setSelected(false);
        amount = -5000.0;
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

}

