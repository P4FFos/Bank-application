package com.piggybank.app.ui;

import com.piggybank.app.backend.Bank;
import com.piggybank.app.backend.customers.Account;
import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.customers.CustomerCorporate;
import com.piggybank.app.backend.customers.CustomerPrivate;
import com.piggybank.app.backend.utils.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class CustomerStartController {

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private Label headerActualIdLabel;

    @FXML
    private AnchorPane headerAnchorPane;

    @FXML
    private Label headerBankNameLabel;

    @FXML
    private Label headerCustomerNameLabel;

    @FXML
    private ImageView headerIconImageView;

    @FXML
    private ImageView headerIconShadedImageView;

    @FXML
    private Label headerIdLabel;

    @FXML
    private Button headerLogoutButton;

    @FXML
    private Separator horisontalSeparator;

    @FXML
    private Button sideMenuAccountsOverviewButton;

    @FXML
    private AnchorPane sideMenuAnchorPane;

    @FXML
    private Button sideMenuCreditsButton;

    @FXML
    private Button sideMenuFaqButton;

    @FXML
    private Label sideMenuLabel;

    @FXML
    private Button sideMenuLoansButton;

    @FXML
    private Button sideMenuStartButton;

    @FXML
    private Button sideMenuSupportButton;

    @FXML
    private Button sideMenuTransferFundsButton;

    @FXML
    private Label startActualTotalBalanceLabel;

    @FXML
    private Label startActualTotalDebtLabel;

    @FXML
    private AnchorPane startAnchorPane;

    @FXML
    private Label startAssetsLabel;

    @FXML
    private ListView<?> startAssetsListView;

    @FXML
    private Label startLoansLabel;

    @FXML
    private ListView<?> startLoansListView;

    @FXML
    private Label startNameLabel;

    @FXML
    private Label startTotalBalanceLabel;

    @FXML
    private Label startTotalDebtLabel;

    @FXML
    private Label start_Hello_Label;

    @FXML
    private Separator verticalSeparator;

    private FXMLLoader loader;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public static Bank bank = UIMain.bank;
    public static Customer currentCustomer;

    public void setCurrentCustomer(Customer customer){ //Method called from CustomerLoginController
        currentCustomer = customer;

        //if there is a customer ID label:
        //customerIdLabel.setText(currentCustomer.getUserId());

        if (customer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            headerCustomerNameLabel.setText(privateCustomer.getFullName());
            startNameLabel.setText(privateCustomer.getFullName());
            headerActualIdLabel.setText(privateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            headerCustomerNameLabel.setText(corporateCustomer.getCompanyName());
            startNameLabel.setText(corporateCustomer.getCompanyName());
            headerActualIdLabel.setText(corporateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }

    public void showCurrentCustomer(){

        if (currentCustomer instanceof CustomerPrivate) {
            CustomerPrivate privateCustomer = (CustomerPrivate) currentCustomer;
            headerCustomerNameLabel.setText(privateCustomer.getFullName());
            startNameLabel.setText(privateCustomer.getFullName());
            headerActualIdLabel.setText(privateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + privateCustomer.getFullName());
        } else {
            CustomerCorporate corporateCustomer = (CustomerCorporate) currentCustomer;
            headerCustomerNameLabel.setText(corporateCustomer.getCompanyName());
            startNameLabel.setText(corporateCustomer.getCompanyName());
            headerActualIdLabel.setText(corporateCustomer.getUserId());

            System.out.println("Customer Start Page. Logged in as: " + corporateCustomer.getCompanyName());
        }
    }

    //-----------------SIDE MENU NAVIGATION----------------
    public void goToStart(ActionEvent event) throws IOException { //sideMenuStartButton
        loader = new FXMLLoader(getClass().getResource("CustomerStart.fxml"));
        root = loader.load();

        CustomerStartController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToAccountsOverview(ActionEvent event) throws IOException { //sideMenuAccountsOverviewButton
        loader = new FXMLLoader(getClass().getResource("CustomerAccountsOverview.fxml"));
        root = loader.load();

        CustomerAccountsOverviewController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToTransferFunds(ActionEvent event) throws IOException { //sideMenuTransferFundsButton
        loader = new FXMLLoader(getClass().getResource("CustomerTransferFunds.fxml"));
        root = loader.load();

        CustomerTransferFundsController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToLoans(ActionEvent event) throws IOException { //sideMenuLoansButton
        loader = new FXMLLoader(getClass().getResource("CustomerLoansOverview.fxml"));
        root = loader.load();

        CustomerLoansOverviewController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToCredits(ActionEvent event) throws IOException { //sideMenuCreditsButton
        loader = new FXMLLoader(getClass().getResource("CustomerCreditsOverview.fxml"));
        root = loader.load();

        CustomerCreditsOverviewController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToFaq(ActionEvent event) throws IOException { //sideMenuFaqButton
        loader = new FXMLLoader(getClass().getResource("CustomerFaq.fxml"));
        root = loader.load();

        CustomerFaqController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToSupport(ActionEvent event) throws IOException { //sideMenuSupportButton
        loader = new FXMLLoader(getClass().getResource("CustomerSupport.fxml"));
        root = loader.load();

        CustomerSupportController controller = loader.getController();
        controller.showCurrentCustomer();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//-----------------HEADER MENU NAVIGATION----------------

    public void logout(ActionEvent event) throws IOException { //headerLogoutButton
        String saveFile = "ui/src/main/java/com/piggybank/app/backend/bankDataOnLogout.json";
        FileHandler.jsonSerializer(saveFile, bank);

        currentCustomer = null;
        System.out.println("Logged out. Have a nice day.");
        loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
