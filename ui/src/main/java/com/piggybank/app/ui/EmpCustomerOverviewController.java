package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.customers.Transaction;
import com.piggybank.app.backend.customers.debts.Credit;
import com.piggybank.app.backend.customers.loans.Loan;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmpCustomerOverviewController extends EmpMainController implements Initializable {
    @FXML
    private AnchorPane privateCustomerInfoAnchorPane;
    @FXML
    private AnchorPane corporateCustomerInfoAnchorPane;
    @FXML
    private Label empIdLabel;
    @FXML
    private Label empInitialsLabel;
    @FXML
    private Label customerIdLabel;
    @FXML
    private Label customerSSNLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label companyNameLabel;
    @FXML
    private Label companyIdLabel;
    @FXML
    private Label companyOrgNrLabel;
    @FXML
    private Label accountNameLabel;
    @FXML
    private Label accountBalanceLabel;
    @FXML
    private Label balanceDebtLabel;
    @FXML
    private Label interestLabel;
    @FXML
    private Label interestRateLabel;
    @FXML
    private Label initialLabel;
    @FXML
    private Label initialAmountLabel;
    @FXML
    private Label accountTypeLabel;
    @FXML
    private Button addAccountButton;
    @FXML
    private Button makeTransactionButton;
    @FXML
    private Button manageFundsButton;
    @FXML
    private CheckBox inTransactionCheckBox;
    @FXML
    private CheckBox outTransactionCheckBox;
    @FXML
    private ListView<String> accountsListView;
    @FXML
    private TableView<Transaction> transactionsTable;
    @FXML
    private TableColumn<Transaction, String> senderColumn;
    @FXML
    private TableColumn<Transaction, String> receiverColumn;
    @FXML
    private TableColumn<Transaction, Double> amountColumn;
    @FXML
    private TableColumn<Transaction, String> messageColumn;
    @FXML
    private TableColumn<Transaction, LocalDate> dateColumn;

    private Parent root;
    private Stage stage;
    private FXMLLoader loader;


    //-----------------ACCOUNTS MANAGEMENT----------------------------

    public void manageFunds(ActionEvent event) throws IOException {
        if(currentAccount == null){
            System.out.println("You must select an account.");
        } else {
            loader = new FXMLLoader(getClass().getResource("EmpManageFunds.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void addAccount(ActionEvent event) throws IOException { //addAccountButton
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpAddAccount.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//----------------MANAGE TRANSACTIONS--------------------------

    public void makeTransaction(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("EmpMakeTransaction.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

	public void toggleIncomingTransactions(){ // filters transactions to only show incoming
		if(inTransactionCheckBox.isSelected()){
			outTransactionCheckBox.setSelected(false);
			ObservableList<Transaction> incomingTransactions = FXCollections.observableArrayList();
			for(Transaction transaction : currentAccount.getTransactions()){
				if(transaction.getAmount() > 0){
					incomingTransactions.add(transaction);
				}
			}
			transactionsTable.setItems(incomingTransactions);
		}
		else{
			transactionsTable.setItems(currentAccount.getTransactions());
		}
	}

	public void toggleOutgoingTransactions(){ // filters transactions to only show outgoing
		if(outTransactionCheckBox.isSelected()){
			inTransactionCheckBox.setSelected(false);
			ObservableList<Transaction> outgoingTransactions = FXCollections.observableArrayList();
			for(Transaction transaction : currentAccount.getTransactions()){
				if(transaction.getAmount() < 0){
					outgoingTransactions.add(transaction);
				}
			}
			transactionsTable.setItems(outgoingTransactions);
		}
		else{
			transactionsTable.setItems(currentAccount.getTransactions());
		}
	}

//----------------SETUP SCENE-------------------------------------

    public void initialize(URL arg0, ResourceBundle arg1) {
        accountsListView.getItems().addAll(currentCustomersAccounts.keySet());
        interestLabel.setVisible(false);
        interestRateLabel.setVisible(false);
        initialLabel.setVisible(false);
        initialAmountLabel.setVisible(false);

        accountsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                String currentAccountId = accountsListView.getSelectionModel().getSelectedItem();
                currentAccount = currentCustomersAccounts.get(currentAccountId);
                accountNameLabel.setText(currentAccount.getAccountName());
                accountBalanceLabel.setText(currentAccount.getBalance() + " SEK");

                if(currentAccount instanceof Credit){
                    Credit currentCredit = (Credit) currentAccount;
                    accountTypeLabel.setText("CREDIT:");
                    balanceDebtLabel.setText("DEBT:");
                    interestLabel.setVisible(true);
                    interestRateLabel.setVisible(true);
                    interestRateLabel.setText(Double.toString(currentCredit.getInterestRate()));
                } else if(currentAccount instanceof Loan){
                    Loan currentLoan = (Loan) currentAccount;
                    accountTypeLabel.setText("LOAN:");
                    balanceDebtLabel.setText("DEBT");
                    interestLabel.setVisible(true);
                    interestRateLabel.setVisible(true);
                    interestRateLabel.setText(Double.toString(currentLoan.getInterestRate()));
                    initialLabel.setVisible(true);
                    initialAmountLabel.setVisible(true);
                    initialAmountLabel.setText(Double.toString(currentLoan.getInitialAmount()));
                } else {
                    accountTypeLabel.setText("ACCOUNT:");
                    balanceDebtLabel.setText("BALANCE:");
                    interestLabel.setVisible(false);
                    interestRateLabel.setVisible(false);
                    initialLabel.setVisible(false);
                    initialAmountLabel.setVisible(false);
                }

                senderColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("senderAccountId"));
                receiverColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("receiverAccountId"));
                amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amount"));
                messageColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("message"));
                dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("date"));

                transactionsTable.setItems(currentAccount.getTransactions());
            }
        });
    }

    public void goToEmpStart(ActionEvent event) throws IOException { //empStartButton
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpStart.fxml"));
        root = loader.load();

        EmpMainController controller = loader.getController();
        controller.showCurrentEmployee();
        EmpMainController.currentCustomer = null;

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showCurrentEmployee() {
        empIdLabel.setText(currentEmployee.getUserId());
        empInitialsLabel.setText(EmpMainController.currentEmployee.getInitials());
        System.out.println("Customer Overview (Accounts) Page. Logged in as: " + EmpMainController.currentEmployee.getInitials());
    }

    public void showCurrentCustomer() {
        if (EmpMainController.currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate currentPrivate = (CustomerPrivate) EmpMainController.currentCustomer;
            privateCustomerInfoAnchorPane.setVisible(true);
            corporateCustomerInfoAnchorPane.setVisible(false);
            customerSSNLabel.setText(currentPrivate.getSsn());
            customerNameLabel.setText(currentPrivate.getFullName());
            customerIdLabel.setText(currentPrivate.getUserId());
        } else {
            CustomerCorporate currentCorporate = (CustomerCorporate) EmpMainController.currentCustomer;
            privateCustomerInfoAnchorPane.setVisible(false);
            corporateCustomerInfoAnchorPane.setVisible(true);
            companyNameLabel.setText(currentCorporate.getCompanyName());
            companyIdLabel.setText(currentCorporate.getUserId());
            companyOrgNrLabel.setText(currentCorporate.getOrgNumber());
        }
    }

}
