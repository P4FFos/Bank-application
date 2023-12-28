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
    private CheckBox agreeCheckBox;

    @FXML
    private Label amountHintLabel;

    @FXML
    private Button amountInfoButton;

    @FXML
    private Label amountSekLabel;

    @FXML
    private Label annualIncomeHintLabel;

    @FXML
    private Label annualIncomeLabel;

    @FXML
    private Label annualIncomeSekLabel;

    @FXML
    private TextField annualIncomeTextField;

    @FXML
    private Button applyButton;

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private AnchorPane bodySeparatorsAnchorPane;

    @FXML
    private AnchorPane creditApplicationAnchorPane;

    @FXML
    private Label creditApplicationLabel;

    @FXML
    private Label desiredAmountLabel;

    @FXML
    private TextField enterAmountTextField;

    @FXML
    private Label enterPasswordLabel;

    @FXML
    private CheckBox firstAmountCheckBox;

    @FXML
    private CheckBox fourthAmountCheckBox;

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
    private Label infoActualAccountIdLabel;

    @FXML
    private AnchorPane infoAnchorPane;

    @FXML
    private Label infoNameLabel;

    @FXML
    private Label informationSectionLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordHintLabel;

    @FXML
    private CheckBox secondAmountCheckBox;

    @FXML
    private Label sectionHeadingLabel;

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
    private Label termsConditionsLabel;

    @FXML
    private CheckBox thirdAmountCheckBox;

    @FXML
    private Separator verticalSeparator;
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
            System.out.println("Customer Accounts Overview Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            infoActualUserIdLabel.setText(corporateCustomer.getUserId());
            System.out.println("Customer Accounts Overview Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }
}
