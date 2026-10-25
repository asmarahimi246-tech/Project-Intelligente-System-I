package app.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client implements AutoCloseable {

    private final Socket socket;

    private final AtomicBoolean closing = new AtomicBoolean(false);
    private final AtomicBoolean connectionEnded =
            new AtomicBoolean(false);

    // Raw lines read from the socket.
    private final BlockingQueue<String> rawQueue =
            new LinkedBlockingQueue<>();

    // OK and ERR command responses.
    private final BlockingQueue<ServerMessage> responseQueue =
            new LinkedBlockingQueue<>();

    // Asynchronous SVR events.
    private final BlockingDeque<ServerMessage> svrQueue =
            new LinkedBlockingDeque<>();

    private final MessageHandler handler = new MessageHandler();

    private final BufferedReader reader;
    private final PrintWriter writer;

    // Only one command-response exchange at a time.
    private final Object commandLock = new Object();

    private final Thread listenerThread;
    private final Thread dispatcherThread;

    private volatile ServerMessage lastError;

    public Client(Socket socket) throws IOException {
        this.socket = Objects.requireNonNull(socket);

        reader = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream(),
                        StandardCharsets.UTF_8));

        writer = new PrintWriter(
                socket.getOutputStream(), true);

        ServerListener listener =
                new ServerListener(reader, rawQueue);

        listenerThread = new Thread(
                listener, "server-listener");

        dispatcherThread = new Thread(
                this::dispatchMessages, "message-dispatcher");

        listenerThread.start();
        dispatcherThread.start();
    }

    /**
     * Reads raw lines from rawQueue, parses them and routes
     * messages to the appropriate queue.
     */
    private void dispatchMessages() {
        try {
            while (true) {
                String raw = rawQueue.take();

                if (ServerListener.END_OF_STREAM.equals(raw)) {
                    break;
                }

                try {
                    ServerMessage message =
                            handler.handleMessage(raw);

                    if (message == null) {
                        System.err.println(
                                "Parser returned null for: " + raw);
                        continue;
                    }

                    switch (message.getType()) {
                        case OK -> responseQueue.put(message);

                        case ERR -> {
                            lastError = message;
                            responseQueue.put(message);
                        }

                        case SVR -> svrQueue.putLast(message);
                    }

                } catch (RuntimeException e) {
                    System.err.println(
                            "Could not parse server message: "
                                    + raw + " (" + e.getMessage() + ")");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            connectionEnded.set(true);
        }
    }

    /**
     * Sends one command and waits for its corresponding OK or ERR.
     *
     * Only one command-response exchange can be active at a time.
     *
     * @param command command to send
     * @param timeout maximum time to wait
     * @param unit timeout unit
     * @return the OK or ERR response
     */
    public ServerMessage sendCommandAndWait(
            String command,
            long timeout,
            TimeUnit unit)
            throws IOException, InterruptedException {

        Objects.requireNonNull(command);
        Objects.requireNonNull(unit);

        if (command.isBlank()
                || command.contains("\n")
                || command.contains("\r")) {
            throw new IllegalArgumentException(
                    "Command must be a non-empty single line");
        }

        synchronized (commandLock) {
            if (closing.get()
                    || connectionEnded.get()
                    || socket.isClosed()
                    || !socket.isConnected()) {
                throw new IOException("Client is not connected");
            }

            // Send the command.
            writer.println(command);

            if (writer.checkError()) {
                throw new IOException(
                        "Failed to send command: " + command);
            }

            // The dispatcher routes SVR events elsewhere, so
            // this queue contains only OK and ERR responses.
            ServerMessage response =
                    responseQueue.poll(timeout, unit);

            if (response == null) {
                throw new IOException(
                        "Timed out waiting for response to: "
                                + command);
            }

            return response;
        }
    }

    /**
     * timeout makes it less likely that a piece of code
     * sends multiple commands without collecting the response first.
     */
    public ServerMessage sendCommandAndWait(String command)
            throws IOException, InterruptedException {

        return sendCommandAndWait(
                command, 2, TimeUnit.SECONDS);
    }

    /**
     * Waits for the next SVR event.
     *
     * Throws IllegalStateException if the connection has ended
     * and there are no remaining queued events.
     */
    public ServerMessage getNextSvrMessage()
            throws InterruptedException {

        while (true) {
            ServerMessage message =
                    svrQueue.pollFirst(200, TimeUnit.MILLISECONDS);

            if (message != null) {
                return message;
            }

            if (connectionEnded.get()) {
                throw new IllegalStateException(
                        "Connection ended; no more server events");
            }
        }
    }

    public ServerMessage getLastError() {
        return lastError;
    }

    public boolean isClosed() {
        return closing.get() || connectionEnded.get();
    }

    /**
     * Closes the socket and waits briefly for worker threads
     * to finish.
     */
    @Override
    public void close() {
        if (!closing.compareAndSet(false, true)) {
            return;
        }

        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(
                    "Error closing socket: " + e.getMessage());
        }

        // Closing the socket should unblock readLine().
        joinThread(listenerThread);
        joinThread(dispatcherThread);
    }

    private void joinThread(Thread thread) {
        if (Thread.currentThread() == thread) {
            return;
        }

        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}