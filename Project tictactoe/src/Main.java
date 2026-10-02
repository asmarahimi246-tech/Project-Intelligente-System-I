import Framework.Game;
import Network.Client;
import Game.Players.Onlineplayer;
import Network.ServerListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        ServerListener listener = new ServerListener(client);
        TicTacToeGame game = new TicTacToeGame(listener);

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
