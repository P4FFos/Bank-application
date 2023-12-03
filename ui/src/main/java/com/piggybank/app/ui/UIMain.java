package com.piggybank.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.Bank;
import src.utils.ContactCardCorporate;


public class UIMain extends Application {

    @Override
    public void start(Stage stage) {
        //LATER
        //Initialize Bank
        //Read mock data from json

        ContactCardCorporate contactCardBank = new ContactCardCorporate("123412", "email@email.com", "0734567811", "Street No1", 90210, "Beverly Hills");
        Bank bank = new Bank(contactCardBank);

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