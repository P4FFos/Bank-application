module com.piggybank.app.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.piggybank.app.ui to javafx.fxml;
    opens com.piggybank.app.backend.customers to com.fasterxml.jackson.databind;

    exports com.piggybank.app.ui;
    exports com.piggybank.app.backend to com.fasterxml.jackson.databind;
    exports com.piggybank.app.backend.customers to com.fasterxml.jackson.databind;
    exports com.piggybank.app.backend.employees to com.fasterxml.jackson.databind;
    exports com.piggybank.app.backend.utils to com.fasterxml.jackson.databind;
    exports com.piggybank.app.backend.customers.debts to com.fasterxml.jackson.databind;
    exports com.piggybank.app.backend.customers.loans to com.fasterxml.jackson.databind;

}