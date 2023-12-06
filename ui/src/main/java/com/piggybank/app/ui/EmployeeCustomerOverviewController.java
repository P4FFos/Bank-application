package com.piggybank.app.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeCustomerOverviewController extends EmployeeMainController implements Initializable {
    @FXML
    private AnchorPane customerCard;
    @FXML
    private Label customerIdLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label accountIdLabel;
    @FXML
    private Label accountBalanceLabel;
    @FXML
    private Button selectAccountButton;
    @FXML
    private ListView<String> accountsListView; //will be list of current customer's accounts
    @FXML
    private ListView<String> accountHistoryListView; //will be list of the current account's transactions

    private String[] accounts = {"main_account", "savings", "emergency", "travel"};
    private String currentAccount = "";

    public void initialize(URL arg0, ResourceBundle arg1){
        accountsListView.getItems().addAll(accounts);
        accountsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2){
                currentAccount = accountsListView.getSelectionModel().getSelectedItem();
                System.out.println("Current account: " + currentAccount);
            }
        });
    } //Initializes accountsListView with elements in accounts, selection sets currentAccount
    public void showAccount(){
        accountIdLabel.setText("0123");
        accountBalanceLabel.setText("25530.12");
        accountHistoryListView.getItems().add(currentAccount);
    } //Just a test to fill the ListView that will show transaction history from selecting an account in accountsListView

}
