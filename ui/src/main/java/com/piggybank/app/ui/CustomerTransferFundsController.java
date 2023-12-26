package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.exceptions.AccountNotFoundException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerTransferFundsController extends CustomerStartController{

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private AnchorPane bodySeparatorsAnchorPane;

    @FXML
    private Label headerActualIdLabel;

    @FXML
    private AnchorPane headerAnchorPane;

    @FXML
    private Label headerBankNameLabel;

    @FXML
    private Label headerCustomerNameLabel;

    @FXML
    private ImageView headerIconImageView;

    @FXML
    private ImageView headerIconShadedImageView;

    @FXML
    private Label headerIdLabel;

    @FXML
    private Button headerLogoutButton;

    @FXML
    private Separator horisontalSeparator;

    @FXML
    private Label infoAccountIdLabel;

    @FXML
    private Label infoActualUserIdLabel;

    @FXML
    private AnchorPane infoAnchorPane;

    @FXML
    private Label infoNameLabel;

    @FXML
    private Button sideMenuAccountsOverviewButton;

    @FXML
    private AnchorPane sideMenuAnchorPane;

    @FXML
    private Button sideMenuCreditsButton1;

    @FXML
    private Button sideMenuFaqButton;

    @FXML
    private Label sideMenuLabel;

    @FXML
    private Button sideMenuLoansButton;

    @FXML
    private Button sideMenuStartButton;

    @FXML
    private Button sideMenuSupportButton;

    @FXML
    private Button sideMenuTransferFundsButton;

    @FXML
    private Label startActualTotalBalanceLabel;

    @FXML
    private Label startActualTotalDebtLabel;

    @FXML
    private AnchorPane startAnchorPane;

    @FXML
    private Label startAssetsLabel;

    @FXML
    private ListView<?> startAssetsListView;

    @FXML
    private Label startLoansLabel;

    @FXML
    private ListView<?> startLoansListView;

    @FXML
    private Label startNameLabel;

    @FXML
    private Label startTotalBalanceLabel;

    @FXML
    private Label startTotalDebtLabel;

    @FXML
    private Label start_Hello_Label;

    @FXML
    private ListView<?> transferAccountListView;

    @FXML
    private Label transferAmountHintLabel;

    @FXML
    private Label transferAmountLabel;

    @FXML
    private AnchorPane transferAnchorPane;

    @FXML
    private Button transferCompleteTransferButton;

    @FXML
    private TextField transferEnterAmountTextField;

    @FXML
    private Label transferEnterPasswordLabel;

    @FXML
    private TextField transferEnterRecieverAccountTextField;

    @FXML
    private CheckBox transferFirstAmountCheckBox;

    @FXML
    private CheckBox transferFourthAmountCheckBox;

    @FXML
    private Label transferFromAccountLabel;

    @FXML
    private Label transferFromHintLabel;

    @FXML
    private Label transferNewTransferLabel;

    @FXML
    private PasswordField transferPasswordField;

    @FXML
    private Label transferPasswordHintLabel;

    @FXML
    private Label transferRecieverHintLabel;

    @FXML
    private CheckBox transferSecondAmountCheckBox;

    @FXML
    private Label transferSekLabel;

    @FXML
    private Button transferSelectAccountButton;

    @FXML
    private CheckBox transferThirdAmountCheckBox;

    @FXML
    private Label transferToAccountLabel;

    @FXML
    private CheckBox transferUnderstandCheckBox;

    @FXML
    private Separator verticalSeparator;
    @FXML
    private TextField transferEnterMessageTextField;
    @FXML
    private DatePicker transferEnterDatePicker;

    @FXML
    private TableView<Account> accountsTableView;
    @FXML
    private TableColumn<Account, String> accountIdColumn;
    @FXML
    private TableColumn<Account, String> accountNameColumn;
    @FXML
    private TableColumn<Account, Double> accountBalanceColumn;

    private Account currentAccount;

    public void showCurrentCustomer(){

        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            headerCustomerNameLabel.setText(privateCustomer.getFullName());
            infoNameLabel.setText(privateCustomer.getFullName());
            infoActualUserIdLabel.setText(privateCustomer.getUserId());
            headerActualIdLabel.setText(privateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            headerCustomerNameLabel.setText(corporateCustomer.getCompanyName());
            infoNameLabel.setText(corporateCustomer.getCompanyName());
            infoActualUserIdLabel.setText(corporateCustomer.getUserId());
            headerActualIdLabel.setText(corporateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }

    public void initialize(URL arg0, ResourceBundle arg1) {
        accountNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountName"));
        accountIdColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountId"));
        accountBalanceColumn.setCellValueFactory(new PropertyValueFactory<Account, Double>("balance"));

        accountsTableView.setItems(currentCustomer.getAccountsList());

        // filter on balance column in descending order
        accountBalanceColumn.setSortType(TableColumn.SortType.DESCENDING);
        accountsTableView.getSortOrder().add(accountBalanceColumn);

        transferEnterDatePicker.setValue(LocalDate.now());
    }

    public void completeTransfer(ActionEvent event) throws Exception {
        // first remove any unwanted styling (if run before)
        clearAllInvalidStyles();

        String receiverAccountId = transferEnterRecieverAccountTextField.getText();

        if(!validateInputs(receiverAccountId)) {
            return; // stop execution if validation fail
        }

        // attempts to transfer
        try{
            double amount = Double.parseDouble(transferEnterAmountTextField.getText());
            LocalDate date = transferEnterDatePicker.getValue();
            String message = transferEnterMessageTextField.getText();

            bank.transfer(currentAccount.getAccountId(), receiverAccountId, amount, message, date);
            accountsTableView.refresh();
        } catch(NumberFormatException e) {
            transferEnterAmountTextField.getStyleClass().add("text-field-invalid");
            e.printStackTrace();
        } catch(AccountNotFoundException e) {
            transferEnterRecieverAccountTextField.getStyleClass().add("text-field-invalid");
            e.printStackTrace();
        }

    }

    private boolean validateInputs(String receiverAccountId) {
        boolean isTermsChecked = transferUnderstandCheckBox.isSelected();
        boolean isPasswordValid = currentCustomer.validatePassword(transferPasswordField.getText());
        boolean isAmountEmpty = transferEnterAmountTextField.getText().isBlank();

        currentAccount = accountsTableView.getSelectionModel().getSelectedItem();

        // check if objects are null before checking isSameAccount
        if (currentAccount == null) {
            accountsTableView.getStyleClass().add("text-field-invalid");
            applyInvalidStyle(isAmountEmpty, transferEnterAmountTextField);
            applyInvalidStyle(isTermsChecked, transferUnderstandCheckBox);
            applyInvalidStyle(isPasswordValid, transferPasswordField);
            return false;
        }

        if(receiverAccountId == null) {
            applyInvalidStyle(isAmountEmpty, transferEnterAmountTextField);
            applyInvalidStyle(isTermsChecked, transferUnderstandCheckBox);
            applyInvalidStyle(isPasswordValid, transferPasswordField);
            return false;
        }
        boolean isSameAccount = receiverAccountId.equals(currentAccount.getAccountId());

        // to avoid repetitive code, applying one method
        applyInvalidStyle(isTermsChecked, transferUnderstandCheckBox);
        applyInvalidStyle(isPasswordValid, transferPasswordField);
        applyInvalidStyle(isAmountEmpty, transferEnterAmountTextField);
        applyInvalidStyle(isSameAccount, transferEnterRecieverAccountTextField);

        return isTermsChecked && isPasswordValid && !isAmountEmpty && !isSameAccount;
    }

    private void applyInvalidStyle(boolean condition, Control control) {
        if(condition) {
            control.getStyleClass().add("text-field-invalid");
        }
    }

    private void clearAllInvalidStyles() {
        transferUnderstandCheckBox.getStyleClass().clear();
        transferPasswordField.getStyleClass().clear();
        transferEnterAmountTextField.getStyleClass().clear();
        transferEnterRecieverAccountTextField.getStyleClass().clear();
        accountsTableView.getStyleClass().clear();
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
