module csci2020u.lab06 {
    requires javafx.controls;
    requires javafx.fxml;


    opens csci2020u.lab06 to javafx.fxml;
    exports csci2020u.lab06;
}