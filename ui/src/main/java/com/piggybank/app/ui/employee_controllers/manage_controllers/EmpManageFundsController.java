package com.piggybank.app.ui.employee_controllers.manage_controllers;

import com.piggybank.app.backend.customers.money_operations.Credit;
import com.piggybank.app.backend.customers.money_operations.Loan;
import com.piggybank.app.ui.employee_controllers.customer_operation.EmpCustomerOverviewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmpManageFundsController extends EmpCustomerOverviewController implements Initializable {
    @FXML
    private CheckBox withdrawCheckBox;
    @FXML
    private CheckBox depositCheckBox;
    @FXML
    private Label accountLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private TextField amountTextField;

    public void initialize(URL arg0, ResourceBundle arg1) {
        super.showCurrentEmployee(); //super: EmpMainController (grandparent)
        super.showCurrentCustomer(); //super: EmpCustomerOverviewController (parent)

        accountLabel.setText(currentAccount.getAccountName());
        balanceLabel.setText(currentAccount.getBalanceString());
        errorMessageLabel.setVisible(false);
    }

    public void showError(String msg){
        errorMessageLabel.setVisible(true);
        errorMessageLabel.setText(msg);
    }

    public void confirm() throws Exception { // confirmButton
        double currentBalance = currentAccount.getBalance();
        double amount = 0.0;
        String enteredAmount = amountTextField.getText();
        String message = "Handled by: " + currentEmployee.getUserId();

        //check for missing info
        if(!withdrawCheckBox.isSelected() && !depositCheckBox.isSelected()){
            showError("You must choose either withdraw or deposit.");
        } else if (enteredAmount.isEmpty()){
            showError("You must enter an amount.");
        } else {
            amount = getDouble(enteredAmount); //get entered amount as double
        }

        if(withdrawCheckBox.isSelected()){
            if(currentAccount instanceof Credit || currentAccount instanceof Loan){
                showError("You cannot withdraw from a credit or loan account.");
            } else if (currentBalance < amount){
                showError("You do not have enough funds in this account.");
            } else {
                currentAccount.withdraw(amount, message, LocalDate.now());
            }
        } else if (depositCheckBox.isSelected()){
            currentAccount.deposit("None", amount, message, LocalDate.now());
        }
        balanceLabel.setText(currentAccount.getBalanceString());
    }

    public double getDouble(String input){ //parses string to double if input is valid, otherwise returns 0.0
        String amountStr = "";

        if(input.charAt(0) == '-'){
            showError("You cannot deposit or withdraw a negative amount.");
            return 0.0;
        }

        for(int i = 0; i < input.length(); i++){
            if(!Character.isDigit(input.charAt(i))){
                showError("You must enter only digits.");
                return 0.0;
            } else {
                amountStr += input.charAt(i);
                errorMessageLabel.setVisible(false);
            }
        }

        return Double.parseDouble(amountStr);
    }

    public void toggleWithdraw(){
        depositCheckBox.setSelected(false);
    }

    public void toggleDeposit(){
        withdrawCheckBox.setSelected(false);
    }
}
