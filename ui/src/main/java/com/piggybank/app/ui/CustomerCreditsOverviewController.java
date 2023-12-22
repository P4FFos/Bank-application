package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CustomerCreditsOverviewController extends CustomerStartController {

    @FXML
    private Button applyForNewCreditButton;

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private AnchorPane bodySeparatorsAnchorPane;

    @FXML
    private ListView<?> creditsListView;

    @FXML
    private AnchorPane creditsOverviewAnchorPane;

    @FXML
    private Label creditsOverviewLabel;

    @FXML
    private Label currentCreditsLabel;

    @FXML
    private Label detailsLabel;

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
    private Label infoAccountIdLabel;

    @FXML
    private Label infoActualUserIdLabel;

    @FXML
    private AnchorPane infoAnchorPane;

    @FXML
    private Label infoNameLabel;

    @FXML
    private Button selectCreditButton;

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
            infoNameLabel.setText(privateCustomer.getFullName());
            infoActualUserIdLabel.setText(privateCustomer.getUserId());
            headerActualIdLabel.setText(privateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            headerCustomerNameLabel.setText(corporateCustomer.getCompanyName());
            infoNameLabel.setText(corporateCustomer.getCompanyName());
            infoActualUserIdLabel.setText(corporateCustomer.getUserId());
            headerActualIdLabel.setText(corporateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }
}
