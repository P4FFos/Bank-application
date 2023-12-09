package com.piggybank.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
//import src.Bank;
//import src.utils.ContactCard;

public class UIMain extends Application {

    @Override
    public void start(Stage stage) {
        //ContactCard bankContactInfo = new ContactCard("employee@piggybank.com", "0701234567", "Oink Oink Alley", 35533, "Trufflesville");
        //Bank bank = new Bank(bankContactInfo);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}