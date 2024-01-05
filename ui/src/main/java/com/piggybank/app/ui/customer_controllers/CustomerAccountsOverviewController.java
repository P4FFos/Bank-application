package com.piggybank.app.ui.customer_controllers;

import com.piggybank.app.backend.customers.*;
import com.piggybank.app.backend.customers.money_operations.Transaction;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class CustomerAccountsOverviewController extends CustomerStartController implements Initializable {
    @FXML
    private CheckBox accountsAllCheckBox;
    @FXML
    private CheckBox accountsIncomingCheckBox;
    @FXML
    private CheckBox accountsOutgoingCheckBox;
    @FXML
    private TableView<Transaction> transactionsTable;
    @FXML
    private TableColumn<Transaction, String> senderColumn;
    @FXML
    private TableColumn<Transaction, String> receiverColumn;
    @FXML
    private TableColumn<Transaction, String> amountColumn;
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
    private TableColumn<Account, String> accountBalanceColumn;

    public void initialize(URL arg0, ResourceBundle arg1) { //Populates accountsListView with elements in accounts, selection "gets" an account
        showCurrentCustomer();
        initializeTables();
    }

    public void initializeTables(){
        // Setting up columns in the accountsTableView to be populated
        accountNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountName"));
        accountIdColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountId"));
        accountBalanceColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("balanceString"));

        accountsTableView.setItems(currentCustomer.getAccountsList());

        // sort tableView on balance with descending values
        accountIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        accountsTableView.getSortOrder().add(accountIdColumn);

        // checkbox for all accounts should be automatically checked
        accountsAllCheckBox.setSelected(true);

        /* gets the selected row (account) in the TableView of accounts. Based on what is selected
         * updates the transactions TableView */
        accountsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Account>() {
            // when account is changed it will update the transactionsTable
            @Override
            public void changed(ObservableValue<? extends Account> observableValue, Account account, Account t1) {
                currentAccount = accountsTableView.getSelectionModel().selectedItemProperty().getValue();

                senderColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("senderAccountId"));
                receiverColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("receiverAccountId"));
                amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amountString"));
                messageColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("message"));
                dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("date"));

                transactionsTable.setItems(currentAccount.getTransactions());

                sortTransactionsTable();
                // Stop from scrolling horizontally in TableView
                transactionsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
            }
        });
    }

    public void sortTransactionsTable() {
        // Sort dates from lowest to highest
        dateColumn.setSortType(TableColumn.SortType.DESCENDING);
        transactionsTable.getSortOrder().add(dateColumn);
    }
    //..........................TOGGLES.............................

    // filter transactions to show all transactions, not possible to deselect it
    public void toggleAllTransactions() {
        if(accountsAllCheckBox.isSelected()) {
            accountsAllCheckBox.setSelected(true);
            accountsIncomingCheckBox.setSelected(false);
            accountsOutgoingCheckBox.setSelected(false);
            // if you try to use a toggle without selecting an account first
            if(currentAccount != null) {
                transactionsTable.setItems(currentAccount.getTransactions());
                sortTransactionsTable();
            }
        } else {
            accountsAllCheckBox.setSelected(true); // always true when clicking the toggle
            // if you try to use a toggle without selecting an account first
            if(currentAccount != null) {
                transactionsTable.setItems(currentAccount.getTransactions());
                sortTransactionsTable();
            }
        }
    }

    // filter transactions to only show incoming (amount > 0)
    public void toggleIncomingTransactions() {
        if(accountsIncomingCheckBox.isSelected()) {
            accountsIncomingCheckBox.setSelected(true);
            accountsAllCheckBox.setSelected(false);
            accountsOutgoingCheckBox.setSelected(false);
            // if you try to use a toggle without selecting an account first, then show only incoming transaction with aid of for loop
            if(currentAccount != null) {
                ObservableList<Transaction> incomingTransactions = FXCollections.observableArrayList();
                for (Transaction transaction : currentAccount.getTransactions()) {
                    if (transaction.getAmount() > 0) {
                        incomingTransactions.add(transaction);
                    }
                }
                transactionsTable.setItems(incomingTransactions);
                sortTransactionsTable();
            }
        } else {
            accountsAllCheckBox.setSelected(true);
            // if you try to use a toggle without selecting an account first, then show only outgoing transaction with aid of for loop
            if(currentAccount != null) {
                transactionsTable.setItems(currentAccount.getTransactions());
                sortTransactionsTable();
            }
        }
    }

    // filter transactions to only show outgoing (amount < 0)
    public void toggleOutgoingTransactions() {
        if(accountsOutgoingCheckBox.isSelected()) {
            accountsOutgoingCheckBox.setSelected(true);
            accountsAllCheckBox.setSelected(false);
            accountsIncomingCheckBox.setSelected(false);
            // if you try to use a toggle without selecting an account first, then show only outgoing transaction with aid of for loop
            if(currentAccount != null) {
                ObservableList<Transaction> outgoingTransactions = FXCollections.observableArrayList();
                for (Transaction transaction : currentAccount.getTransactions()) {
                    if (transaction.getAmount() < 0) {
                        outgoingTransactions.add(transaction);
                    }
                }
                transactionsTable.setItems(outgoingTransactions);
                sortTransactionsTable();
            }
        } else {
            // if you try to use a toggle without selecting an account first
            if(currentAccount != null) {
                transactionsTable.setItems(currentAccount.getTransactions());
                sortTransactionsTable();
            }
            accountsAllCheckBox.setSelected(true);
        }
    }
}
