package com.piggybank.app.ui;

import com.piggybank.app.backend.utils.FileHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.piggybank.app.backend.Bank;
import com.piggybank.app.backend.employees.Employee;
import com.piggybank.app.backend.utils.ContactCard;

public class UIMain extends Application {
    public static Bank bank;
    public static Bank getBank(){
        return bank;
    }

    @Override
    public void start(Stage stage) throws Exception {
        bank = FileHandler.jsonDeserializer("ui/src/main/java/com/piggybank/app/backend/bankData.json");

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