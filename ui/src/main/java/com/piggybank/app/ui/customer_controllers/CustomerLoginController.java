package com.piggybank.app.ui.customer_controllers;

import com.piggybank.app.backend.Bank;
import com.piggybank.app.backend.exceptions.PasswordException;
import com.piggybank.app.ui.UIMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerLoginController {
    //.....................FXML ELEMENTS...........................
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label incorrectLoginLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;


    //..........................METHODS.............................

    public void login(ActionEvent event) throws Exception { //loginButton

        String password;
        String userId;

        try {
            Bank bank = UIMain.bank;
            userId = usernameTextField.getText();
            password = passwordField.getText();

            if (bank.verifyCustomer(userId, password)) {
                CustomerStartController.currentCustomer = bank.getCustomer(userId);
                CustomerStartController.currentCustomersAccounts = CustomerStartController.currentCustomer.getAccounts();

                loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/customer_scenes/CustomerStart.fxml"));
                root = loader.load();

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (PasswordException e) {
            incorrectLoginLabel.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goBackToStart(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/StartScene.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
