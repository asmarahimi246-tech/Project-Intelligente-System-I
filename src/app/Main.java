package app;
import app.games.tictactoe.TicTacToeGame;
import app.network.Client;
import app.network.ServerListener;
import app.pages.GameMenu;
import app.pages.MainPage;
import app.pages.context.PageManager;
import app.pages.context.Page;
import app.players.LocalPlayer;
import app.players.OnlinePlayer;
import app.util.ScannerSingleton;

import java.io.IOException;
import java.util.Scanner;

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
        // new code v
        // set a start page
        PageManager manager = PageManager.getInstance();
        Page page = new MainPage();
        manager.setCurrentPage(page);

        // make an app and run it
        App app = new App();
        app.run();

        // close the scanner
        ScannerSingleton.closeInstance();
    }
}