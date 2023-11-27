module ui.ui {
    requires javafx.controls;
    requires javafx.fxml;


    opens piggyBankUI to javafx.fxml;
    exports piggyBankUI;
}