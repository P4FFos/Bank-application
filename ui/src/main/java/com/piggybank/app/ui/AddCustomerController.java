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

    public void addCustomer(){ // saveNewCustomerButton
        //throw exception if fields not filled out properly or if there are any empty fields

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
            //bank.createPrivateCustomer()with arguments (addPrivateCustomer()??)
        } else if(corporateCustomerCheckBox.isSelected()){
            //bank.createCorporateCustomewr() with arguments
        }

        privateCustomerCheckBox.setVisible(false);
        corporateCustomerCheckBox.setVisible(false);
        onSavePane.setVisible(true);
        //newCustomerIdLabel.setText(bank.getCurrentCustomer.getId()); or whatever method applies

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

