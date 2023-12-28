package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.customers.debts.Credit;
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
    private Button applyForNewCreditButton;
    @FXML
    private Button selectCreditButton;
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
            System.out.println("Customer Accounts Overview Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            infoActualUserIdLabel.setText(corporateCustomer.getUserId());
            System.out.println("Customer Accounts Overview Page. Logged in as: " + corporateCustomer.getCompanyName());
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

    public void selectCredit(){ //selectCreditButton

    }

    public void apply(ActionEvent event) throws IOException { //applyForNewCreditButton
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerCreditApplication.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
