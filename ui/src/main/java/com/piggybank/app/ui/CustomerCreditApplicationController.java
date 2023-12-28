package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerCreditApplicationController extends CustomerStartController implements Initializable {
    @FXML
    private Button amountInfoButton;
    @FXML
    private Button applyButton;
    @FXML
    private CheckBox firstAmountCheckBox;
    @FXML
    private CheckBox secondAmountCheckBox;
    @FXML
    private CheckBox thirdAmountCheckBox;
    @FXML
    private CheckBox fourthAmountCheckBox;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField annualIncomeTextField;
    @FXML
    private TextField enterAmountTextField;
    @FXML
    private Label infoActualUserIdLabel;


    public void initialize(URL arg0, ResourceBundle arg1) {
        showCurrentCustomer();
    }

    @Override
    public void showCurrentCustomer(){
        super.showCurrentCustomer();
        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            infoActualUserIdLabel.setText(privateCustomer.getUserId());
            System.out.println("Customer Credit Application Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            infoActualUserIdLabel.setText(corporateCustomer.getUserId());
            System.out.println("Customer Credit Application Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }
}
