package com.piggybank.app.ui.employee_controllers;

import com.piggybank.app.backend.Bank;
import com.piggybank.app.backend.exceptions.PasswordException;
import com.piggybank.app.ui.UIMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EmpLoginController {
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

    public void login(ActionEvent event) throws Exception { //loginButton, if correct employee user id + password are entered -> switches to scene: EmpStart

        String password;
        String userId;

        try {
            Bank bank = UIMain.bank;
            userId = usernameTextField.getText();
            password = passwordField.getText();

            if (bank.verifyEmployee(userId, password)) {
                EmpMainController.currentEmployee = bank.getEmployee(userId); //sets the current employee before loading EmpStart, which cannot be loaded if currentEmployee is null

                loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/employee_scenes/EmpStart.fxml"));
                root = loader.load();

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch(PasswordException e) {
            incorrectLoginLabel.setVisible(true); //displays an error message if login credentials are invalid

        } catch (Exception e) {
            e.printStackTrace(); //mainly to make debugging easier for us
        }
    }

    public void goBackToStart(ActionEvent event) throws IOException { //backButton, switches to scene: StartScene
        loader = new FXMLLoader(getClass().getResource("/com/piggybank/app/ui/StartScene.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

