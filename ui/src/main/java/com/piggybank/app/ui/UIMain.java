package com.piggybank.app.ui;

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
        ContactCard bankContactInfo = new ContactCard("employee@piggybank.com", "0701234567", "Oink Oink Alley", 35533, "Trufflesville");
        bank = new Bank(bankContactInfo);
        ContactCard employeeContactCard = new ContactCard("moiraine@sedai.com", "0731234567", "White Tower", 77777, "Tar Valon");
        bank.createEmployee("11DRAGON", employeeContactCard);

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