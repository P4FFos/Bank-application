package com.piggybank.app.ui;

import com.piggybank.app.backend.Bank;
import com.piggybank.app.backend.employees.Employee;
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

    //....................CURRENT EMPLOYEE..................
    private Employee currentEmployee = null;

    //..........................METHODS.............................

    public static Employee getCurrentEmployee(){
        return getCurrentEmployee();
    }

    public void login(ActionEvent event) throws Exception {
        Stage stage;
        Scene scene;
        Parent root;
        String password;
        String userId;

        try {
            Bank bank = UIMain.getBank();
            userId = usernameTextField.getText();
            password = passwordField.getText();

            if (bank.verifyEmployee(userId, password)) { //using method from bank
                currentEmployee = bank.getEmployee(userId);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpStart.fxml"));
                root = loader.load();

                EmpMainController controller = loader.getController();
                controller.initializeEmployeeSection("Emp123", userId);
                controller.fillcustomers();

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            // Show error message or summon pop-up window
            e.printStackTrace();
        }
    }
}

