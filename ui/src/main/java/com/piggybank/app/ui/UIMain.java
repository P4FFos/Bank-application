package com.piggybank.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*import src.*;
import src.utils.ContactCard;
import src.utils.ContactCardCorporate;*/

public class UIMain extends Application {
    /* Uncomment when methods in backend src are functioning
    private Bank bank;
    */
    @Override
    public void start(Stage stage) {
        /*LATER
        Initialize Bank
        Read mock data from json
        ------------------------------------
        ContactCardCorporate contactCardBank = new ContactCardCorporate("123412", "email@email.com", "0734567811", "Street No1", 90210, "Beverly Hills");
        bank = new Bank(contactCardBank);
         src/Bank: createEmployee() must be amended before I can use it here. Would be better to name it "addEmployee" and have it create
         and add an employee to the HashMap of employees. Should not take in an employee as a parameter. Where would such an employee be
         created?? /Tanya


        String userId, BankEmployee employee:
        String userId, String password, ContactCard contactCard)
        contactCard: String email, String phoneNumber, String streetAddress, int zipCode, String city
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

    /*public Bank getBank(){
        return bank;
    }*/

    public static void main(String[] args) {
        launch(args);
    }
}