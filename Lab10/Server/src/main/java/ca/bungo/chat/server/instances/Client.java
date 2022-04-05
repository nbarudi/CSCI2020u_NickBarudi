package ca.bungo.chat.server.instances;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client implements Runnable {

    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private ServerHandler server;
    public boolean isStopped = false;

    public Map<Long, String> messages = new HashMap<>();


    public Client(Socket clientSocket, ServerHandler server){
        System.out.println("Client Created!");
        this.clientSocket = clientSocket;
        this.server = server;
        try {
            this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
            this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        writer.println(message);
    }

    public void run(){
        while(!isStopped){
            try{
                String message = reader.readLine();
                if(clientSocket.isClosed() || message == null){
                    System.out.println("Cannot find client! Stopping Service!");
                    stopConnection();
                    break;
                }
                System.out.println(message);
                server.addMessage(message);
                writer.println("Recieved!");
            }catch(IOException e){}
        }
    }

    public synchronized void stopConnection() throws IOException {
        isStopped = true;
        this.writer.close();
        this.reader.close();
        this.clientSocket.close();
    }

}
