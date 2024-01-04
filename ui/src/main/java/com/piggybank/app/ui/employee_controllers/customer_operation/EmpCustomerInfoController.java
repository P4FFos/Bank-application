package com.piggybank.app.ui.employee_controllers.customer_operation;

import com.piggybank.app.backend.exceptions.PasswordException;
import com.piggybank.app.ui.employee_controllers.EmpMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class EmpCustomerInfoController extends EmpCustomerOverviewController implements Initializable {

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
    @FXML
    private Label invalidPasswordLabel;

    public void initialize(URL arg0, ResourceBundle arg1) {
        super.showCurrentEmployee();
        showCurrentCustomer();
        invalidPasswordLabel.setVisible(false);
    }
    @Override
    public void showCurrentCustomer(){
        super.showCurrentCustomer();
        streetField.setText(EmpMainController.currentCustomer.getStreet());
        zipField.setText(EmpMainController.currentCustomer.getZipCode());
        cityField.setText(EmpMainController.currentCustomer.getCity());
        phoneField.setText(EmpMainController.currentCustomer.getPhoneNumber());
        emailField.setText(EmpMainController.currentCustomer.getEmail());
    }


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
        invalidPasswordLabel.setVisible(false);
        try {
            String newPassword = passwordField.getText();
            passwordField.setText(newPassword);
            EmpMainController.currentCustomer.setPassword(newPassword);
            passwordField.setEditable(false);
        } catch (PasswordException passwordException) {
            invalidPasswordLabel.setVisible(true);
        }
    }



}
