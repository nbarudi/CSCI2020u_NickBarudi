module ca.bungo.lab09 {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;


    opens ca.bungo.lab09 to javafx.fxml;
    exports ca.bungo.lab09;
}