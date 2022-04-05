package ca.bungo.chat.server;

import ca.bungo.chat.server.instances.ServerHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ServerController {

    private ServerHandler server;

    @FXML
    public TextArea chatBox;

    public void initialize(){
        server = new ServerHandler(8000, this);
        Thread serverThread = new Thread(server);
        serverThread.start();
    }

    @FXML
    public void onExitClicked(ActionEvent actionEvent) {
        //Tree Foolery to get the stage without setting a static instance of it
        Button button = (Button)actionEvent.getSource();
        Stage stage = (Stage)button.getScene().getWindow();
        stage.close();

        //ToDo: Close any open sockets and stop any active threads
        server.stop();
    }
}