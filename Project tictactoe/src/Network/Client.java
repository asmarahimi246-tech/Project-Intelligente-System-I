package Network;

import java.io.*;
import java.net.*;

public class client {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public client() throws IOException {
        socket = new Socket("localhost", 7789);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }
}
