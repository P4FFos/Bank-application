package com.piggybank.app.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;


public class CustomerMainController implements Initializable {

    /* 1.0 BASE [GENERAL] : INCL. SEPARATORS */
    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private Separator horisontalSeparator;

    @FXML
    private Separator verticalSeparator;


    /* 1.1 HEADER */
    @FXML
    private AnchorPane headerAnchorPane;

    @FXML
    private Circle headerBankLogotype;

    @FXML
    private ImageView headerIconImageView;

    @FXML
    private ImageView headerIconShadedImageView;

    @FXML
    private Label headerBankNameLabel;

    @FXML
    private Label headerIdLabel;

    @FXML
    private Label headerActualIdLabel;

    @FXML
    private Label headerCustomerNameLabel;

    @FXML
    private Button headerLogoutButton;


    /* 1.2 MENU */
    @FXML
    private AnchorPane sideMenuAnchorPane;

    @FXML
    private Label sideMenuLabel;

    @FXML
    private Button sideMenuStartButton;

    @FXML
    private Button sideMenuAccountsOverviewButton;

    @FXML
    private Button sideMenuTransferFundsButton;

    @FXML
    private Button sideMenuLoansButton;

    @FXML
    private Button sideMenuFaqButton;

    @FXML
    private Button sideMenuSupportButton;


    /* 1.3 CUSTOMER INFO */
    @FXML
    private AnchorPane infoAnchorPane;

    @FXML
    private Label infoNameLabel;

    @FXML
    private Label infoAccountIdLabel;

    @FXML
    private Label infoActualAccountIdLabel;


    /* 1.4 BODY [GENERAL] : PARENT FOR BODY SIZING */
    @FXML
    private AnchorPane bodyAnchorPane;


    /* 1.4.1 BODY [START] */
    @FXML
    private AnchorPane startAnchorPane;

    @FXML
    private Label startAssetsLabel;

    @FXML
    private ListView<?> startAssetsListView;

    @FXML
    private Label startTotalBalanceLabel;

    @FXML
    private Label startActualTotalBalanceLabel;

    @FXML
    private Label startLoansLabel;

    @FXML
    private ListView<?> startLoansListView;

    @FXML
    private Label startTotalDebtLabel;

    @FXML
    private Label startActualTotalDebtLabel;


    /* 1.4.2 BODY [ACCOUNT OVERVIEW] */
    @FXML
    private AnchorPane accountsAnchorPane;

    @FXML
    private Label accountsMyAccountsLabel;

    @FXML
    private Label accountsAccountHistoryLabel;

    @FXML
    private Label displayLabel;

    @FXML
    private CheckBox allCheckBox;

    @FXML
    private CheckBox incomingCheckBox;

    @FXML
    private CheckBox outgoingCheckBox;

        // OPTION 1: LIST VIEW
    @FXML
    private ListView<?> accountsListView;

    @FXML
    private ListView<?> accountsListView1;

        // OPTION 2: TABLE VIEW
    @FXML
    private TableView<?> accountsTableView;

    @FXML
    private TableColumn<?, String> accountTableColumn;

    @FXML
    private TableColumn<?, Double> balanceTableColumn;



    /* 1.4.3 BODY [TRANSFER FUNDS] */
    @FXML
    private AnchorPane transferAnchorPane;


    /* 1.4.4 BODY [LOANS] */
    @FXML
    private AnchorPane loansAnchorPane;


    /* 1.4.5 BODY [FAQ] */
    @FXML
    private Label faqHeaderLabel;

    @FXML
    private AnchorPane faqAnchorPane;

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



    /* 1.4.6 BODY [SUPPORT] */
    @FXML
    private AnchorPane supportAnchorPane;


    /* METHODS */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // accountTableColumn.setCellFactory(new PropertyValueFactory<Customer, String>("account"));
        // balanceTableColumn.setCellFactory(new PropertyValueFactory<Customer, Double>("balance"));

    }

}
