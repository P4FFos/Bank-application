package com.piggybank.app.ui.customer_controllers.credit_controllers;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.customers.money_operations.Credit;
import com.piggybank.app.ui.customer_controllers.CustomerStartController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerCreditsOverviewController extends CustomerStartController implements Initializable {
    @FXML
    private ListView<Account> creditsListView;

    public void initialize(URL arg0, ResourceBundle arg1) {
        showCurrentCustomer();
        initializeListView();
    }

    public void initializeListView(){
        for(Account account : currentCustomer.getAccountsList()){
            if(account instanceof Credit){
                creditsListView.getItems().add(account);
            }
        }
    }
}
