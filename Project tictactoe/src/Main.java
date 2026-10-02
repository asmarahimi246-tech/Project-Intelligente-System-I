import Framework.Game;
import Network.Client;
import Game.Players.Onlineplayer;
import Network.ServerListener;
import java.io.IOException;

public class Main {

    /**
     * Entry point of application
     * 
     * @param args args
     * @throws IOException IOException
     */
    public static void main(String[] args) throws IOException {
        // make a UUID for the player
        String uniqueID = java.util.UUID.randomUUID().toString().substring(0, 5);
        String playername = "Player" + uniqueID;

        // make a new client
        Client client = new Client();
        // make a new game
        Game game = new Game(client);
        // make a new serverListener
        ServerListener listener = new ServerListener(client, game);

        // login to the server and subscribe to the game
        client.sendCommand("login " + playername);
        client.sendCommand("subscribe tic-tac-toe");

        // infinitly reads from the server
        String message;
        while ((message = client.getReader().readLine()) != null) {
            listener.Commandhandler(message);
        }
    }
}
