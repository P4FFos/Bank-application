package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerTransferFundsController extends CustomerStartController implements Initializable {

    @FXML
    private Button transferCompleteTransferButton;
    @FXML
    private CheckBox transferFirstAmountCheckBox;
    @FXML
    private CheckBox transferFourthAmountCheckBox;
    @FXML
    private CheckBox transferSecondAmountCheckBox;
    @FXML
    private CheckBox transferThirdAmountCheckBox;
    @FXML
    private CheckBox transferUnderstandCheckBox;
    @FXML
    private DatePicker transferEnterDatePicker;
    @FXML
    private Label infoActualUserIdLabel;
    @FXML
    private PasswordField transferPasswordField;
    @FXML
    private TableView<Account> accountsTableView;
    @FXML
    private TableColumn<Account, String> accountIdColumn;
    @FXML
    private TableColumn<Account, String> accountNameColumn;
    @FXML
    private TableColumn<Account, Double> accountBalanceColumn;
    @FXML
    private TextField transferEnterAmountTextField;
    @FXML
    private TextField transferEnterRecieverAccountTextField;
    @FXML
    private TextField transferEnterMessageTextField;


    public void initialize(URL arg0, ResourceBundle arg1) {
        showCurrentCustomer();
        initializeTables();
    }

    @Override
    public void showCurrentCustomer(){
        super.showCurrentCustomer();
        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            infoActualUserIdLabel.setText(privateCustomer.getUserId());
            System.out.println("Customer Accounts Overview Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            infoActualUserIdLabel.setText(corporateCustomer.getUserId());
            System.out.println("Customer Accounts Overview Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }

    public void initializeTables(){
        accountNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountName"));
        accountIdColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountId"));
        accountBalanceColumn.setCellValueFactory(new PropertyValueFactory<Account, Double>("balance"));

        accountsTableView.setItems(currentCustomer.getAccountsList());

        // filter on balance column in descending order
        accountBalanceColumn.setSortType(TableColumn.SortType.DESCENDING);
        accountsTableView.getSortOrder().add(accountBalanceColumn);

        transferEnterDatePicker.setValue(LocalDate.now());
        transferCompleteTransferButton.setDisable(false);
    }

    public void completeTransfer(ActionEvent event) throws Exception {
        // first remove any unwanted styling (if run before)
        resetStyles();

        String receiverAccountId = transferEnterRecieverAccountTextField.getText();

        // checks and validates all fields in transaction window
        if(!validateInputs(receiverAccountId)) {
            return; // stop execution if validation fail
        }

        double amount = Double.parseDouble(transferEnterAmountTextField.getText());
        LocalDate date = transferEnterDatePicker.getValue();
        String message = transferEnterMessageTextField.getText();

        bank.transfer(currentAccount.getAccountId(), receiverAccountId, amount, message, date);
        accountsTableView.refresh();
        transferCompleteTransferButton.getStyleClass().add("button-all-green");

        // reset all buttons once transfer is completed so customer can make a new transfer
        transferEnterAmountTextField.clear();
        transferEnterMessageTextField.clear();
        transferEnterRecieverAccountTextField.clear();
        transferFirstAmountCheckBox.setSelected(false);
        transferSecondAmountCheckBox.setSelected(false);
        transferThirdAmountCheckBox.setSelected(false);
        transferFourthAmountCheckBox.setSelected(false);
        transferUnderstandCheckBox.setSelected(false);
        transferPasswordField.clear();
    }

    private boolean validateInputs(String receiverAccountId) {
        boolean isTermsChecked = transferUnderstandCheckBox.isSelected();
        boolean isPasswordValid = currentCustomer.validatePassword(transferPasswordField.getText());
        boolean isAmountNotEmpty = !transferEnterAmountTextField.getText().isEmpty();
        boolean isReceiverAccount = !transferEnterRecieverAccountTextField.getText().isEmpty(); // if receiver account has an input

        // since account is not selected automatically in TableView, then check with Optional that may be null or not
        Optional<Account> selectedAccount = Optional.ofNullable(accountsTableView.getSelectionModel().getSelectedItem());
        boolean isSelectedAccount = selectedAccount.isPresent();

        // if an account is selected in TableView, get the account ID
        if(selectedAccount.isPresent()) {
            currentAccount = currentCustomer.getAccount(selectedAccount.get().getAccountId());
        }

        boolean isNotSameAccount = true;
        // when an account is selected in TableView & account name specified, then check if strings are equal
        if (selectedAccount.isPresent() && receiverAccountId != null) {
            isNotSameAccount = !selectedAccount.get().getAccountId().equals(receiverAccountId);
        }

        // to avoid further repetitive code, applying one method updateValidationStyle
        updateValidationStyle(isSelectedAccount, accountsTableView);
        updateValidationStyle(isReceiverAccount, transferEnterRecieverAccountTextField); // needs to run before isNotSameAccount
        updateValidationStyle(isNotSameAccount, transferEnterRecieverAccountTextField); // they operate on the same field
        updateValidationStyle(isTermsChecked, transferUnderstandCheckBox);
        updateValidationStyle(isPasswordValid, transferPasswordField);
        updateValidationStyle(isAmountNotEmpty, transferEnterAmountTextField);

        return isSelectedAccount && isReceiverAccount && isNotSameAccount && isTermsChecked && isPasswordValid && isAmountNotEmpty;
    }

    private void updateValidationStyle(boolean condition, Control control) {
        if(!condition) {
            control.getStyleClass().add("text-field-invalid");
        } else {
            control.getStyleClass().remove("text-field-invalid");
        }
    }
    private void removeInvalidStyle(Control control) {
        control.getStyleClass().remove("text-field-invalid");
    }

    private void resetStyles() {
        removeInvalidStyle(transferUnderstandCheckBox);
        removeInvalidStyle(transferPasswordField);
        removeInvalidStyle(transferEnterAmountTextField);
        removeInvalidStyle(transferEnterRecieverAccountTextField);
        removeInvalidStyle(accountsTableView);
        transferCompleteTransferButton.getStyleClass().remove("button-all-green");

    }

    // toggle button in CustomerTransferFunds.fxml file
    public void toggleAmountOneHundred() {
        if(transferFirstAmountCheckBox.isSelected()) {
            transferEnterAmountTextField.clear();
            transferEnterAmountTextField.setText("100.0");

            transferSecondAmountCheckBox.setSelected(false);
            transferThirdAmountCheckBox.setSelected(false);
            transferFourthAmountCheckBox.setSelected(false);
        } else {
            transferEnterAmountTextField.clear();
        }
    }

    // toggle button in CustomerTransferFunds.fxml file
    public void toggleAmountTwoHundred() {
        if(transferSecondAmountCheckBox.isSelected()) {
            transferEnterAmountTextField.clear();
            transferEnterAmountTextField.setText("200.0");

            transferFirstAmountCheckBox.setSelected(false);
            transferThirdAmountCheckBox.setSelected(false);
            transferFourthAmountCheckBox.setSelected(false);
        } else {
            transferEnterAmountTextField.clear();
        }
    }

    // toggle button in CustomerTransferFunds.fxml file
    public void toggleAmountFiveHundred() {
        if(transferThirdAmountCheckBox.isSelected()) {
            transferEnterAmountTextField.clear();
            transferEnterAmountTextField.setText("500.0");

            transferFirstAmountCheckBox.setSelected(false);
            transferSecondAmountCheckBox.setSelected(false);
            transferFourthAmountCheckBox.setSelected(false);
        } else {
            transferEnterAmountTextField.clear();
        }
    }

    // toggle button in CustomerTransferFunds.fxml file
    public void toggleAmountOneThousand() {
        if(transferFourthAmountCheckBox.isSelected()) {
            transferEnterAmountTextField.clear();
            transferEnterAmountTextField.setText("1000.0");

            transferFirstAmountCheckBox.setSelected(false);
            transferSecondAmountCheckBox.setSelected(false);
            transferThirdAmountCheckBox.setSelected(false);
        } else {
            transferEnterAmountTextField.clear();
        }
    }
}
