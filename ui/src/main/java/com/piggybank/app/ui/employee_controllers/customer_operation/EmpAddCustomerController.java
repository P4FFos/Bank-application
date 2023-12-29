package com.piggybank.app.ui.employee_controllers.customer_operation;

import com.piggybank.app.backend.utils.ContactCard;
import com.piggybank.app.ui.UIMain;
import com.piggybank.app.ui.employee_controllers.EmpMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

public class EmpAddCustomerController extends EmpMainController implements Initializable {
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
    private Pane wrongPasswordPane;
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

    public void initialize(URL arg0, ResourceBundle arg1) {
        super.showCurrentEmployee();
        onSavePane.setVisible(false);
        wrongPasswordPane.setVisible(false);
        privateCustomerCheckBox.setSelected(true);
        togglePrivate();
        System.out.println("Employee Add Customer Page. Logged in as: " + currentEmployee.getInitials());
    }

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

        String userId;
        if(privateCustomerCheckBox.isSelected()){

			ContactCard newContactCard = new ContactCard(email, phone, street, zip, city);
			try{
				userId = UIMain.bank.createCustomerPrivate(ssn, firstName, lastName, password, newContactCard);
                successFulSave(userId);
			} catch (Exception e){
				System.out.println(e.getMessage());
                wrongPasswordPane.setVisible(true);
			}
        } else if(corporateCustomerCheckBox.isSelected()){
			ContactCard newContactCard = new ContactCard(email, phone, street, zip, city);
			try{
				userId = UIMain.bank.createCustomerCorporate(orgNumber, companyName, password, newContactCard);
                successFulSave(userId);
			} catch (Exception e){
				System.out.println(e.getMessage());
                wrongPasswordPane.setVisible(true);
			}
        }

    }

    public void successFulSave(String userId){
        newCustomerIdLabel.setText(userId);
        privateCustomerCheckBox.setVisible(false);
        corporateCustomerCheckBox.setVisible(false);
        onSavePane.setVisible(true);
        wrongPasswordPane.setVisible(false);
        saveNewCustomerButton.setVisible(false);
        passwordField.setEditable(false);
        currentCustomer = bank.getCustomer(userId);
        currentCustomersAccounts = currentCustomer.getAccounts();
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

}

