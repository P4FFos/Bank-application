package com.piggybank.app.ui;

import com.piggybank.app.backend.Bank;
import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.employees.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class EmpMainController {
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

    private Parent root;
    private Stage stage;
    private Scene scene;
    private Node node;
    private FXMLLoader loader;
    private Customer currentCustomer;

    public static Employee currentEmployee;
    public static Bank bank = UIMain.getBank();


    public Customer getCurrentCustomer(){
        return currentCustomer;
    }
    public void setCurrentEmployee(Employee employee){
        currentEmployee = employee;
        empIdLabel.setText(currentEmployee.getUserId());
        empInitialsLabel.setText(currentEmployee.getInitials());
        System.out.println("Employee Start Page. Logged in as: " + employee.getInitials());
    }

    public void logout(ActionEvent event) throws IOException { //logoutButton
        currentEmployee = null;
        System.out.println("Logged out. Have a nice day.");
        loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToEmpStart(ActionEvent event) throws IOException { //empStartButton
        loader = new FXMLLoader(getClass().getResource("EmpStart.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        empIdLabel.setText(currentEmployee.getUserId());
        empInitialsLabel.setText(currentEmployee.getInitials());
        System.out.println("Employee Start Page. Logged in as: " + currentEmployee.getInitials());
    } //Not displaying Emp Info Labels correctly

    public void searchCustomer(ActionEvent event) throws IOException { //searchButton
        String searchPhrase = searchCustomerTextField.getText();
        try{
            currentCustomer = bank.getCustomerByIdOrSSN(searchPhrase);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpCustomerOverview.fxml"));
            root = loader.load();

            EmpCustomerOverviewController controller = loader.getController();
            controller.setCurrentCustomer(currentCustomer);
            controller.setCurrentEmployee();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Customer not found by ID or SSN.");
        }


        /*if(bank.getCustomers().containsKey(searchCustomerTextField.getText())){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpCustomerOverview.fxml"));
            root = loader.load();

            EmpCustomerOverviewController controller = loader.getController();
            controller.setCurrentCustomer(bank.getCustomer());
            controller.setCurrentEmployee();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            System.out.println("Customer not found.");
        }*/
    }

    public void goToCustomer(ActionEvent event) throws IOException { //viewCustomerButton
        loader = new FXMLLoader(getClass().getResource("EmpCustomerOverview.fxml"));
        root = loader.load();

        EmpCustomerOverviewController controller = loader.getController();
        controller.setCurrentCustomer(currentCustomer);
        controller.setCurrentEmployee();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToCustomerInfo(ActionEvent event) throws IOException { //customerInfoButton
        loader = new FXMLLoader(getClass().getResource("EmpCustomerInfo.fxml"));
        root = loader.load();

        EmpCustomerInfoController controller = loader.getController();
        controller.setCurrentEmployee();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToLoans(ActionEvent event) throws IOException { //manageLoansButton
        loader = new FXMLLoader(getClass().getResource("ManageLoans.fxml"));
        root = loader.load();

        ManageLoansController controller = loader.getController();
        controller.setCurrentEmployee();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToCustomerCreation(ActionEvent event) throws IOException { //newCustomerButton
        loader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
        root = loader.load();

        AddCustomerController controller = loader.getController();
        controller.initialiseAddCustomer();
        controller.setCurrentEmployee();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }








}
