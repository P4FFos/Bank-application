package com.piggybank.app.ui.customer_controllers;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.exceptions.AccountNotFoundException;
import com.piggybank.app.backend.exceptions.InsufficientBalanceException;

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
    private PasswordField transferPasswordField;
    @FXML
    private TableView<Account> accountsTableView;
    @FXML
    private TableColumn<Account, String> accountIdColumn;
    @FXML
    private TableColumn<Account, String> accountNameColumn;
    @FXML
    private TableColumn<Account, String> accountBalanceColumn;
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

    public void initializeTables(){
        // set table columns and prepare factory method
        accountNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountName"));
        accountIdColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountId"));
        accountBalanceColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("balanceString"));

        accountsTableView.setItems(currentCustomer.getAccountsList());

        // sort on id column in ascending order
        accountIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        accountsTableView.getSortOrder().add(accountIdColumn);

        // set today's value
        transferEnterDatePicker.setValue(LocalDate.now());

        transferCompleteTransferButton.setDisable(false);
    }

    public void completeTransfer() throws Exception {
        // reset styling (needed when running several times)
        resetStyles();

        String receiverAccountId = transferEnterRecieverAccountTextField.getText();

        // checks and validates all fields in transaction window
        if(!validateInputs(receiverAccountId)) {
            return; // stop execution if validation fail
        }

        double amount = Double.parseDouble(transferEnterAmountTextField.getText());
        LocalDate date = transferEnterDatePicker.getValue();
        String message = transferEnterMessageTextField.getText();

        // when error occur in try-catch it will format text fields, tables and buttons according to what is wrong/missing
        try {
            // will update balance and transactions on both accounts
            bank.transfer(currentAccount.getAccountId(), receiverAccountId, amount, message, date);
            accountsTableView.refresh(); // show the changes made directly
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
        } catch(InsufficientBalanceException e) {
            transferEnterAmountTextField.getStyleClass().add("text-field-invalid");
        } catch(AccountNotFoundException e) {
            transferEnterRecieverAccountTextField.getStyleClass().add("text-field-invalid");
        }
    }

    //..........................VERIFICATION & HELPER METHODS.............................
    private boolean validateInputs(String receiverAccountId) {
        boolean isTermsChecked = transferUnderstandCheckBox.isSelected();
        boolean isPasswordValid = currentCustomer.validatePassword(transferPasswordField.getText());
        boolean isAmountNotEmpty = !transferEnterAmountTextField.getText().isEmpty();
        boolean isReceiverAccount = !transferEnterRecieverAccountTextField.getText().isEmpty(); // if receiver account has an input
        boolean isDateValid = !transferEnterDatePicker.getValue().isBefore(LocalDate.now());

        // since account is not selected automatically in TableView, then check with Optional that may be null or not
        Optional<Account> selectedAccount = Optional.ofNullable(accountsTableView.getSelectionModel().getSelectedItem());
        boolean isSelectedAccount = selectedAccount.isPresent();
        boolean isBalanceAvailable = false;

        // if an account is selected in TableView, get the account ID
        if(selectedAccount.isPresent()) {
            currentAccount = currentCustomer.getAccount(selectedAccount.get().getAccountId());
            isBalanceAvailable = currentAccount.getBalance() > 0;
        }

        boolean isNotSameAccount = true;
        // when an account is selected in TableView & account name specified, then check if strings are equal
        if (selectedAccount.isPresent() && receiverAccountId != null) {
            isNotSameAccount = !selectedAccount.get().getAccountId().equals(receiverAccountId);
        }

        // to avoid even more repetitive code, applying one method updateValidationStyle
        updateValidationStyle(isDateValid, transferEnterDatePicker);
        updateValidationStyle(isSelectedAccount, accountsTableView);
        updateValidationStyle(isReceiverAccount, transferEnterRecieverAccountTextField);
        updateValidationStyle(isBalanceAvailable, accountsTableView);
        if (isReceiverAccount) {
            updateValidationStyle(isNotSameAccount, transferEnterRecieverAccountTextField);
        }
        updateValidationStyle(isTermsChecked, transferUnderstandCheckBox);
        updateValidationStyle(isPasswordValid, transferPasswordField);
        updateValidationStyle(isAmountNotEmpty, transferEnterAmountTextField);

        return isDateValid && isSelectedAccount && isReceiverAccount && isBalanceAvailable && isNotSameAccount && isTermsChecked && isPasswordValid && isAmountNotEmpty;
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

    //..........................TOGGLES.............................

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
