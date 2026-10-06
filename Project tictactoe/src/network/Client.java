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
    private ServerListener listener;

    public Client() {
        try {
            socket = new Socket("localhost", 7789);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }  catch (IOException e) {
            e.printStackTrace(); // TODO: better loging
        }
    }

    // send message to server
    public void send(String command) {
        writer.println(command);
    }

    // read message from server
    public BufferedReader receive() {
        return reader;
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace(); // TODO: better loging
        }
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