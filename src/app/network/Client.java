package app.network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * > handles connection to server
 * > send/recieve network data
 * > can be hard coded???
 * > preferable aproach is finite state machine
 * 
 * EX:
 * > connect() --> open socket
 * > send() --> send message
 * > disconnect() --> close socket
 * > receive() --> with ServerListener
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
