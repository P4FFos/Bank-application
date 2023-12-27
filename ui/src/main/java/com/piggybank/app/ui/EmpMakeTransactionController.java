package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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


    public void initialize(URL arg0, ResourceBundle arg1) {
        super.showCurrentEmployee();
        showCurrentCustomer();

        //Populate accountsListView with accountIDs from currentCustomersAccounts

        System.out.println("Employee Make Transaction Page. Logged in as: " + currentEmployee.getInitials());
    }

    public void selectSendingAccount(){

    }
    public void toggleOneHundred(){

    }
    public void toggleTwoHundred(){

    }
    public void toggleFiveHundred(){

    }
    public void toggleOneThousand(){

    }
    public void transferFunds(){

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
