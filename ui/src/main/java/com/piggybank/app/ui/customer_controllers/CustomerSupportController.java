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
        } else {
            textArea.clear();
            emptyMessageLabel.setVisible(false);
            successfulMessageLabel.setVisible(true);
        }
    }
}
