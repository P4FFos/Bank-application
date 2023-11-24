package piggyBankUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class EmployeeStartController {
    @FXML
    private AnchorPane employeeStart;
    @FXML
    private AnchorPane customerCard;
    @FXML
    private Button employeeStartButton;
    @FXML
    private Button customerCardButton;
    public void displayName(String name){
        System.out.println("Logged in as: " + name);
    }

    public void showEmployeeStart(){
        employeeStart.setVisible(true);
        customerCard.setVisible(false);
    }
    public void showEmployeeStart(ActionEvent event){
        employeeStart.setVisible(true);
        customerCard.setVisible(false);
    }

    public void showCustomerCard(ActionEvent event){
        customerCard.setVisible(true);
        employeeStart.setVisible(false);
    }



}
