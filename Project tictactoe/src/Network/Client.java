package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client
 */
public class Client {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    /**
     * A constructor
     * makes a Client
     *
     * @throws IOException when something goes wrong with the socket
     */
    public Client() throws IOException {
        socket = new Socket("localhost", 7789);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * Send a command to the server
     *
     * @param command the command to send
     */
    public void sendCommand(String command) {
        writer.println(command);
    }

    /**
     * Gets the reader that is used to read from the server
     *
     * @return the reader
     */
    public BufferedReader getReader() {
        return reader;
    }
}
