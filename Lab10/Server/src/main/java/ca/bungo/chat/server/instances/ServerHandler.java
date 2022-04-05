package ca.bungo.chat.server.instances;

import ca.bungo.chat.server.ServerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.TreeMap;

public class ServerHandler implements Runnable{

    private ServerSocket serverSocket;
    private Thread runningThread;
    private int port;
    private boolean isStopped = false;

    private ArrayList<Client> connectedClients = new ArrayList<>();

    private ServerController controller;

    public ServerHandler(int port, ServerController controller){
        this.port = port;
        this.controller = controller;
    }

    public void run(){
        synchronized (this){
            this.runningThread = Thread.currentThread();
        }
        try {
            serverSocket = new ServerSocket(port);
            while(!isStopped()){
                Socket clientSocket = serverSocket.accept();
                Client client = new Client(clientSocket, this);
                connectedClients.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            if(isStopped()){
                System.out.println("Server is Stopped!");
            }else{
                throw new RuntimeException("Error occurred while creating client!", e);
            }
        }
    }

    private synchronized boolean isStopped(){
        return this.isStopped;
    }


    public void stop(){
        try{
            this.serverSocket.close();
            for(Client client : connectedClients){
                client.sendMessage("Server Shutting Down");
                try{
                    client.stopConnection();
                }catch(IOException e){
                    throw new RuntimeException("Failed to stop client connection!", e);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public synchronized void addMessage(String message){
        controller.chatBox.setText(controller.chatBox.getText() + "\n" + message);
    }

}
