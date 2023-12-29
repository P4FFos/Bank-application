package com.piggybank.app.ui.employee_controllers.manage_controllers;

import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.ui.employee_controllers.EmpMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EmpManageFundsController extends EmpMainController implements Initializable {
    @FXML
    private AnchorPane privateCustomerInfoAnchorPane;
    @FXML
    private AnchorPane corporateCustomerInfoAnchorPane;
    @FXML
    private Button okButton;
    @FXML
    private CheckBox withdrawCheckBox;
    @FXML
    private CheckBox depositCheckBox;
    @FXML
    private Label accountLabel;
    @FXML
    private Label balanceLabel;
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
    private TextField amountTextField;

    public void initialize(URL arg0, ResourceBundle arg1) {
        super.showCurrentEmployee();
        showCurrentCustomer();

        accountLabel.setText(currentAccount.getAccountName());
        balanceLabel.setText(Double.toString(currentAccount.getBalance()));

        System.out.println("Employee Manage Funds (deposit/withdraw) Page. Logged in as: " + currentEmployee.getInitials());
    }

    public void adjustFunds(){ // okButton
        double currentBalance = currentAccount.getBalance();
        double amount = 0.0;
        String enteredAmount = amountTextField.getText();

        if(!withdrawCheckBox.isSelected() && !depositCheckBox.isSelected()){
            System.out.println("You must choose either withdraw or deposit.");
        } else if (enteredAmount.isEmpty()){
            System.out.println("You must enter an amount.");
        } else {
            amount = extractAmount(enteredAmount);
        }

        if(withdrawCheckBox.isSelected()){
            if(currentBalance < amount){
                System.out.println("You do not have that much funds to withdraw from this account.");
            } else {
                currentAccount.setBalance(currentBalance - amount);
            }
        } else if (depositCheckBox.isSelected()){
            currentAccount.setBalance(currentBalance + amount);
        }
        balanceLabel.setText(Double.toString(currentAccount.getBalance()));
    }

    public double extractAmount(String input){
        String amountStr = "";
        for(int i = 0; i < input.length(); i++){
            if(!Character.isDigit(input.charAt(i))){
                System.out.println("You must enter only digits.");
                return 0.0;
            } else {
                amountStr += input.charAt(i);
                System.out.println("AmountStr: " + amountStr); //delete this row later
            }
        }
        double amount = Double.parseDouble(amountStr);
        System.out.println(amount);
        return amount;
    }

    public void toggleWithdraw(){
        depositCheckBox.setSelected(false);
    }

    public void toggleDeposit(){
        withdrawCheckBox.setSelected(false);
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
