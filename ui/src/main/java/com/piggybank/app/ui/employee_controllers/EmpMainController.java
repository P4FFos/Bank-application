package com.piggybank.app.ui.employee_controllers;

import com.piggybank.app.backend.Bank;
import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.employees.Employee;
import com.piggybank.app.backend.exceptions.UserNotFoundException;
import com.piggybank.app.backend.utils.FileHandler;
import com.piggybank.app.ui.UIMain;
import com.piggybank.app.ui.employee_controllers.customer_operation.EmpAddAccountController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
    private Label empIdLabel;
    @FXML
    private Label empInitialsLabel;
    @FXML
    private Label companyNameLabel;
    @FXML
    private TextField searchCustomerTextField;
    @FXML
    private Label noSelectedCustomerLabel;
    @FXML
    private Label noCustomerFoundLabel;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;

    public static Bank bank = UIMain.bank; //refers to the bank object created in UIMain
    public static Employee currentEmployee; //refers to the employee account that is currently logged in
    public static Customer currentCustomer; //refers to the current customer that the employee is operating on if there is one
    public static Account currentAccount; //refers to the current account that the employee is operating on if there is one
    public static HashMap<String, Account> currentCustomersAccounts;
    public String saveFile = UIMain.savePath;

    public void initialize(URL arg0, ResourceBundle arg1) { //setup for scene: EmpStart
        showCurrentEmployee();
        companyNameLabel.setText("No active customer.");
    }

    public void showCurrentEmployee(){ //displays the current employee user id and initials in the upper right corner
        empIdLabel.setText(currentEmployee.getUserId());
        empInitialsLabel.setText(currentEmployee.getInitials());
    }

    //--------------------------------MAIN NAVIGATION--------------------------------
    //................Inherited by subclasses of EmpMainController...................
    public void logout(ActionEvent event) throws IOException { //logoutButton, switches to scene: StartScene
        FileHandler.jsonSerializer(saveFile, bank);

        currentEmployee = null;
        currentCustomer = null;

        loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/StartScene.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToEmpStart(ActionEvent event) throws IOException { //empStartButton, switches to scene: EmpStart
        currentCustomer = null;
        currentCustomersAccounts = null;

        loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/employee_scenes/EmpStart.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchCustomer(ActionEvent event) throws IOException { //searchButton, on finding a customer switches to scene: EmpCustomerOverview
        noSelectedCustomerLabel.setVisible(false);
        String searchPhrase = searchCustomerTextField.getText();
        try {
            currentCustomer = bank.getCustomerByIdOrSsn(searchPhrase);
            currentCustomersAccounts = currentCustomer.getAccounts();

            loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/employee_scenes/customer_operation/EmpCustomerOverView.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(UserNotFoundException e) {
            if(noCustomerFoundLabel != null){
                noCustomerFoundLabel.setVisible(true); //displays an error message if no customer was found
            }
        } catch (Exception e) {
            e.printStackTrace(); //mostly for the sake of easier debugging
        }
    }

    public void goToCustomerAccounts(ActionEvent event) throws IOException { //viewCustomerButton, if there is a current customer -> switches to scene: EmpCustomerOverview
        if(currentCustomer != null) {
            loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/employee_scenes/customer_operation/EmpCustomerOverview.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            if (noCustomerFoundLabel != null) {
                noCustomerFoundLabel.setVisible(false);
            }
            if (noSelectedCustomerLabel != null) {
                noSelectedCustomerLabel.setVisible(true); //displays an error message if there is no current customer
            }
        }
    }

    public void goToCustomerInfo(ActionEvent event) throws IOException { //customerInfoButton, if there is a current customer -> switches to scene: EmpCustomerInfo
        if(currentCustomer != null){
            loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/employee_scenes/customer_operation/EmpCustomerInfo.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            if (noCustomerFoundLabel != null) {
                noCustomerFoundLabel.setVisible(false);
            }
            if (noSelectedCustomerLabel != null) {
                noSelectedCustomerLabel.setVisible(true); //displays an error message if there is no current customer
            }
        }
    }

    public void goToLoans(ActionEvent event) throws IOException { //manageLoansButton, if there is a current customer -> switches to scene: EmpAddAccount
        //Leftover after reducing our scope. Directed to EmpAddAccount instead of a page for handling loan applications as we had planned.
        if(currentCustomer != null){
            loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/employee_scenes/customer_operation/EmpAddAccount.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            if (noCustomerFoundLabel != null) {
                noCustomerFoundLabel.setVisible(false);
            }
            if (noSelectedCustomerLabel != null) {
                noSelectedCustomerLabel.setVisible(true); //displays an error message if there is no current customer
            }
        }
    }

    public void goToAddCustomer(ActionEvent event) throws IOException { //newCustomerButton, switches to scene: EmpAddCustomer
        loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/employee_scenes/customer_operation/EmpAddCustomer.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
