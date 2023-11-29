module com.piggybank.app.ui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.piggybank.app.ui to javafx.fxml;
    exports com.piggybank.app.ui;
}