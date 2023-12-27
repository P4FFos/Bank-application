package com.piggybank.app.ui;

import com.piggybank.app.backend.Bank;
import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.employees.Employee;
import com.piggybank.app.backend.utils.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class EmpMainController implements Initializable {
    @FXML
    private Button logoutButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button empStartButton;
    @FXML
    private Button viewCustomerButton;
    @FXML
    private Button customerInfoButton;
    @FXML
    private Button manageLoansButton;
    @FXML
    private Button newCustomerButton;
    @FXML
    private Label empIdLabel;
    @FXML
    private Label empInitialsLabel;
    @FXML
    private TextField searchCustomerTextField;
    @FXML
    private Label NameLabel;
    @FXML
    private Label infoActualUserIdLabel;

    private Parent root;
    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;

    public static Bank bank = UIMain.bank;
    public static Employee currentEmployee;
    public static Customer currentCustomer;
    public static Account currentAccount;
    public static HashMap<String, Account> currentCustomersAccounts;
    public String saveFile = UIMain.savePath;

    public void initialize(URL arg0, ResourceBundle arg1) {
        showCurrentEmployee();
        System.out.println("Employee Start Page. Logged in as: " + currentEmployee.getInitials());
        NameLabel.setText(currentEmployee.getFullName());
        infoActualUserIdLabel.setText(currentEmployee.getUserId());
        System.out.println("Employee Start Page. Logged in as: " + currentEmployee.getInitials());
    }

    public void showCurrentEmployee(){
        empIdLabel.setText(currentEmployee.getUserId());
        empInitialsLabel.setText(currentEmployee.getInitials());
    }

    public void logout(ActionEvent event) throws IOException { //logoutButton
        FileHandler.jsonSerializer(saveFile, bank);

        currentEmployee = null;
        currentCustomer = null;

        loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.println("Logged out. Have a nice day.");
    }

    public void goToEmpStart(ActionEvent event) throws IOException { //empStartButton
        currentCustomer = null;
        currentCustomersAccounts = null;

        loader = new FXMLLoader(getClass().getResource("EmpStart.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchCustomer(ActionEvent event) throws IOException { //searchButton
        String searchPhrase = searchCustomerTextField.getText();
        try{
            currentCustomer = bank.getCustomerByIdOrSsn(searchPhrase);
            currentCustomersAccounts = currentCustomer.getAccounts();

            loader = new FXMLLoader(getClass().getResource("EmpCustomerOverView.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToCustomerAccounts(ActionEvent event) throws IOException { //viewCustomerButton
        if(currentCustomer != null) {
            loader = new FXMLLoader(getClass().getResource("EmpCustomerOverview.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("No selected customer. Do a search.");
        }

    }

    public void goToCustomerInfo(ActionEvent event) throws IOException { //customerInfoButton
        if(currentCustomer != null){
            loader = new FXMLLoader(getClass().getResource("EmpCustomerInfo.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("No selected customer. Do a search.");
        }
    }

    public void goToLoans(ActionEvent event) throws IOException { //manageLoansButton
        if(currentCustomer != null){
            loader = new FXMLLoader(getClass().getResource("EmpManageLoans.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("No selected customer. Do a search.");
        }

    }

    public void goToAddCustomer(ActionEvent event) throws IOException { //newCustomerButton
        currentCustomer = null;
        currentCustomersAccounts = null;

        loader = new FXMLLoader(getClass().getResource("EmpAddCustomer.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
