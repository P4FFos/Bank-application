package com.piggybank.app.ui.customer_controllers.credit_controllers;

import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.customers.money_operations.Credit;
import com.piggybank.app.ui.customer_controllers.CustomerStartController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class CustomerCreditsOverviewController extends CustomerStartController implements Initializable {
    @FXML
    private Label infoActualUserIdLabel;
    @FXML
    private ListView<String> creditsListView;

    public void initialize(URL arg0, ResourceBundle arg1) {
        showCurrentCustomer();
        initializeListView();
    }

    @Override
    public void showCurrentCustomer(){
        super.showCurrentCustomer();
        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            infoActualUserIdLabel.setText(privateCustomer.getUserId());
            System.out.println("Customer Credits Overview Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            infoActualUserIdLabel.setText(corporateCustomer.getUserId());
            System.out.println("Customer Credits Overview Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }

    public void initializeListView(){
        Set<String> currentCustomerCreditAccounts = new HashSet<>();
        for(String key : currentCustomersAccounts.keySet()){
            if(currentCustomersAccounts.get(key) instanceof Credit){
                currentCustomerCreditAccounts.add(key);
            }
        }
        creditsListView.getItems().addAll(currentCustomerCreditAccounts);
    }

}
