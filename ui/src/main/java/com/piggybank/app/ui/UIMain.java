package com.piggybank.app.ui;

import com.piggybank.app.backend.utils.FileHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.piggybank.app.backend.Bank;

public class UIMain extends Application {
    public static Bank bank;
    public static String loadPath;
    public static String savePath;

    @Override
    public void start(Stage stage) throws Exception {
        loadPath = "ui/src/main/java/com/piggybank/app/backend/data_base/bankDataOnLogout.json";
        bank = FileHandler.jsonDeserializer(loadPath);

        // used in EmpMainController:logout() and CustomerStartController:logout()
        savePath = "ui/src/main/java/com/piggybank/app/backend/data_base/bankDataOnLogout.json";

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