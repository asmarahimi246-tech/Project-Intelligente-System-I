package app.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;


public class ServerListener implements Runnable {

    /**
     * string to tell the dispatcher it reached the end of the stream
     */
    public static final String END_OF_STREAM =
            "\u0000CLIENT_END_OF_STREAM\u0000";

    private final BufferedReader reader;
    private final BlockingQueue<String> rawQueue;

    public ServerListener(
            BufferedReader reader,
            BlockingQueue<String> rawQueue) {
        this.reader = reader;
        this.rawQueue = rawQueue;
    }

    @Override
    public void run() {
        try {
            String message;

            while ((message = reader.readLine()) != null) {
                rawQueue.put(message);
            }
        } catch (IOException e) {
            System.err.println(
                    "Server connection lost: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            rawQueue.offer(END_OF_STREAM);
        }
    }
}