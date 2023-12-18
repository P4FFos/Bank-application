package com.piggybank.app.ui;

import com.piggybank.app.backend.utils.ContactCard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustomerController extends EmpMainController {
    @FXML
    private AnchorPane privateCustomerAnchorPane;
    @FXML
    private AnchorPane corporateCustomerAnchorPane;
    @FXML
    private Button saveNewCustomerButton;
    @FXML
    private Button empStartButton;
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

		String companyName = companyNameField.getText();
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String ssn = ssnField.getText();
		String orgNumber = orgNumberField.getText();
		String street = streetField.getText();
		String zip = zipField.getText();
		String city = cityField.getText();
		String phone = phoneField.getText();
		String email = emailField.getText();
		String password = passwordField.getText();

        if(privateCustomerCheckBox.isSelected()){
			ContactCard newContactCard = new ContactCard(email, phone, street, zip, city);
			try{
				UIMain.bank.createCustomerPrivate(ssn, firstName, lastName, password, newContactCard);
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
        } else if(corporateCustomerCheckBox.isSelected()){
			ContactCard newContactCard = new ContactCard(email, phone, street, zip, city);
			try{
				UIMain.bank.createCustomerCorporate(orgNumber, companyName, password, newContactCard);
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
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
}

