package com.piggybank.app.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ManageLoansController extends EmpMainController {
    @FXML
    private Button selectedLoanButton;
    @FXML
    private Button addLoanButton;
    @FXML
    private ListView loansListView;
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

    public void setCurrentEmployee(){
        empIdLabel.setText(EmpMainController.currentEmployee.getUserId());
        empInitialsLabel.setText(EmpMainController.currentEmployee.getInitials());
        System.out.println("Manage Loans Page. Logged in as: " + EmpMainController.currentEmployee.getInitials());
    }

    public void setCurrentLoan(){

    }

    public void addLoan(ActionEvent event){

    }

}
