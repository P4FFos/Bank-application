package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class TransactionController {

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
    private TableColumn<Transaction, String> dateColumn;
    private ObservableList<Transaction> transactions;

    public TransactionController(ArrayList<Transaction> transactions) {
        this.transactions = FXCollections.observableArrayList(transactions);
        transactionsTable = new TableView<>();
        senderColumn = new TableColumn<>();
        receiverColumn = new TableColumn<>();
        amountColumn = new TableColumn<>();
        messageColumn = new TableColumn<>();
        dateColumn = new TableColumn<>();
    }
    @FXML
    public void initialize() {
        senderColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("senderAccountId"));
        receiverColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("receiverAccountId"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amount"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("message"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));

        transactionsTable.setItems(transactions);
    }

}
