import Game.Games.TicTacToe.TicTacToeGame;
import Network.Client;
import Game.Players.Onlineplayer;
import Network.ServerListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        TicTacToeGame game = new TicTacToeGame(client);

        ServerListener listener = new ServerListener(client, game);

        Onlineplayer onlineplayer = new Onlineplayer(client);

        String message;
        while ((message = client.getReader().readLine()) != null) {
            listener.Commandhandler(message);
            System.out.println("SERVER: " + message);
        }
    }
}
