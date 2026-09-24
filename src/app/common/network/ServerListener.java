package app.common.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Client
 * 
 * 
 * AI usage:
 * Made with help from a lot of youtube videos and explenations from deepai
 */
public class ServerListener implements Runnable {
    private volatile boolean errorFlag = false;
    private final ConcurrentLinkedQueue<String> okQueue;
    private final ConcurrentLinkedQueue<String> svrQueue;
    private BufferedReader reader;

    /**
     * Constructor
     * 
     * @param reader reader
     * @param okQueue okQueue
     * @param svrQueue scrQueue
     */
    public ServerListener(BufferedReader reader, ConcurrentLinkedQueue<String> okQueue, ConcurrentLinkedQueue<String> svrQueue) {
        this.reader = reader;
        this.okQueue = okQueue;
        this.svrQueue = svrQueue;
    }

    /**    (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            while (true) {
                String message = reader.readLine(); // your method
                if (message.contains("ERR")) {
                    System.err.println("[error] " + message);
                    errorFlag = true;
                } else if (message.contains("OK")) {
                    okQueue.add(message);
                } else if (message.contains("SVR")) {
                    svrQueue.add(message);
                }
            }
        } catch (IOException e) {
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
    public String getNextOkMessage() {
        return okQueue.poll(); // removes and returns the head of the queue
    }

    /**
     * checks for server messages in the queue
     * 
     * @return the server message
     */
    public String getNextSvrMessage() {
        return svrQueue.poll(); // removes and returns the head of the queue
    }
}
