package com.piggybank.app.ui.employee_controllers.customer_operation;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.money_operations.Credit;
import com.piggybank.app.backend.customers.money_operations.Loan;
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
import java.time.LocalDate;
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
        wrongDetailsLabel.setVisible(false);
    }

    public void populateAccountsChoiceBox(){
        String[] standardAccounts = new String[currentCustomersAccounts.size()]; //Tanya: not pretty I know...
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

    public void showError(String message){
        wrongDetailsLabel.setVisible(true);
        wrongDetailsLabel.setText(message);
    }

    public void setAmount(CheckBox checkBox, double chosenAmount){
        if(checkBox.isSelected()){
            amount = chosenAmount;
        } else {
            amount = 0;
        }
    }

    public void saveNewAccount(ActionEvent event) {
        String message = "Handled by: " + currentEmployee.getUserId();
        try {
            if (standardCheckBox.isSelected()) {
                bank.createAccount(currentCustomer.getUserId(), newAccountNameField.getText());
                backToOverview(event);
            } else if (creditCheckBox.isSelected()) {
                if(accountToIncrement == null){
                    wrongDetailsLabel.setText("Choose an account to send funds to.");
                    wrongDetailsLabel.setVisible(true);
                } else {
                    Credit newCreditAccount = bank.createCredit(currentCustomer.getUserId(), newAccountNameField.getText(), Calendar.getInstance(), amount);
                    bank.transferFomCreditAccount(newCreditAccount.getAccountId(), accountToIncrement.getAccountId(), 0 - amount, message, LocalDate.now());
                    backToOverview(event);
                }

            } else if (loanCheckBox.isSelected()) {
                if(accountToIncrement == null){
                    wrongDetailsLabel.setText("Choose an account to send funds to.");
                    wrongDetailsLabel.setVisible(true);
                } else {
                    Loan newLoanAccount = bank.createLoanAccount(currentCustomer.getUserId(), newAccountNameField.getText(), amount);
                    bank.transferFomLoanAccount(newLoanAccount.getAccountId(), accountToIncrement.getAccountId(), 0 - amount, message, LocalDate.now());
                    backToOverview(event);
                }
            }
        } catch (Exception e) {
            showError("Oops. Something went wrong...");
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
        creditCheckBox.setSelected(false);
        standardCheckBox.setSelected(false);
        if(loanCheckBox.isSelected()){
            loanAnchorPane.setVisible(true);
            creditAnchorPane.setVisible(false);
            toAccountLabel.setVisible(true);
            accountsChoiceBox.setVisible(true);
        } else {
            loanAnchorPane.setVisible(false);
            toAccountLabel.setVisible(false);
            accountsChoiceBox.setVisible(false);
        }
    }

    public void toggleHalfMillionLoan() {
        oneMillionCheckBox.setSelected(false);
        twoHalfMillionCheckBox.setSelected(false);
        fiveMillionCheckBox.setSelected(false);
        setAmount(halfMillionCheckBox, -500000.0);
    }

    public void toggleOneMillionLoan() {
        halfMillionCheckBox.setSelected(false);
        twoHalfMillionCheckBox.setSelected(false);
        fiveMillionCheckBox.setSelected(false);
        setAmount(oneMillionCheckBox, -1000000.0);

    }

    public void toggleTwoHalfMillionLoan() {
        halfMillionCheckBox.setSelected(false);
        oneMillionCheckBox.setSelected(false);
        fiveMillionCheckBox.setSelected(false);
        setAmount(twoHalfMillionCheckBox, -2500000.0);
    }

    public void toggleFiveMillionLoan() {
        halfMillionCheckBox.setSelected(false);
        oneMillionCheckBox.setSelected(false);
        twoHalfMillionCheckBox.setSelected(false);
        setAmount(fiveMillionCheckBox, -5000000.0);
    }

    //------------Credit Account--------------
    public void toggleCredit() {
        standardCheckBox.setSelected(false);
        loanCheckBox.setSelected(false);
        if(creditCheckBox.isSelected()){
            loanAnchorPane.setVisible(false);
            creditAnchorPane.setVisible(true);
            toAccountLabel.setVisible(true);
            accountsChoiceBox.setVisible(true);
        } else {
            creditAnchorPane.setVisible(false);
            toAccountLabel.setVisible(false);
            accountsChoiceBox.setVisible(false);
        }

    }

    public void toggleFiveKCredit() {
        tenKCheckBox.setSelected(false);
        twentyFiveKCheckBox.setSelected(false);
        fiftyKCheckBox.setSelected(false);
        setAmount(fiveKCheckBox, -5000.0);
    }

    public void toggleTenKCredit() {
        fiveKCheckBox.setSelected(false);
        twentyFiveKCheckBox.setSelected(false);
        fiftyKCheckBox.setSelected(false);
        setAmount(tenKCheckBox, -10000.0);
    }

    public void toggleTwentyFiveKCredit() {
        fiveKCheckBox.setSelected(false);
        tenKCheckBox.setSelected(false);
        fiftyKCheckBox.setSelected(false);
        setAmount(twentyFiveKCheckBox, -25000.0);
    }

    public void toggleFiftyKCredit() {
        fiveKCheckBox.setSelected(false);
        tenKCheckBox.setSelected(false);
        twentyFiveKCheckBox.setSelected(false);
        setAmount(fiftyKCheckBox, -50000.0);
    }

}

