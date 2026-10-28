package app.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BooleanSupplier;

import app.util.Logger;

/**
 * Client
 * 
 * 
 * AI usage:
 * Made with help from a lot of youtube videos and explenations from deepai
 * 
 * 
 * TODO: better sources
 */
public class ServerListener implements Runnable {
    private volatile boolean errorFlag = false;
    //TODO: wait for ok
    public List<ServerMessage> okList;
    public List<ServerMessage> svrList;
    private final BooleanSupplier clientClosing;
    private BufferedReader reader;
    private Socket socket;
    private String rawMessage;
    private final MessageHandler handler;

    /**
     * A server listener listens to the server and sorts answers into queues
     * 
     * @param reader reader
     * @param okQueue okQueue
     * @param svrList svrList
     * @param clientClosing if the client us closing
     */
    public ServerListener(BufferedReader reader, List<ServerMessage> okList, List<ServerMessage> svrList, BooleanSupplier clientClosing) {
        this.reader = reader;
        this.okList = okList;
        this.svrList = svrList;
        this.clientClosing = clientClosing;
        this.handler = new MessageHandler();
    }

    /**    (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        Logger.logDebug("ServerListener started");

        try {
            while (!clientClosing.getAsBoolean() && (rawMessage = reader.readLine()) != null) {
                // Ignore startup messages that aren't part of the protocol.
                if (!rawMessage.startsWith("OK")
                        && !rawMessage.startsWith("ERR")
                        && !rawMessage.startsWith("SVR")) {

                    Logger.logDebug("Ignoring unrecognized server message: " + rawMessage);
                    continue;
                }

                // get a message handler
                ServerMessage message = handler.handleMessage(rawMessage);

                // sort by type
                if (message.getType() == MessageType.ERR) {
                    System.err.println("[error] " + message);
                    errorFlag = true;
                } else if (message.getType() == MessageType.OK) {
                    okList.add(message);
                } else if (message.getType() == MessageType.SVR) {
                    svrList.add(message);
                }
            }
        } catch (IOException e) {
            if (!clientClosing.getAsBoolean()) {
                Logger.logDebug("Server connection lost");
                e.printStackTrace();
            }
        }

        Logger.logDebug("CLosing server the connection");
    }

    /**
     * check for error
     * 
     * @return true if there is an error
     */
    public boolean isError() {
        return errorFlag;
    }
}