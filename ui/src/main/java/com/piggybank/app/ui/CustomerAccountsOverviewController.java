package com.piggybank.app.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CustomerAccountsOverviewController extends CustomerStartController {

    @FXML
    private Label accountsAccountHistoryLabel;

    @FXML
    private Label accountsAccountsOverviewLabel;

    @FXML
    private CheckBox accountsAllCheckBox;

    @FXML
    private Label accountsDisplayLabel;

    @FXML
    private CheckBox accountsIncomingCheckBox;

    @FXML
    private ListView<?> accountsListView;

    @FXML
    private Label accountsMyAccountsLabel;

    @FXML
    private CheckBox accountsOutgoingCheckBox;

    @FXML
    private AnchorPane accountsOverviewAnchorPane;

    @FXML
    private Button accountsSelectButton;

    @FXML
    private ListView<?> accountsTransactionsListView;

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private AnchorPane bodySeparatorsAnchorPane;

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

}
