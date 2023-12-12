package com.piggybank.app.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    private Button employeeLoginButton;
    @FXML
    private Button customerLoginButton;

    private Parent root;
    private Stage stage;
    private Scene scene;


    public void employeeLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void customerLogin(ActionEvent event){
        //Tanya will connect to customer login
        System.out.println("Customer login not available yet.");
    }

}
