package com.piggybank.app.ui;

import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.CustomerPrivate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageLoansController extends EmpMainController {
    @FXML
    private Button selectedLoanButton;
    @FXML
    private Button addLoanButton;
    @FXML
    private ListView loansListView;
    @FXML
    private Label customerIdLabel;
    @FXML
    private Label customerSSNLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label empIdLabel;
    @FXML
    private Label empInitialsLabel;
    @FXML
    private Label loanIdLabel;
    @FXML
    private Label debtLabel;
    @FXML
    private Label loanAmountLabel;

    public void showCurrentEmployee(){
        empIdLabel.setText(EmpMainController.currentEmployee.getUserId());
        empInitialsLabel.setText(EmpMainController.currentEmployee.getInitials());
        System.out.println("Manage Loans Page. Logged in as: " + EmpMainController.currentEmployee.getInitials());
    }

    public void showCurrentCustomer() {
        Customer currentCustomer = EmpMainController.currentCustomer;
        customerIdLabel.setText(currentCustomer.getUserId());
        if(currentCustomer instanceof CustomerPrivate customerPrivate){
            customerSSNLabel.setText(customerPrivate.getSsn());
            customerNameLabel.setText(customerPrivate.getFullName());
        }
        //addAccountAnchorPane.setVisible(false);
        //contentAnchorPane.setVisible(true);
    }

    public void setCurrentLoan(){

    }

    public void addLoan(ActionEvent event){

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
