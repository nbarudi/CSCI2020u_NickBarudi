module ca.bungo.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires commons.csv;


    opens ca.bungo.assignment2 to javafx.fxml;
    exports ca.bungo.assignment2;
}