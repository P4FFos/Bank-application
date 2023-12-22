package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.exceptions.PasswordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EmpCustomerInfoController extends EmpMainController {
    @FXML
    private AnchorPane privateCustomerInfoAnchorPane;
    @FXML
    private AnchorPane corporateCustomerInfoAnchorPane;
    @FXML
    private Button editStreetButton;
    @FXML
    private Button editZipButton;
    @FXML
    private Button editCityButton;
    @FXML
    private Button editPhoneButton;
    @FXML
    private Button editEmailButton;
    @FXML
    private Button editPasswordButton;
    @FXML
    private Button saveNewStreetButton;
    @FXML
    private Button saveNewZipButton;
    @FXML
    private Button saveNewCityButton;
    @FXML
    private Button saveNewPhoneButton;
    @FXML
    private Button saveNewEmailButton;
    @FXML
    private Button getSaveNewPasswordButton;
    @FXML
    private Label empInitialsLabel;
    @FXML
    private Label empIdLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label customerIdLabel;
    @FXML
    private Label customerSSNLabel;
    @FXML
    private Label companyNameLabel;
    @FXML
    private Label companyIdLabel;
    @FXML
    private Label companyOrgNrLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField zipField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;


    public void streetEditable(){ //editStreetButton
        streetField.setEditable(true);
    }
    public void zipEditable(){ //editZipButton
        zipField.setEditable(true);
    }
    public void cityEditable(){ //editCityButton
        cityField.setEditable(true);
    }
    public void phoneEditable(){ //editPhoneButton
        phoneField.setEditable(true);
    }
    public void emailEditable(){ //editEmailButton
        emailField.setEditable(true);
    }
    public void passwordEditable(){ //editPasswordButton
        passwordField.setEditable(true);
    }
    public void setNewStreet(){ //saveNewStreetButton
        String newStreet = streetField.getText();
        streetField.setText(newStreet);
        EmpMainController.currentCustomer.setStreet(newStreet);
        streetField.setEditable(false);
    }
    public void setNewZip(){ //saveNewZipButton
        String newZip = zipField.getText();
        zipField.setText(newZip);
        EmpMainController.currentCustomer.setZipCode(newZip);
        zipField.setEditable(false);
    }
    public void setNewCity(){ //saveNewCityButton
        String newCity = cityField.getText();
        cityField.setText(newCity);
        EmpMainController.currentCustomer.setCity(newCity);
        cityField.setEditable(false);
    }
    public void setNewPhone(){ //saveNewPhoneButton
        String newPhone = phoneField.getText();
        phoneField.setText(newPhone);
        EmpMainController.currentCustomer.setPhoneNumber(newPhone);
        phoneField.setEditable(false);
    }
    public void setNewEmail() throws Exception { //saveNewEmailButton
        String newEmail = emailField.getText();
        emailField.setText(newEmail);
        EmpMainController.currentCustomer.setEmail(newEmail);
        emailField.setEditable(false);
    }
    public void setNewPassword() { //saveNewPasswordButton
        try {
            String newPassword = passwordField.getText();
            passwordField.setText(newPassword);
            EmpMainController.currentCustomer.setPassword(newPassword);
            passwordField.setEditable(false);
        } catch (PasswordException passwordException) {
            System.out.println("Password must be at least 8 characters and include uppercase letters and numbers.");
        }
    }
    public void goToEmpStart(ActionEvent event) throws IOException { //empStartButton
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpStart.fxml"));
        Parent root = loader.load();

        EmpMainController controller = loader.getController();
        controller.showCurrentEmployee();
        EmpMainController.currentCustomer = null;

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showCurrentEmployee(){
        empIdLabel.setText(EmpMainController.currentEmployee.getUserId());
        empInitialsLabel.setText(EmpMainController.currentEmployee.getInitials());
        System.out.println("Customer Info Page. Logged in as: " + EmpMainController.currentEmployee.getInitials());
    }

    public void showCurrentCustomer(){
        customerIdLabel.setText(EmpMainController.currentCustomer.getUserId());
        streetField.setText(EmpMainController.currentCustomer.getStreet());
        zipField.setText(EmpMainController.currentCustomer.getZipCode());
        cityField.setText(EmpMainController.currentCustomer.getCity());
        phoneField.setText(EmpMainController.currentCustomer.getPhoneNumber());
        emailField.setText(EmpMainController.currentCustomer.getEmail());
        if(EmpMainController.currentCustomer instanceof CustomerPrivate){
            CustomerPrivate currentPrivate = (CustomerPrivate) EmpMainController.currentCustomer;
            privateCustomerInfoAnchorPane.setVisible(true);
            corporateCustomerInfoAnchorPane.setVisible(false);
            customerSSNLabel.setText(currentPrivate.getSsn());
            customerNameLabel.setText(currentPrivate.getFullName());
            customerIdLabel.setText(currentPrivate.getUserId());
        } else {
            CustomerCorporate currentCorporate = (CustomerCorporate) EmpMainController.currentCustomer;
            privateCustomerInfoAnchorPane.setVisible(false);
            corporateCustomerInfoAnchorPane.setVisible(true);
            companyNameLabel.setText(currentCorporate.getCompanyName());
            companyIdLabel.setText(currentCorporate.getUserId());
            companyOrgNrLabel.setText(currentCorporate.getOrgNumber());
        }

    }



}
