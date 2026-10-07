package app;
import app.games.tictactoe.TicTacToeGame;
import app.menus.GameMenu;
import app.network.Client;
import app.network.ServerListener;
import app.players.LocalPlayer;
import app.players.OnlinePlayer;

import java.io.IOException;

/**
 * <pre>
 * Entry point for the program.
 * Contains no application logic.
 *
 * Example:
 * &gt; Create a {@code GameApp} instance.
 * &gt; Call {@code app.run()}.
 * </pre>
 */
public class Main {
    /**
     * TODO: application logic in main
     * Concstructor.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        GameMenu menu = new GameMenu();

        // haalt de gekozen spelmodus op lokaal of online
        boolean online = menu.mainMenu();

        // haalt op of een player online wil spelen
        boolean aiMode = menu.menu(online);

        if (!online) {
            TicTacToeGame game = new TicTacToeGame(null);

            // start een lokaal spel op met de AI modus
            LocalPlayer localplayer = new LocalPlayer(game, aiMode);
            localplayer.play();
            return;
        }

        Client client = new Client();
        ServerListener listener = new ServerListener(client);
        TicTacToeGame game = new TicTacToeGame(listener);

        listener.setGame(game);
        listener.aiMode(aiMode);
        OnlinePlayer onlineplayer = new OnlinePlayer(client);

        String message;
        while ((message = client.getReader().readLine()) != null) {
            listener.commandhandler(message);
        }
    }
}