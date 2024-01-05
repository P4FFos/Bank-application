package com.piggybank.app.ui;

import com.piggybank.app.backend.utils.FileHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.piggybank.app.backend.Bank;

public class UIMain extends Application {
    public static Bank bank; //a single bank object used throughout the system
    public static String jsonPath; //reading mockdata in a json file on start

    @Override
    public void start(Stage stage) throws Exception {
        // also used in EmpMainController:logout() and CustomerStartController:logout()
        jsonPath = "ui/src/main/java/com/piggybank/app/backend/data_base/bankDataFinal.json";
        bank = FileHandler.jsonDeserializer(jsonPath);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/piggybank/app/ui/StartScene.fxml"));
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