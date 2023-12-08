package com.piggybank.app.ui;

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
    private Label empNameLabel;
    @FXML
    private TextField searchCustomerTextField;

    private Parent root;
    private Stage stage;
    private Scene scene;
    private Node node;
    private FXMLLoader loader;

    public static Map<String, String[]> customers = new HashMap<>();
    public static String customerID;
    public static String customerName;
    public static String customerSSN;

    public void fillcustomers() {
        customers.put("010101-1234", new String[]{"Anna Andersson", "0123"});
        customers.put("020202-2345", new String[]{"Babben Borg", "0456"});
    }

    public void logout(ActionEvent event) throws IOException { //logoutButton
        //Later: add alert with options to save before logging out, cancelling logout and logging out without saving
        loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchCustomer(ActionEvent event) throws IOException { //searchButton
        if(customers.containsKey(searchCustomerTextField.getText())){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpCustomerOverview.fxml"));
            root = loader.load();

            //Placeholder logic. Connect appropriately with backend when ready.
            customerID = searchCustomerTextField.getText();
            customerName = customers.get(customerID)[0];
            customerSSN = customers.get(customerID)[1];

            EmpCustomerOverviewController controller = loader.getController();
            controller.displayCurrentCustomer(customerID, customerName, customerSSN);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            System.out.println("Customer not found.");
        }
    }

    public void goToEmpStart(ActionEvent event) throws IOException { //empStartButton
        loader = new FXMLLoader(getClass().getResource("EmpStart.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToCustomer(ActionEvent event) throws IOException { //viewCustomerButton
        loader = new FXMLLoader(getClass().getResource("EmpCustomerOverview.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToCustomerInfo(ActionEvent event) throws IOException { //customerInfoButton
        loader = new FXMLLoader(getClass().getResource("EmpCustomerInfo.fxml"));
        root = loader.load();

        EmpCustomerInfoController controller = loader.getController();
        controller.displayCurrentCustomer(customerID, customerName, customerSSN);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToLoans(ActionEvent event) throws IOException { //manageLoansButton
        loader = new FXMLLoader(getClass().getResource("ManageLoans.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToCustomerCreation(ActionEvent event) throws IOException { //newCustomerButton
        loader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initializeEmpoyeeSection(String id, String name){
        empIdLabel.setText(id);
        empNameLabel.setText(name);
    }






}
