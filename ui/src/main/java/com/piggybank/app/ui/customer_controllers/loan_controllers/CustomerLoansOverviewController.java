package com.piggybank.app.ui.customer_controllers.loan_controllers;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.customers.money_operations.Loan;

import com.piggybank.app.ui.customer_controllers.CustomerStartController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerLoansOverviewController extends CustomerStartController implements Initializable {

    //@FXML
    //private Button selectLoanButton;
    @FXML
    private Label infoActualUserIdLabel;
    @FXML
    private ListView<Account> loansListView;

	public void initialize(URL arg0, ResourceBundle arg1) {
		showCurrentCustomer();

		
		// fill loansListView with current customer's loans
		for(Account account : currentCustomer.getAccountsList()){
			if(account instanceof Loan){
				loansListView.getItems().add(account);
			}
		}
		
	}

	/*public void selectLoan(){ // show details of selected loan on button click
		Loan selectedLoan = (Loan) loansListView.getSelectionModel().getSelectedItem();
		String details = "";
		details += "Account ID: " + selectedLoan.getAccountId() + "\n";
		details += "Balance: " + selectedLoan.getBalance() + " SEK" + "\n";
		details += "Initial Amount: " + selectedLoan.getInitialAmount() + " SEK" + "\n";
		details += "Interest Rate: " + selectedLoan.getInterestRate() + " %" + "\n";
		details += "Min Payment Percent: " + selectedLoan.getMinPaymentPercent() + " %" + "\n";
		details += "Min Payment Amount: " + selectedLoan.getMinPaymentAmount() + " SEK" + "\n";
		detailsField.setText(details);
		detailsField.setVisible(true);
	}*/

    @Override
    public void showCurrentCustomer(){
        super.showCurrentCustomer();
        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            infoActualUserIdLabel.setText(privateCustomer.getUserId());
            System.out.println("Customer Accounts Overview Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            infoActualUserIdLabel.setText(corporateCustomer.getUserId());
            System.out.println("Customer Accounts Overview Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }
}
