package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.exceptions.AccountNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
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
        resetStyles();

        String receiverAccountId = transferEnterRecieverAccountTextField.getText();

        if(!validateInputs(receiverAccountId)) {
            return; // stop execution if validation fail
        }

        double amount = Double.parseDouble(transferEnterAmountTextField.getText());
        LocalDate date = transferEnterDatePicker.getValue();
        String message = transferEnterMessageTextField.getText();

        bank.transfer(currentAccount.getAccountId(), receiverAccountId, amount, message, date);
        accountsTableView.refresh();
    }

    private boolean validateInputs(String receiverAccountId) {
        boolean isTermsChecked = transferUnderstandCheckBox.isSelected();
        boolean isPasswordValid = currentCustomer.validatePassword(transferPasswordField.getText());
        boolean isAmountNotEmpty = !transferEnterAmountTextField.getText().isEmpty();
        boolean isOtherAccount = !transferEnterRecieverAccountTextField.getText().isEmpty();

        // since account is not selected automatically, then check with Optional that may be null or not
        Optional<Account> selectedAccount = Optional.ofNullable(accountsTableView.getSelectionModel().getSelectedItem());
        boolean isCurrentAccount = selectedAccount.isPresent();

        boolean isNotSameAccount = true;
        // when an account is selected in TableView & account name specified, then check if strings are equal
        if (selectedAccount.isPresent() && receiverAccountId != null) {
            isNotSameAccount = !selectedAccount.get().getAccountId().equals(receiverAccountId);
        }

        // to avoid further repetitive code, applying one method updateValidationStyle
        updateValidationStyle(isCurrentAccount, accountsTableView);
        updateValidationStyle(isOtherAccount, transferEnterRecieverAccountTextField);
        updateValidationStyle(isNotSameAccount, transferEnterRecieverAccountTextField);
        updateValidationStyle(isTermsChecked, transferUnderstandCheckBox);
        updateValidationStyle(isPasswordValid, transferPasswordField);
        updateValidationStyle(isAmountNotEmpty, transferEnterAmountTextField);

        return isCurrentAccount && isOtherAccount && isNotSameAccount && isTermsChecked && isPasswordValid && isAmountNotEmpty;
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
