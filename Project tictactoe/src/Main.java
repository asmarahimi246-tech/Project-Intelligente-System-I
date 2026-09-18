import Framework.Game;
import Network.Client;
import java.io.IOException;
import Framework.ServerListener;

public class Main {
    public static void main(String[] args) throws IOException {
        String uniqueId = java.util.UUID.randomUUID().toString().substring(0, 5);
        String playername = "Player" + uniqueId;

        Client client = new Client();
        Game game = new Game(client);
        ServerListener listener = new ServerListener(client, game);

        client.sendCommand("login " + playername);
        client.sendCommand("subscribe tic-tac-toe");

        String message;
        while ((message = client.getReader().readLine()) != null) {
            listener.Commandhandler(message);
        }
    }
}
