package app.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <pre>
 * &gt; Handles the connection to the server.
 * &gt; Sends and receives network data.
 * &gt; Could be hard-coded, but a finite-state-machine approach is preferable.
 *
 * Example:
 * &gt; connect()    --> opens a socket
 * &gt; send()       --> sends a message
 * &gt; disconnect() --> closes the connection
 * &gt; receive()    --> receives data through a ServerListener
 * </pre>
 */
public class Client {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

        /**
     * TODO: dit moet je op een andere thread doen 
     * om te voorkomen dat de applicatie vast loopt
     * 
     * Constructor
     * @throws IOException
     */
    public Client() throws IOException {
        socket = new Socket("localhost", 7789);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * TODO:
     * @param command
     */
    public void sendCommand(String command) {
        writer.println(command);
    }

    /**
     * TODO:
     * @return
     */
    public BufferedReader getReader() {
        return reader;
    }

    /**
     * sluit de socket
     * @throws IOException 
     */
    public void close() throws IOException {
        this.socket.close();
    }
}
