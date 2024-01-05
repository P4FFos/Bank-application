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
import java.util.HashMap;
import java.util.ResourceBundle;

public class EmpAddAccountController extends EmpCustomerOverviewController implements Initializable {
    @FXML
    private AnchorPane loanAnchorPane;
    @FXML
    private AnchorPane creditAnchorPane;
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
    private Label wrongDetailsLabel;
    @FXML
    private TextField newAccountNameField;

    private double amount;
    private Account accountToIncrement;

    public void initialize(URL arg0, ResourceBundle arg1) {
        super.showCurrentEmployee();
        super.showCurrentCustomer();
        populateAccountsChoiceBox();

        amount = 0.0;

        wrongDetailsLabel.setVisible(false);
        accountsChoiceBox.setVisible(false);
        toAccountLabel.setVisible(false);
    }

    public void populateAccountsChoiceBox(){ //list of standard accounts (their keys) to send funds to when creating a credit or a loan
        HashMap<String, Account> standardAccountsMap = new HashMap<>();

        for(String key : currentCustomersAccounts.keySet()) {
            if (!(currentCustomersAccounts.get(key) instanceof Credit) || !(currentCustomersAccounts.get(key) instanceof Loan)) {
                //if an account is not a credit or a loan, then it is a standard account
                //we only want to be able to send funds to standard accounts when creating credits and loans
                standardAccountsMap.put(key, currentCustomersAccounts.get(key));
            }
        }

        String[] standardAccounts = new String[standardAccountsMap.size()];
        int index = 0;

        for(String key : standardAccountsMap.keySet()){
            standardAccounts[index] = key;
            index++;
        }

        accountsChoiceBox.getItems().addAll(standardAccounts);
        accountsChoiceBox.setOnAction(this::setAccountToIncrement);
        accountsChoiceBox.setOnAction(this::setAccountToIncrement);
    }

    public void setAccountToIncrement(ActionEvent event) { //sets accountToIncrement (standard account to send funds to)
        //get key via accountsChoiceBox (String), get account via currentCustomersAccounts (HashMap<String, Account>)
        accountToIncrement = currentCustomersAccounts.get(accountsChoiceBox.getValue());
    }

    public void showError(String message){
        wrongDetailsLabel.setVisible(true);
        wrongDetailsLabel.setText(message);
    }

    public void setAmount(CheckBox checkBox, double chosenAmount){
        if(checkBox.isSelected()){
            amount = chosenAmount;
        } else { //when deselecting a checkbox, amount goes back to 0.0
            amount = 0.0;
        }
    }

    public void saveNewAccount(ActionEvent event) throws Exception {
        String message = "Handled by: " + currentEmployee.getUserId();
        try {
            //----------------------Check for missing info------------------------
            if(newAccountNameField.getText().isEmpty()){
                showError("Enter an account name.");
            } else if(!standardCheckBox.isSelected() && !creditCheckBox.isSelected() && !loanCheckBox.isSelected()){
                showError("Select an account type.");
            //------If standard account, only account name and type are required------
            } else if (standardCheckBox.isSelected()) {
                bank.createAccount(currentCustomer.getUserId(), newAccountNameField.getText());
                backToOverview(event);
            //--------If not standard account, check for further missing info---------
            } else if(accountToIncrement == null){
                showError("Choose an account to send funds to.");
            } else if(amount == 0.0){
                showError("Select an amount.");
            //---If all required information is entered - go ahead and create credit or loan---
            } else if (creditCheckBox.isSelected()) {
                Credit newCreditAccount = bank.createCredit(currentCustomer.getUserId(), newAccountNameField.getText(), Calendar.getInstance(), amount);
                bank.transferFromCreditAccount(newCreditAccount.getAccountId(), accountToIncrement.getAccountId(), 0 - amount, message, LocalDate.now());
                backToOverview(event);
            } else if (loanCheckBox.isSelected()) {
                Loan newLoanAccount = bank.createLoanAccount(currentCustomer.getUserId(), newAccountNameField.getText(), amount);
                bank.transferFromLoanAccount(newLoanAccount.getAccountId(), accountToIncrement.getAccountId(), 0 - amount, message, LocalDate.now());
                backToOverview(event);
            }
        } catch (Exception e) {
            showError("Oops. Something went wrong...");
        }
    }

    public void backToOverview(ActionEvent event) throws IOException { //On saving an account, switches to scene: EmpCustomerOverview
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

