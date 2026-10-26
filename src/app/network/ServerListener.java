package app.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BooleanSupplier;

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
    private final BlockingQueue<String> okQueue;
    private final BlockingQueue<String> svrQueue;
    private final BooleanSupplier clientClosing;
    private BufferedReader reader;
    private Socket socket;
    private String rawMessage;
    private final MessageHandler handler;
    private ServerMessage svrMessage;

    /**
     * Constructor
     * 
     * @param reader reader
     * @param okQueue okQueue
     * @param svrQueue scrQueue
     */
    public ServerListener(
            BufferedReader reader,
            BlockingQueue<String> okQueue,
            BlockingQueue<String> svrQueue,
            BooleanSupplier clientClosing) {

        this.reader = reader;
        this.okQueue = okQueue;
        this.svrQueue = svrQueue;
        this.clientClosing = clientClosing;
        this.handler = new MessageHandler();
    }

    /**    (non-Javadoc)
     * 
     * @see Runnable#run()
     */
    @Override
    public void run() {
        System.out.println("DEBUG: ServerListener started");

        try {
            while (!clientClosing.getAsBoolean()
                    && (rawMessage = reader.readLine()) != null) {

                System.out.println(
                        "DEBUG: Received from server: [" + rawMessage + "]"
                );

                // Ignore startup messages that aren't part of the protocol.
                if (!rawMessage.startsWith("OK")
                        && !rawMessage.startsWith("ERR")
                        && !rawMessage.startsWith("SVR")) {

                    System.out.println(
                            "DEBUG: Ignoring unrecognized server message: "
                                    + rawMessage
                    );
                    continue;
                }

                ServerMessage message = handler.handleMessage(rawMessage);

                switch (message.getType()) {
                    case OK -> {
                        okQueue.add(rawMessage);
                        System.out.println("DEBUG: Added to okQueue");
                    }
                    case ERR -> {
                        errorFlag = true;
                        System.err.println("DEBUG: Server error: " + rawMessage);
                    }
                    case SVR -> {
                        svrQueue.add(rawMessage);
                        System.out.println("DEBUG: Added to svrQueue");
                    }
                }
            }

            if (!clientClosing.getAsBoolean()) {
                System.err.println("DEBUG: Server closed the connection");
            }

        } catch (IOException e) {
            if (!clientClosing.getAsBoolean()) {
                System.err.println("DEBUG: Server connection lost");
                e.printStackTrace();
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(
                    "DEBUG: Failed to process message: [" + rawMessage + "]"
            );
            e.printStackTrace();
        }
    }

    /**
     * check for error
     * 
     * @return true if there is an error
     */
    public boolean isError() {
        return errorFlag;
    }

    /**
     * checks for ok in the queue
     *
     * @return the ok
     */
    public ServerMessage getNextSvrMessage() {
        try {
            return handler.handleMessage(svrQueue.take());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(
                    "Interrupted while waiting for server message", e
            );
        }
    }

    public ServerMessage getNextOkMessage() {
        try {
            return handler.handleMessage(okQueue.take());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(
                    "Interrupted while waiting for OK message", e
            );
        }
    }

    /**
     * Returns a svr message back to the queue
     */
    public void returnSvrMessage(String message) {
        throw new UnsupportedOperationException("Unimplemented method 'returnSvrMessage' in ServerListener");
    }
}