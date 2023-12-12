package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.CustomerPrivate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EmpCustomerInfoController extends EmpMainController {
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
    private Label customerNameLabel;
    @FXML
    private Label customerIdLabel;
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

    private Customer currentCustomer;

    public void streetEditable(){ //editStreetButton
        streetField.setEditable(true);
    }
    //implement the "Editable" methods the same way as streetEditable() for their corresponding fields
    public void zipEditable(){ //editZipButton

    }
    public void cityEditable(){ //editCityButton

    }
    public void phoneEditable(){ //editPhoneButton

    }
    public void emailEditable(){ //editEmailButton

    }
    public void passwordEditable(){ //editPasswordButton

    }
    public void setNewStreet(){ //saveNewStreetButton
        String newStreet = streetField.getText();
        streetField.setText(newStreet);
        currentCustomer.setStreet(newStreet);
        streetField.setEditable(false);
    }
    //implement the "setNew" methods the same way as setNewStreet() and create the necessary setters in Customer and ContactCard
    public void setNewZip(){ //saveNewZipButton

    }
    public void setNewCity(){ //saveNewCityButton

    }
    public void setNewPhone(){ //saveNewPhoneButton

    }
    public void setNewEmail(){ //saveNewEmailButton

    }
    public void setNewPassword(){ //saveNewPasswordButton
        //If the employee has checked the customer's id in real life,
        //they don't need to enter the old password to be able to update
        //it. It could be a service performed by a bank employee for a
        //customer who has forgotten their password.
    }

    public void displayCurrentCustomer(String id, String name, String ssn){ //change parameter to Customer and modify implementation accordingly
        customerIdLabel.setText(id);
        customerNameLabel.setText(name);
        //Tanya will add a label in the UI for SSN
    }


}
