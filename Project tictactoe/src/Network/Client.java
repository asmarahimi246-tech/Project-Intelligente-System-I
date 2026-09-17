package Network;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client() throws IOException {
        socket = new Socket("localhost", 7789);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendCommand(String command) {
        writer.println(command);
    }

    public BufferedReader getReader() {
        System.out.println("test");
        return reader;
    }
}

