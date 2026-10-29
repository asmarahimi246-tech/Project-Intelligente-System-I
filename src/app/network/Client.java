package app.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import app.util.Logger;
import app.util.NameGenerator;

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
    private String name = null;


    /**
     * Constructor
     * 
     * Makes a new client
     * 
     * @param socket the socket
     * @throws IOException an exception
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
        LinkedBlockingQueue<ServerMessage> okQueue = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<ServerMessage> svrList = new LinkedBlockingQueue<>();
        this.listener = new ServerListener(reader, okQueue, svrList, () -> closing.get());

        //place it on a thread and start it
        Thread thread = new Thread(listener);
        thread.start();
    }

    /**
     * writes a command to the server
     * 
     * @param command the command
     * 
     * @return true if succesfull
     */
    public boolean sendCommand(String command) {
        Logger.logDebug("sending " + command);
        writer.println(command);
        return isOk();
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
            if (listener.isError()) {
                System.err.println("Error received from server");
                return false;
            } else
                try {
                    if (listener.okQueue.take() != null) {
                        ok = true;
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return ok;
    }

    /**
     * gets the next svr message by the subtype and optional addon
     * 
     * @param subtype the message subtype
     * @param addon the message addon
     * @return the message
     */
    public ServerMessage getSvrMessageBySubTypeAndOptionalAddon(MessageSubtype subtype, MessageAddon addon) {
        ServerMessage toReturn = null;

        while (toReturn == null) {
            // check for error
            if (listener.isError()) {
                System.err.println("Error received from server");
                return toReturn;
            }

            // loop throug all messages in the list
            for (ServerMessage message : listener.svrQueue) {

                // check if the subtype is correct
                if (message.getSubtype() == subtype) {
                    // check for addon
                    if (message.getAddon() == addon || addon == null) {
                        toReturn = message;
                        listener.svrQueue.remove(toReturn);
                        break;
                    }
                }
            }
        }

        return toReturn;
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

    /**
     * Generats a unique name
     */
    public void generateUniqueName() {
        Logger.logDebug("get playerlist");
        sendCommand("get playerlist");
        Logger.logDebug("playerlist");
        ServerMessage msg = getSvrMessageBySubTypeAndOptionalAddon(MessageSubtype.PLAYERLIST, null);
        ArrayList<String> playerlist = msg.getListDetails();

        NameGenerator nameGenerator = new NameGenerator();
        name = nameGenerator.getName();

        // check if the random name is taken
        // if so, generate a new one
        // if not the name is unique and can be returned
        while (playerlist.contains(name)) {
            name = nameGenerator.getName();
        }
    }

    /**
     * Logs into the server
     * 
     * @return true if succesfull
     */
    public boolean login() {
        Logger.logDebug("generating name");
        generateUniqueName();
        Logger.logDebug("login client");
        sendCommand("login " + name);
        Logger.logDebug("login succes");
        return true;
    }

    /**
     * Gets the name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
}