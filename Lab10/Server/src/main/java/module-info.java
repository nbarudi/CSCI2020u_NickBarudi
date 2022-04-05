module ca.bungo.chat.server {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.bungo.chat.server to javafx.fxml;
    exports ca.bungo.chat.server;
}