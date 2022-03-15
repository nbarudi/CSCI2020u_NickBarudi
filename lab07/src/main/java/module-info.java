module ca.bungo.lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;

    opens ca.bungo.lab07 to javafx.fxml;
    exports ca.bungo.lab07;
}