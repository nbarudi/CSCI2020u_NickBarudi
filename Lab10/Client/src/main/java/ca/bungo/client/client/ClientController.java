package ca.bungo.client.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {

    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;

    @FXML
    private Label serverStatus;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField messageField;

    public void initialize(){
        try {
            clientSocket = new Socket("127.0.0.1", 8000);
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            serverStatus.setText("Connected!");
            serverStatus.setTextFill(Color.GREEN);
        } catch(IOException e){
            e.printStackTrace();
            serverStatus.setText("Disconnected!");
            serverStatus.setTextFill(Color.RED);
        }
    }

    public void sendMessage(ActionEvent actionEvent) throws IOException {
        writer.println(usernameField.getText() + ": " + messageField.getText());
        //String resp = reader.readLine();
        //System.out.println(resp);
    }

    public void exitClient(ActionEvent actionEvent) throws IOException {
        //Bypass to creating static instance of Stage
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage)button.getScene().getWindow();
        stage.close();

        writer.close();
        reader.close();
        clientSocket.close();
    }
}