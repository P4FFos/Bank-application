module com.piggybank.app.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.piggybank.app.ui to javafx.fxml;
    opens com.piggybank.app.backend.customers to com.fasterxml.jackson.databind, javafx.base;

    exports com.piggybank.app.ui;
    exports com.piggybank.app.backend to com.fasterxml.jackson.databind;
    exports com.piggybank.app.backend.customers to com.fasterxml.jackson.databind;
    exports com.piggybank.app.backend.employees to com.fasterxml.jackson.databind;
    exports com.piggybank.app.backend.utils to com.fasterxml.jackson.databind;
    exports com.piggybank.app.backend.customers.money_operations to com.fasterxml.jackson.databind;
    opens com.piggybank.app.backend.customers.money_operations to com.fasterxml.jackson.databind, javafx.base;
    exports com.piggybank.app.ui.customer_controllers;
    opens com.piggybank.app.ui.customer_controllers to javafx.fxml;
    exports com.piggybank.app.ui.employee_scenes;
    opens com.piggybank.app.ui.employee_scenes to javafx.fxml;
    exports com.piggybank.app.ui.customer_controllers.credit_controllers;
    opens com.piggybank.app.ui.customer_controllers.credit_controllers to javafx.fxml;
    exports com.piggybank.app.ui.customer_controllers.loan_controllers;
    opens com.piggybank.app.ui.customer_controllers.loan_controllers to javafx.fxml;
    exports com.piggybank.app.ui.employee_scenes.manage_controllers;
    opens com.piggybank.app.ui.employee_scenes.manage_controllers to javafx.fxml;
    exports com.piggybank.app.ui.employee_scenes.customer_operation;
    opens com.piggybank.app.ui.employee_scenes.customer_operation to javafx.fxml;

}