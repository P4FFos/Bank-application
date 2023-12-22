package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CustomerFaqController extends CustomerStartController{

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private AnchorPane faqAnchorPane;

    @FXML
    private Label faqAnswer1Label;

    @FXML
    private Label faqAnswer2Label;

    @FXML
    private Label faqAnswer3Label;

    @FXML
    private Label faqAnswer4Label;

    @FXML
    private Label faqAnswer5Label;

    @FXML
    private Label faqAnswer5Label1;

    @FXML
    private Label faqAnswer6Label;

    @FXML
    private Label faqAnswer7Label;

    @FXML
    private Label faqHeaderLabel;

    @FXML
    private Label faqQuestion1Label;

    @FXML
    private Label faqQuestion2Label;

    @FXML
    private Label faqQuestion3Label;

    @FXML
    private Label faqQuestion4Label;

    @FXML
    private Label faqQuestion5Label;

    @FXML
    private Label faqQuestion6Label;

    @FXML
    private Label faqQuestion7Label;

    @FXML
    private Label headerActualIdLabel;

    @FXML
    private AnchorPane headerAnchorPane;

    @FXML
    private Label headerBankNameLabel;

    @FXML
    private Label headerCustomerNameLabel;

    @FXML
    private ImageView headerIconImageView;

    @FXML
    private ImageView headerIconShadedImageView;

    @FXML
    private Label headerIdLabel;

    @FXML
    private Button headerLogoutButton;

    @FXML
    private Separator horisontalSeparator;

    @FXML
    private Button sideMenuAccountsOverviewButton;

    @FXML
    private AnchorPane sideMenuAnchorPane;

    @FXML
    private Button sideMenuCreditsButton1;

    @FXML
    private Button sideMenuFaqButton;

    @FXML
    private Label sideMenuLabel;

    @FXML
    private Button sideMenuLoansButton;

    @FXML
    private Button sideMenuStartButton;

    @FXML
    private Button sideMenuSupportButton;

    @FXML
    private Button sideMenuTransferFundsButton;

    @FXML
    private Separator verticalSeparator;

    public void showCurrentCustomer(){
        //if there is a customer ID label:
        //customerIdLabel.setText(currentCustomer.getUserId());

        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            headerCustomerNameLabel.setText(privateCustomer.getFullName());
            headerActualIdLabel.setText(privateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            headerCustomerNameLabel.setText(corporateCustomer.getCompanyName());
            headerActualIdLabel.setText(corporateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }

}
