package app.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import app.util.Config;
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
    private final Object messageMonitor = new Object();

    /**
     * Subscribes to the game
     * 
     * @param game the name of the game to subscribe to
     */
    public void subscribe(String game) {
        sendCommand("subscribe " + game);
    }


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
        this.listener = new ServerListener(reader, okQueue, svrList, () -> closing.get(), messageMonitor);

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
        try {
            if (listener.isError()) {
                System.err.println("Error received from server");
                throw new InterruptedException();
            }
            if (listener.okQueue.poll(Config.DEFAULT_TIMOUT, TimeUnit.MILLISECONDS) != null) {
                ok = true;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ok;
        }

        return ok;
    }

    /**
     * gets the next svr message by the subtype and optional addon
     * 
     * @param subtype the message subtype
     * @param addon the message addon
     * @param timeout max amount of time to wait in miliseconds
     * @return the message
     */
    public ServerMessage getSvrMessageBySubTypeAndOptionalAddon(MessageSubtype subtype, MessageAddon addon, long timeout) {
        ServerMessage toReturn = null;

        long deadline = System.currentTimeMillis() + timeout;

        try {
            if (listener.isError()) {
                System.err.println("Error received from server");
                throw new InterruptedException();
            }
            
            synchronized (messageMonitor) {
                while (!closing.get() && toReturn == null) {
                    if (System.currentTimeMillis() >= deadline) {
                        throw new InterruptedException();
                    }

                    for (ServerMessage message : listener.svrQueue) {
                        // check if the subtype is correct
                        if (message.getSubtype() == subtype) {
                            // check for addon
                            if (message.getAddon() == addon || addon == null) {
                                toReturn = message;
                                listener.svrQueue.remove(message);
                                return toReturn;
                            }
                        }
                    }

                    Long remaining = deadline - System.currentTimeMillis();

                    // wait for the listener to receive a message
                    messageMonitor.wait(remaining);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return toReturn;
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
            sendCommand("bye");
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
        ServerMessage msg = getSvrMessageBySubTypeAndOptionalAddon(MessageSubtype.PLAYERLIST, null, Integer.MAX_VALUE);
        ArrayList<String> playerlist = msg.getListDetails();

        Logger.logDebug("wut");

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