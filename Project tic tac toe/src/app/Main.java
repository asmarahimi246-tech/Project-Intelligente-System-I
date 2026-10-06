package app;

import app.Game.Games.TicTacToe.TicTacToeGame;
import app.Game.Players.Localplayer;
import app.Game.Players.Onlineplayer;
import app.Menus.GameMenu;
import app.Network.Client;
import app.Network.ServerListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameMenu menu = new GameMenu();

        // haalt de gekozen spelmodus op lokaal of online
        boolean online = menu.mainMenu();

        // haalt op of een player online wil spelen
        boolean aiMode = menu.menu(online);

        if (!online) {
            TicTacToeGame game = new TicTacToeGame(null);

            // start een lokaal spel op met de AI modus
            Localplayer localplayer = new Localplayer(game, aiMode);
            localplayer.play();
            return;
        }

        Client client = new Client();
        ServerListener listener = new ServerListener(client);
        TicTacToeGame game = new TicTacToeGame(listener);

        listener.setGame(game);
        listener.AiMode(aiMode);
        Onlineplayer onlineplayer = new Onlineplayer(client);

        String message;
        while ((message = client.getReader().readLine()) != null) {
            listener.Commandhandler(message);
        }
    }
}
