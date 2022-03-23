module csci2020u.lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;

    opens csci2020u.lab08 to javafx.fxml;
    exports csci2020u.lab08;
}