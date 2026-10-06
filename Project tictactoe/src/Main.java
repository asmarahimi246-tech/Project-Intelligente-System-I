import games.tictactoe.TicTacToeController;
import network.Client;
import players.Onlineplayer;
import network.ServerListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        ServerListener listener = new ServerListener(client);
        TicTacToeController game = new TicTacToeController(listener);

        listener.setGame(game);
        Onlineplayer onlineplayer = new Onlineplayer(client);

        String message;
        while ((message = client.receive().readLine()) != null) {
            listener.Commandhandler(message);
        }
    }
}

/*
> entry point of entire program
> !! NO APPLICATION LOGIC HERE !!

EX:
> GameApp app = new GameApp
> app.run()
*/