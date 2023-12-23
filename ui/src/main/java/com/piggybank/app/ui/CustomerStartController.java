package com.piggybank.app.ui;

import com.piggybank.app.backend.Bank;
import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.customers.Transaction;
import com.piggybank.app.backend.customers.debts.Credit;
import com.piggybank.app.backend.customers.loans.Loan;
import com.piggybank.app.backend.utils.FileHandler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;

public class CustomerStartController implements Initializable {

    @FXML
    private AnchorPane baseAnchorPane;

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
    private Button sideMenuCreditsButton;

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
    private Label startActualTotalBalanceLabel;

    @FXML
    private Label startActualTotalDebtLabel;

    @FXML
    private AnchorPane startAnchorPane;

    @FXML
    private Label startAssetsLabel;

    @FXML
    private Label startLoansLabel;

    @FXML
    private ListView<?> startLoansListView;

    @FXML
    private Label startNameLabel;

    @FXML
    private Label startTotalBalanceLabel;

    @FXML
    private Label startTotalDebtLabel;

    @FXML
    private Label start_Hello_Label;

    @FXML
    private Separator verticalSeparator;


    @FXML
    private Circle headerBankLogotype;



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

    @FXML
    private TableView<Account> accountsTableView;

    @FXML
    private TableColumn<Account, String> accountsTableColumn;

    @FXML
    private TableColumn<Account, Double> balanceTableColumn;



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

    private FXMLLoader loader;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public static Bank bank = UIMain.bank;
    public static Customer currentCustomer;

	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // accountTableColumn.setCellFactory(new PropertyValueFactory<Customer, String>("account"));
        // balanceTableColumn.setCellFactory(new PropertyValueFactory<Customer, Double>("balance"));
    }

    public void setCurrentCustomer(Customer customer){ //Method called from CustomerLoginController
        currentCustomer = customer;
        showCurrentCustomer();
    }

    public void showCurrentCustomer(){
        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            headerCustomerNameLabel.setText(privateCustomer.getFullName());
            startNameLabel.setText(privateCustomer.getFullName());
            headerActualIdLabel.setText(privateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            headerCustomerNameLabel.setText(corporateCustomer.getCompanyName());
            startNameLabel.setText(corporateCustomer.getCompanyName());
            headerActualIdLabel.setText(corporateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }

	public void showAssetsOverview() {
		// set table columns
		accountsTableColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountName"));
		balanceTableColumn.setCellValueFactory(new PropertyValueFactory<Account, Double>("balance"));
		accountsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

		// fill assets table
		accountsTableView.setItems(currentCustomer.getAccountsOL());

		// set total assets balance
		double totalAssetsBalance = 0;
		for(Account account : currentCustomer.getAccounts().values()){
			if(!(account instanceof Credit) && !(account instanceof Loan)){
				totalAssetsBalance = totalAssetsBalance + account.getBalance();
			}
		}

		startActualTotalBalanceLabel.setText(String.format("%.2f SEK", totalAssetsBalance));
	}

    //-----------------SIDE MENU NAVIGATION----------------
    public void goToStart(ActionEvent event) throws IOException { //sideMenuStartButton
        loader = new FXMLLoader(getClass().getResource("CustomerStart.fxml"));
        root = loader.load();

        CustomerStartController controller = loader.getController();
        controller.showCurrentCustomer();
		controller.showAssetsOverview();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToAccountsOverview(ActionEvent event) throws IOException { //sideMenuAccountsOverviewButton
        loader = new FXMLLoader(getClass().getResource("CustomerAccountsOverview.fxml"));
        root = loader.load();

        CustomerAccountsOverviewController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToTransferFunds(ActionEvent event) throws IOException { //sideMenuTransferFundsButton
        loader = new FXMLLoader(getClass().getResource("CustomerTransferFunds.fxml"));
        root = loader.load();

        CustomerTransferFundsController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToLoans(ActionEvent event) throws IOException { //sideMenuLoansButton
        loader = new FXMLLoader(getClass().getResource("CustomerLoansOverview.fxml"));
        root = loader.load();

        CustomerLoansOverviewController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToCredits(ActionEvent event) throws IOException { //sideMenuCreditsButton
        loader = new FXMLLoader(getClass().getResource("CustomerCreditsOverview.fxml"));
        root = loader.load();

        CustomerCreditsOverviewController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToFaq(ActionEvent event) throws IOException { //sideMenuFaqButton
        loader = new FXMLLoader(getClass().getResource("CustomerFaq.fxml"));
        root = loader.load();

        CustomerFaqController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToSupport(ActionEvent event) throws IOException { //sideMenuSupportButton
        loader = new FXMLLoader(getClass().getResource("CustomerSupport.fxml"));
        root = loader.load();

        CustomerSupportController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//-----------------HEADER MENU NAVIGATION----------------

    public void logout(ActionEvent event) throws IOException { //headerLogoutButton
        String saveFile = "ui/src/main/java/com/piggybank/app/backend/bankDataOnLogout.json";
        FileHandler.jsonSerializer(saveFile, bank);

        currentCustomer = null;
        System.out.println("Logged out. Have a nice day.");
        loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
