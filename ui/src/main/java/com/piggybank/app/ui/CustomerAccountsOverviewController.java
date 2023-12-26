package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.*;
import com.piggybank.app.backend.customers.debts.Credit;
import com.piggybank.app.backend.customers.loans.Loan;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;

public class CustomerAccountsOverviewController extends CustomerStartController implements Initializable {

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
    private ListView<String> accountsListView;

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
    private Label infoActualUserIdLabel;

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

    @FXML
    private TableView<Account> accountsTableView;
    @FXML
    private TableColumn<Account, String> accountIdColumn;
    @FXML
    private TableColumn<Account, String> accountNameColumn;
    @FXML
    private TableColumn<Account, Double> accountBalanceColumn;

    private Account currentAccount;

    public void setCurrentCustomer(Customer customer){ //Method called from CustomerLoginController
        currentCustomer = customer;

        if (customer instanceof CustomerPrivate) {
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

    public void showCurrentCustomer(){

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

    public void initialize(URL arg0, ResourceBundle arg1) { //Populates accountsListView with elements in accounts, selection "gets" an account
        accountNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountName"));
        accountIdColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountId"));
        accountBalanceColumn.setCellValueFactory(new PropertyValueFactory<Account, Double>("balance"));

        accountsTableView.setItems(currentCustomer.getAccountsList());

        // sort tableView on balance with descending values
        accountBalanceColumn.setSortType(TableColumn.SortType.DESCENDING);
        accountsTableView.getSortOrder().add(accountBalanceColumn);

        // checkbox for all accounts should be automatically checked
        accountsAllCheckBox.setSelected(true);

        /* gets the selected row (account) in the TableView of accounts. Based on what is selected
        * updates the transactions TableView */
        accountsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Account>() {
            @Override
            public void changed(ObservableValue<? extends Account> observableValue, Account account, Account t1) {
                currentAccount = accountsTableView.getSelectionModel().selectedItemProperty().getValue();

                senderColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("senderAccountId"));
                receiverColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("receiverAccountId"));
                amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amount"));
                messageColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("message"));
                dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("date"));

                transactionsTable.setItems(currentAccount.getTransactions());
            }
        });
    }

    // filter transactions to show all transactions
    public void toggleAllTransactions() {
        if(accountsAllCheckBox.isSelected()) {
            accountsAllCheckBox.setSelected(true);
            accountsIncomingCheckBox.setSelected(false);
            accountsOutgoingCheckBox.setSelected(false);

            transactionsTable.setItems(currentAccount.getTransactions());
        } else {
            transactionsTable.setItems(currentAccount.getTransactions());
        }
    }

    // filter transactions to only show incoming (amount > 0)
    public void toggleIncomingTransactions() {
        if(accountsIncomingCheckBox.isSelected()) {
            accountsIncomingCheckBox.setSelected(true);
            accountsAllCheckBox.setSelected(false);
            accountsOutgoingCheckBox.setSelected(false);

            ObservableList<Transaction> incomingTransactions = FXCollections.observableArrayList();
            for(Transaction transaction : currentAccount.getTransactions()) {
                if (transaction.getAmount() > 0) {
                    incomingTransactions.add(transaction);
                }
            }
            transactionsTable.setItems(incomingTransactions);
        } else {
            transactionsTable.setItems(currentAccount.getTransactions());
        }
    }

    // filter transactions to only show outgoing (amount < 0)
    public void toggleOutgoingTransactions() {
        if(accountsOutgoingCheckBox.isSelected()) {
            accountsOutgoingCheckBox.setSelected(true);
            accountsAllCheckBox.setSelected(false);
            accountsIncomingCheckBox.setSelected(false);

            ObservableList<Transaction> outgoingTransactions = FXCollections.observableArrayList();
            for(Transaction transaction : currentAccount.getTransactions()) {
                if (transaction.getAmount() < 0) {
                    outgoingTransactions.add(transaction);
                }
            }
            transactionsTable.setItems(outgoingTransactions);
        } else {
            transactionsTable.setItems(currentAccount.getTransactions());
        }
    }
}
