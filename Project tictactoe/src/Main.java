import Game.Games.TicTacToe.TicTacToeGame;
import Network.Client;
import Game.Players.Onlineplayer;
import Network.ServerListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean useAI = false;
        Client client = new Client();
        ServerListener listener = new ServerListener(client);
        TicTacToeGame game = new TicTacToeGame(listener);

        listener.setGame(game);
        Onlineplayer onlineplayer = new Onlineplayer(client);

        String message;
        while ((message = client.getReader().readLine()) != null) {
            listener.Commandhandler(message);
            System.out.println("SERVER: " + message);
        }
    }
}
