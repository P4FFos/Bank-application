package com.piggybank.app.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EmpMakeTransactionController extends EmpMainController implements Initializable{
    @FXML
    private ListView<String> accountsListView;
    @FXML
    private Button selectSendingAccountButton;
    @FXML
    private Button okButton;
    @FXML
    private TextField recieverAccountTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private CheckBox oneHundredCheckBox;
    @FXML
    private CheckBox twoHundredCheckBox;
    @FXML
    private CheckBox fiveHundredCheckBox;
    @FXML
    private CheckBox oneThousandCheckBox;

    public void initialize(URL arg0, ResourceBundle arg1) {
        //Populate accountsListView with accountIDs from currentCustomersAccounts
    }

    public void selectSendingAccount(){

    }
    public void toggleOneHundred(){

    }
    public void toggleTwoHundred(){

    }
    public void toggleFiveHundred(){

    }
    public void toggleOneThousand(){

    }
    public void transferFunds(){

    }
}
