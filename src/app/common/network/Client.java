package app.common.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Client
 * 
 * 
 * AI usage:
 * Made with help from a lot of youtube videos and explenations from deepai
 */
public class Client {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private ServerListener listener;


    /**
     * Constructor
     * @param socket the socket
     * @throws IOException 
     */
    public Client(Socket socket) throws IOException {
        this.socket = socket;
        connectToServer();
    }

    /**
     * connects to a server
     */
    private void connectToServer() throws IOException{
        // make writers
        reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        writer = new PrintWriter(this.socket.getOutputStream(), true);

        // still dont exactly know how concurent linked queue works i do know that its a thread save queue
        // make server listen
        ConcurrentLinkedQueue<String> okQueue = new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<String> svrQueue = new ConcurrentLinkedQueue<>();
        this.listener = new ServerListener(reader, okQueue, svrQueue);

        //place it on a thread and start it
        Thread thread = new Thread(listener);
        thread.start();
    }

    /**
     * writes a command to the server
     * 
     * @param command the command
     */
    public void sendCommand(String command) {
        //write to server
        writer.println(command);
    }

    /**
     * checks if the client is ok or if theres an error
     * 
     * @return true if its ok
     * @throws IOException if there is an error
     */
    public boolean isOk() throws IOException{
        // wait for ok
        // by checking queue
        boolean ok = false;
        while (!ok) {
            if (this.listener.isError()) {
                throw new IOException("Error received from server");
            } else if (this.listener.getNextOkMessage() != null) {
                ok = true;
            }
        }

        return true;
    }
    
}