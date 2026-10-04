package network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
        return reader;
    }
}

/*
> handles connection to server
> send/recieve network data
> can be hard coded???

EX:
> connect() --> open socket
> send() --> send message
> disconnect() --> close socket
> receive() --> with ServerListener
*/