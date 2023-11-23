package piggyBankUI;

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
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    private boolean passwordValid;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String username;

    public void login(ActionEvent event) throws IOException {

        if(passwordField.getText().length() < 4){ //Replace with logic for password validation
            passwordValid = false;
            System.out.println("Password must be at least 4 characters long.");
        } else {
            passwordValid = true;
            System.out.println("Successfully logged in!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeStart.fxml"));
            root = loader.load();

            username = usernameTextField.getText();
            EmployeeStartController employeeStartController = loader.getController();
            employeeStartController.displayName(username);
            employeeStartController.showEmployeeStart();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


}