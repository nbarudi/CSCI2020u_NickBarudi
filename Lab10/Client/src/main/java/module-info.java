module ca.bungo.client.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens ca.bungo.client.client to javafx.fxml;
    exports ca.bungo.client.client;
}