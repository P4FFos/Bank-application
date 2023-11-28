package com.piggybank.app.ui;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class EmployeeMainController implements Initializable {
    @FXML
    private Button employeeStartButton;
    @FXML
    private Button customerCardButton;
    @FXML
    private Button searchCustomer;
    @FXML
    private Button logoutButton;
    @FXML
    private Label searchMsg;
    @FXML
    private Label customerIdLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label userIdLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private TextField customerSearchTextField;
    @FXML
    private AnchorPane employeeStart;
    @FXML
    private AnchorPane customerCard;
    @FXML
    private ListView<String> accountsListView; //will be list of accounts

    private Parent root;
    private Stage stage;
    private Scene scene;

    private Map<String, String> customers = new HashMap<>();
    private String customerID;
    private String customerName;

    String[] accounts = {"paycheck", "savings", "travel", "emergency"};
    String currentAccount;


    public void initialize(URL arg0, ResourceBundle arg1){

        accountsListView.getItems().addAll(accounts);
        accountsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2){
                currentAccount = accountsListView.getSelectionModel().getSelectedItem();
                System.out.println(currentAccount);
            }
        });
    }

    public void displayUser(String id, String name){
        userIdLabel.setText(id);
        userNameLabel.setText(name);
    }

    public void showEmployeeStart(){
        employeeStart.setVisible(true);
        customerCard.setVisible(false);

        //Placeholder below. Will search through backend hashmap of customers when ready.
        fillCustomers();
    }

    public void fillCustomers(){
        customers.put("010101-1234", "Anna Andersson");
        customers.put("020202-2345", "Babben Borg");
        customers.put("030303-3456", "Charles Choco");
        customers.put("040404-4567", "David Dancer");
        customers.put("050505-5678", "Eve Ericson");
    }

    public void searchCustomer(ActionEvent event){
        //Placeholder logic.
        if(customers.containsKey(customerSearchTextField.getText())){
            customerID = customerSearchTextField.getText();
            customerName = customers.get(customerID);
            showCustomerCard(customerID, customerName);
            searchMsg.setText("");
        } else {
            searchMsg.setText("Not found.");
        }
    }
    public void showEmployeeStart(ActionEvent event){
        employeeStart.setVisible(true);
        customerCard.setVisible(false);
    }

    public void showCustomerCard(ActionEvent event){
        customerCard.setVisible(true);
        employeeStart.setVisible(false);
    }

    public void showCustomerCard(String id, String name){
        customerIdLabel.setText(id);
        customerNameLabel.setText(name);
        customerCard.setVisible(true);
        employeeStart.setVisible(false);
    }

    public void logout(ActionEvent event) throws IOException {
        //Later: add alert with options to save before logging out, cancelling logout and logging out without saving
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
