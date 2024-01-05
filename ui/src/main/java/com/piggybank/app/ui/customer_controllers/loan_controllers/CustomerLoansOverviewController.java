package com.piggybank.app.ui.customer_controllers.loan_controllers;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.money_operations.Loan;
import com.piggybank.app.ui.customer_controllers.CustomerStartController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerLoansOverviewController extends CustomerStartController implements Initializable {
    @FXML
    private ListView<Account> loansListView;

	public void initialize(URL arg0, ResourceBundle arg1) {
		showCurrentCustomer();
        initializeListView();
	}

    public void initializeListView(){
        // fill loansListView with current customer's loans
        for(Account account : currentCustomer.getAccountsList()){
            if(account instanceof Loan){
                loansListView.getItems().add(account);
            }
        }
    }
}

