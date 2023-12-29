package com.piggybank.app.ui.customer_controllers;

import com.piggybank.app.backend.Bank;
import com.piggybank.app.backend.customers.*;
import com.piggybank.app.backend.customers.money_operations.Credit;
import com.piggybank.app.backend.customers.money_operations.Loan;
import com.piggybank.app.backend.utils.FileHandler;

import com.piggybank.app.ui.UIMain;
import com.piggybank.app.ui.customer_controllers.credit_controllers.CustomerCreditsOverviewController;
import com.piggybank.app.ui.customer_controllers.loan_controllers.CustomerLoansOverviewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;



public class CustomerStartController implements Initializable {

    @FXML
    private Button headerLogoutButton;
    @FXML
    private Button sideMenuAccountsOverviewButton;
    @FXML
    private Button sideMenuCreditsButton;
    @FXML
    private Button sideMenuFaqButton;
    @FXML
    private Button sideMenuLoansButton;
    @FXML
    private Button sideMenuStartButton; //keep
    @FXML
    private Button sideMenuSupportButton;
    @FXML
    private Button sideMenuTransferFundsButton;
    @FXML
    private Label headerActualIdLabel;
    @FXML
    private Label headerCustomerNameLabel;
    @FXML
    private Label startActualTotalBalanceLabel;
    @FXML
    private Label startActualTotalDebtLabel;
    @FXML
    private Label infoNameLabel;
    @FXML
    private TableView<Account> assetsTableView;
    @FXML
    private TableColumn<Account, String> assetsNameTableColumn;
    @FXML
    private TableColumn<Account, Double> assetsBalanceTableColumn;
	@FXML
    private TableView<Account> debtsTableView;
    @FXML
    private TableColumn<Account, String> debtsNameTableColumn;
    @FXML
    private TableColumn<Account, Double> debtsBalanceTableColumn;

    private FXMLLoader loader;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public static Bank bank = UIMain.bank;
    public static Customer currentCustomer;
    public static Account currentAccount;
    public static HashMap<String, Account> currentCustomersAccounts;

	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCurrentCustomer();
        showStartAccountOverviews();
    }

    public void showCurrentCustomer(){
        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            headerCustomerNameLabel.setText(privateCustomer.getFullName());
            infoNameLabel.setText(privateCustomer.getFullName());
            headerActualIdLabel.setText(privateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            headerCustomerNameLabel.setText(corporateCustomer.getCompanyName());
            infoNameLabel.setText(corporateCustomer.getCompanyName());
            headerActualIdLabel.setText(corporateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }

	public void showStartAccountOverviews() {
		// set table columns and placeholder
		assetsNameTableColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountName"));
		assetsBalanceTableColumn.setCellValueFactory(new PropertyValueFactory<Account, Double>("balance"));
		assetsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		assetsTableView.setPlaceholder(new Label("No assets"));
		assetsTableView.setSelectionModel(null);

		debtsNameTableColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountName"));
		debtsBalanceTableColumn.setCellValueFactory(new PropertyValueFactory<Account, Double>("balance"));
		debtsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		debtsTableView.setPlaceholder(new Label("No debts"));
		debtsTableView.setSelectionModel(null);

		// fill the tables with data
		ObservableList<Account> assetsOL = FXCollections.observableArrayList();
		ObservableList<Account> debtsOL = FXCollections.observableArrayList();

		for(Account account : currentCustomersAccounts.values()){
			if(account instanceof Credit || account instanceof Loan){
				debtsOL.add(account);
			}else{
				assetsOL.add(account);
			}
		}

		assetsTableView.setItems(assetsOL);
		debtsTableView.setItems(debtsOL);

		// set total account balances (label below the tables)
		double totalAssetsBalance = 0;
		double totalDebtsBalance = 0;
		for(Account account : currentCustomersAccounts.values()){
			if(account instanceof Credit || account instanceof Loan){
				totalDebtsBalance = totalDebtsBalance + account.getBalance();
			}else{
				totalAssetsBalance = totalAssetsBalance + account.getBalance();
			}
		}

		startActualTotalBalanceLabel.setText(String.format("%.2f SEK", totalAssetsBalance));
		startActualTotalDebtLabel.setText(String.format("%.2f SEK", totalDebtsBalance));
	}

    //-----------------SIDE MENU NAVIGATION----------------
    public void goToStart(ActionEvent event) throws IOException { //sideMenuStartButton
        loader = new FXMLLoader(getClass().getResource("CustomerStart.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToAccountsOverview(ActionEvent event) throws IOException { //sideMenuAccountsOverviewButton
        loader = new FXMLLoader(getClass().getResource("CustomerAccountsOverview.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToTransferFunds(ActionEvent event) throws IOException { //sideMenuTransferFundsButton
        loader = new FXMLLoader(getClass().getResource("CustomerTransferFunds.fxml"));
        root = loader.load();

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
        FileHandler.jsonSerializer(UIMain.savePath, bank);

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
