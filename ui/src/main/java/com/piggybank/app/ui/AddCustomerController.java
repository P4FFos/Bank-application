package com.piggybank.app.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AddCustomerController extends EmpMainController {
    @FXML
    private AnchorPane privateCustomerAnchorPane;
    @FXML
    private AnchorPane corporateCustomerAnchorPane;
    @FXML
    private Button saveNewCustomerButton;
    @FXML
    private CheckBox corporateCustomerCheckBox;
    @FXML
    private CheckBox privateCustomerCheckBox;
    @FXML
    private Label newCustomerIdLabel;
    @FXML
    private Label empIdLabel;
    @FXML
    private Label empInitialsLabel;
    @FXML
    private Pane onSavePane;
    @FXML
    private TextField companyNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField ssnField;
    @FXML
    private TextField orgNumberField;
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
    private PasswordField passwordField;

    //-----------------METHODS THAT NEED FURTHER IMPLEMENTATION-----------

    public void addCustomer(){ // saveNewCustomerButton
        companyNameField.setEditable(false);
        firstNameField.setEditable(false);
        lastNameField.setEditable(false);
        ssnField.setEditable(false);
        orgNumberField.setEditable(false);
        streetField.setEditable(false);
        zipField.setEditable(false);
        cityField.setEditable(false);
        phoneField.setEditable(false);
        emailField.setEditable(false);
        passwordField.setEditable(false);
        saveNewCustomerButton.setVisible(false);

        if(privateCustomerCheckBox.isSelected()){
            //try:
            //use method in bank to create a CustomerPrivate.
            //get arguments from corresponding TextFields.
            //catch: if there are empty fields or if password is invalid

        } else if(corporateCustomerCheckBox.isSelected()){
            //try:
            //use method in bank to create a CustomerCorporate.
            //get arguments from corresponding TextFields.
            //catch: if there are empty fields or if password is invalid
        }

        privateCustomerCheckBox.setVisible(false);
        corporateCustomerCheckBox.setVisible(false);
        onSavePane.setVisible(true);
        //newCustomerIdLabel.setText(bank.getCurrentCustomer.getId()); or whatever method applies
        //Tanya will create logic for setting currentCustomer to the newly created customer

    }


    //-------------------FINISHED METHODS------------------------
    public void setCurrentEmployee(){
        empIdLabel.setText(EmpMainController.currentEmployee.getUserId());
        empInitialsLabel.setText(EmpMainController.currentEmployee.getInitials());
        System.out.println("Add Customer Page. Logged in as: " + EmpMainController.currentEmployee.getInitials());
    }

    public void togglePrivate(){ //privateCustomerCheckBox
        privateCustomerAnchorPane.setVisible(true);
        corporateCustomerAnchorPane.setVisible(false);
        corporateCustomerCheckBox.setSelected(false);
    }

    public void toggleCorporate(){ //corporateCustomerCheckbox
        corporateCustomerAnchorPane.setVisible(true);
        privateCustomerAnchorPane.setVisible(false);
        privateCustomerCheckBox.setSelected(false);
    }

    public void initialiseAddCustomer(){
        onSavePane.setVisible(false);
        privateCustomerCheckBox.setSelected(true);
        togglePrivate();
    }

}

