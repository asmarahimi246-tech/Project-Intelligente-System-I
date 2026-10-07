package app;
import app.games.tictactoe.TicTacToeController;
import app.network.Client;
import app.players.Onlineplayer;
import app.network.ServerListener;
import java.io.IOException;

/**
 * > entry point of entire program
 * > !! NO APPLICATION LOGIC HERE !!
 * 
 * EX:
 * > GameApp app = new GameApp
 * > app.run()
 */
public class Main {
    /**
     * TODO: application logic in main
     * Concstructor.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // why is there application logic here?
        Client client = new Client();
        ServerListener listener = new ServerListener(client);
        TicTacToeController game = new TicTacToeController(listener);

        listener.setGame(game);
        Onlineplayer onlineplayer = new Onlineplayer(client);

        String message;
        while ((message = client.getReader().readLine()) != null) {
            listener.commandhandler(message);
        }
    }
}