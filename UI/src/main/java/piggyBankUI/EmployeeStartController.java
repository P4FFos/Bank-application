package piggyBankUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeStartController {
    @FXML
    private Button employeeStartButton;
    @FXML
    private Button customerCardButton;
    @FXML
    private Button searchCustomer;
    @FXML
    private Label searchMsg;
    @FXML
    private Label customerIdLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private TextField customerSearchTextField;
    @FXML
    private AnchorPane employeeStart;
    @FXML
    private AnchorPane customerCard;

    private Map<String, String> customers = new HashMap<>();
    private String customerID;
    private String customerName;

    public void displayName(String name){
        System.out.println("Logged in as: " + name);
    }

    public void showEmployeeStart(){
        employeeStart.setVisible(true);
        customerCard.setVisible(false);

        //Placeholder below. Will search through backend hashmap of customers when ready.
        customers.put("010101-1234", "Anna Andersson");
        customers.put("020202-2345", "Babben Borg");
        customers.put("030303-3456", "Charles Choco");
        customers.put("040404-4567", "David Dancer");
        customers.put("050505-5678", "Eve Ericson");
    }

    public void searchCustomer(ActionEvent event){
        //Placeholder logic.
        if(customers.containsKey(customerSearchTextField.getText())){
            customerID = customerSearchTextField.getText();
            customerName = customers.get(customerID);
            showCustomerCard(customerID, customerName);
            searchMsg.setText("");
        } else {
            searchMsg.setText("Not found.");
        }
    }
    public void showEmployeeStart(ActionEvent event){
        employeeStart.setVisible(true);
        customerCard.setVisible(false);
    }

    public void showCustomerCard(ActionEvent event){
        customerCard.setVisible(true);
        employeeStart.setVisible(false);
    }

    public void showCustomerCard(String id, String name){
        customerIdLabel.setText(id);
        customerNameLabel.setText(name);
        customerCard.setVisible(true);
        employeeStart.setVisible(false);
    }

}
