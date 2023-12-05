package com.piggybank.app.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    //.....................FXML ELEMENTS...........................
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    //..............................................................

    private boolean passwordValid;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String username;

    //..........................METHODS.............................

    public void login(ActionEvent event) throws IOException {
        if(passwordField.getText().length() < 4){ //Replace with logic for password validation
            passwordValid = false;
            System.out.println("Password must be at least 4 characters long.");
        } else {
            passwordValid = true;
            System.out.println("Successfully logged in!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeMainScene.fxml"));
            root = loader.load();

            //Placeholder logic. Connect appropriately with backend when ready.
            username = usernameTextField.getText();
            EmployeeMainController employeeStartController = loader.getController();
            employeeStartController.displayUser("Emp123", username);
            employeeStartController.showEmployeeStart();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

}