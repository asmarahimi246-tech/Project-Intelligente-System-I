package games.kasia.app.client.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ServerProxy
 */
public class ServerProxy {
    private BufferedReader reader;
    private PrintWriter writer;

    public ServerProxy(Socket socket) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendCommand(String command) {
        writer.println(command);
    }

    public BufferedReader getReader() {
        return reader;
    }
    
}
