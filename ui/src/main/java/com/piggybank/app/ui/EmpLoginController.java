package com.piggybank.app.ui;

import com.piggybank.app.backend.Bank;
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

public class EmpLoginController {
    //.....................FXML ELEMENTS...........................
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    //..............................................................

    private boolean passwordValid;
    private boolean userIdValid;
    private String password;
    private String userId;
    private Stage stage;
    private Scene scene;
    private Parent root;


    //..........................METHODS.............................

    public void login(ActionEvent event) throws Exception {
        Bank bank = UIMain.getBank();
        userId = usernameTextField.getText();
        password = passwordField.getText();

        if(bank.verifyEmployee(userId, password)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpStart.fxml"));
            root = loader.load();

            EmpMainController controller = loader.getController();
            controller.initializeEmpoyeeSection("Emp123", userId);
            controller.fillcustomers();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
}

