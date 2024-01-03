package com.piggybank.app.ui.employee_controllers.customer_operation;

import com.piggybank.app.backend.exceptions.InvalidEmailException;
import com.piggybank.app.backend.exceptions.PasswordException;
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
	@FXML
	private Label requiredLabel;
    @FXML
    private Label invalidEmailAddressLabel;
    @FXML
    private Pane invalidEmailPane;


    public void initialize(URL arg0, ResourceBundle arg1) {
        super.showCurrentEmployee();
        togglePrivate();
        currentCustomer = null;
        currentCustomersAccounts = null;

        onSavePane.setVisible(false);
        invalidEmailPane.setVisible(false);
        wrongPasswordPane.setVisible(false);
        privateCustomerCheckBox.setSelected(true);

        System.out.println("Employee Add Customer Page. Logged in as: " + currentEmployee.getInitials());
    }

    public void addCustomer() throws Exception { // saveNewCustomerButton
        invalidEmailPane.setVisible(false);
        wrongPasswordPane.setVisible(false);

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

		if(checkForEmptyFields()) return;

        if(privateCustomerCheckBox.isSelected()){

			try{
                ContactCard newContactCard = new ContactCard(email, phone, street, zip, city);
				userId = UIMain.bank.createCustomerPrivate(ssn, firstName, lastName, password, newContactCard);
                successfulSave(userId);
			} catch (PasswordException e){
                wrongPasswordPane.setVisible(true);
			} catch (InvalidEmailException e) {
                invalidEmailPane.setVisible(true);
                e.printStackTrace();
            }
        } else if(corporateCustomerCheckBox.isSelected()){

			try{
                ContactCard newContactCard = new ContactCard(email, phone, street, zip, city);
				userId = UIMain.bank.createCustomerCorporate(orgNumber, companyName, password, newContactCard);
                successfulSave(userId);
			} catch (PasswordException e){
                wrongPasswordPane.setVisible(true);
			} catch (InvalidEmailException e) {
                e.printStackTrace();
                invalidEmailPane.setVisible(true);
            }
        }
    }

    public void successfulSave(String userId){
        newCustomerIdLabel.setText(userId);
        privateCustomerCheckBox.setVisible(false);
        corporateCustomerCheckBox.setVisible(false);
        onSavePane.setVisible(true);
        wrongPasswordPane.setVisible(false);
        saveNewCustomerButton.setVisible(false);
		requiredLabel.setVisible(false);
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
        invalidEmailPane.setVisible(false);
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

	public boolean checkForEmptyFields(){
		if(privateCustomerCheckBox.isSelected()){
			if(firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || ssnField.getText().isEmpty() || streetField.getText().isEmpty() || zipField.getText().isEmpty() || cityField.getText().isEmpty() || phoneField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty()){
				requiredLabel.styleProperty().setValue("-fx-text-fill: red");
				return true;
			} else {
				requiredLabel.styleProperty().setValue("-fx-text-fill: black");
				return false;
			}
		} else if(corporateCustomerCheckBox.isSelected()){
			if(companyNameField.getText().isEmpty() || orgNumberField.getText().isEmpty() || streetField.getText().isEmpty() || zipField.getText().isEmpty() || cityField.getText().isEmpty() || phoneField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty()){
				requiredLabel.styleProperty().setValue("-fx-text-fill: red");
				return true;
			} else {
				requiredLabel.styleProperty().setValue("-fx-text-fill: black");
				return false;
			}
		}
		return false;
	}

}

