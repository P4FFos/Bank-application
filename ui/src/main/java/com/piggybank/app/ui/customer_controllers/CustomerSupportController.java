package com.piggybank.app.ui.customer_controllers;

import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerSupportController extends CustomerStartController implements Initializable {
    @FXML
    private Label infoActualUserIdLabel;
    @FXML
    private TextArea textArea;
    @FXML
    private Button sendSupportRequestButton;


    public void initialize(URL arg0, ResourceBundle arg1) {
        showCurrentCustomer();
    }

    public void sendSupportRequest(ActionEvent event) {
        if (textArea.getText().isEmpty()) {
            System.out.println("Please explain your problem/question in the text area.");
        } else {
            String request = textArea.getText();
            System.out.println("Your request has been sent.");
            System.out.println(request);
            textArea.clear();
        }
    }

    public void showCurrentCustomer(){
        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            infoActualUserIdLabel.setText(privateCustomer.getUserId());
            System.out.println("Customer Support Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            infoActualUserIdLabel.setText(corporateCustomer.getUserId());
            System.out.println("Customer Support Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }
}
