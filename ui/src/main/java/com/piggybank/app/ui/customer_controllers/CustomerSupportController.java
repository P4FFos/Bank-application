package com.piggybank.app.ui.customer_controllers;

import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerSupportController extends CustomerStartController implements Initializable {
    @FXML
    private TextArea textArea;
    @FXML
    private Button sendSupportRequestButton;
    @FXML
    private Label emptyMessageLabel;
    @FXML
    private Label successfulMessageLabel;


    public void initialize(URL arg0, ResourceBundle arg1) {
        showCurrentCustomer();
    }

    public void sendSupportRequest(ActionEvent event) {
        if (textArea.getText().isEmpty()) {
            successfulMessageLabel.setVisible(false);
            emptyMessageLabel.setVisible(true);
            System.out.println("Please explain your problem/question in the text area.");
        } else {
            String request = textArea.getText();
            System.out.println("Your request has been sent.");
            System.out.println(request);
            textArea.clear();
            emptyMessageLabel.setVisible(false);
            successfulMessageLabel.setVisible(true);
        }
    }

    public void showCurrentCustomer(){
        super.showCurrentCustomer();
        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            System.out.println("Customer Support Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            System.out.println("Customer Support Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }
}
