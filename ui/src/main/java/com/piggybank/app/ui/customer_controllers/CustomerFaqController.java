package com.piggybank.app.ui.customer_controllers;

import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFaqController extends CustomerStartController implements Initializable {

    public void initialize(URL arg0, ResourceBundle arg1) {
        showCurrentCustomer();
    }

    @Override
    public void showCurrentCustomer(){
        super.showCurrentCustomer();
        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            System.out.println("Customer FAQ Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            System.out.println("Customer FAQ Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }
}
