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
    public static String loadPath;
    public static String savePath;

    @Override
    public void start(Stage stage) throws Exception {
        loadPath = "ui/src/main/java/com/piggybank/app/backend/bankDataOnLogout.json";
        savePath = "ui/src/main/java/com/piggybank/app/backend/bankDataOnLogout.json"; // used in EmpMainController:logout()

        bank = FileHandler.jsonDeserializer(loadPath);

        /*
        ContactCard piggyWolvesbaneContactCard = new ContactCard("piggy@wolvesbane.com", "0735555555", "Big Brick Building", "12345", "Wolvesout");
        bank.createCustomerPrivate("5505051234", "Piggy", "Wolvesbane", "IATE5WOLVES", piggyWolvesbaneContactCard);
        bank.createAccount("C001", "Main Account");
        System.out.println("Piggy's account: " + bank.getCustomer("C001").getAccount("A00001").getAccountName());


        ContactCard trufflesIncContactCard = new ContactCard("contact@trufflesinc.com", "0101234567", "Muddy Basement", "12345", "Wolvesout");
        bank.createCustomerCorporate("12345", "Truffles Inc.", "123PIGLET", trufflesIncContactCard);
        bank.createAccount("C002", "Main Account");
        System.out.println("Company's account: " + bank.getCustomer("C002").getAccount("A00002").getAccountName());
         */

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