package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class EmpManageLoansController extends EmpMainController implements Initializable {
    @FXML
    private AnchorPane privateCustomerInfoAnchorPane;
    @FXML
    private AnchorPane corporateCustomerInfoAnchorPane;
    @FXML
    private Button selectedLoanButton;
    @FXML
    private Button addLoanButton;
    @FXML
    private ListView<Account> loansListView;
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
    private Label empIdLabel;
    @FXML
    private Label empInitialsLabel;
    @FXML
    private Label loanIdLabel;
    @FXML
    private Label debtLabel;
    @FXML
    private Label loanAmountLabel;


    public void initialize(URL arg0, ResourceBundle arg1) {
        super.showCurrentEmployee();
        showCurrentCustomer();

        System.out.println("Employee Pending Loan/Credit Applications Page. Logged in as: " + currentEmployee.getInitials());
    }

    public void setCurrentLoan() {

    }

    public void addLoan() {

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
            privateCustomerInfoAnchorPane.setVisible(false);
            corporateCustomerInfoAnchorPane.setVisible(true);
            companyNameLabel.setText(currentCorporate.getCompanyName());
            companyIdLabel.setText(currentCorporate.getUserId());
            companyOrgNrLabel.setText(currentCorporate.getOrgNumber());
        }
    }
}
