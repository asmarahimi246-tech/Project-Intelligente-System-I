package src.app.network;

import app.network.ServerListener;
import app.network.ServerMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Client
 * 
 * AI usage:
 * Made with help from a lot of youtube videos and explanations from deepai
 * 
 * TODO: better sources
 */
public class Client {
    private final AtomicBoolean closing = new AtomicBoolean(false);
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private ServerListener listener;


    /**
     * Constructor
     * 
     * Makes a new client
     * 
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
        writer = new PrintWriter(this.socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

        // still dont exactly know how concurent linked queue works i do know that its a thread save queue
        // make server listen with a lambda function
        BlockingQueue<String> okQueue = new LinkedBlockingQueue<>();
        BlockingQueue<String> svrQueue = new LinkedBlockingQueue<>();
        this.listener = new ServerListener(reader, okQueue, svrQueue, () -> closing.get());

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
     */
    public boolean isOk(){
        // wait for ok
        // by checking queue
        boolean ok = false;
        while (!ok) {
            if (this.listener.isError()) {
                System.err.println("Error received from server");
            } else if (this.listener.getNextOkMessage() != null) {
                ok = true;
            }
        }

        return true;
    }

    /**
     * gets the next svr message
     * 
     * @return the message
     */
    public ServerMessage getNextSvrMessage() {
        return listener.getNextSvrMessage();
//        throw new UnsupportedOperationException("Unimplemented method 'getNextSvrMessage' in Client");
    }

    /**
     * Returns a svr message back to the queue
     */
    public void returnSvrMessage(ServerMessage message) {
        listener.returnSvrMessage(null);
//        throw new UnsupportedOperationException("Unimplemented method 'returnSvrMessage'");
    }

    /**
     * closes the socket
     * 
     * TODO: error handling
     */
    public void close() {
        try {
            // this should also automaticly closes the listener
            closing.set(true);
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}