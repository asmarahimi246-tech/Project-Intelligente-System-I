import Network.Client;
import Network.ServerListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        ServerListener listener = new ServerListener(client);

        String message;
        while ((message = client.getReader().readLine()) != null) {
            listener.Commandhandler(message);
            System.out.println("SERVER: " + message);
        }
    }
}
