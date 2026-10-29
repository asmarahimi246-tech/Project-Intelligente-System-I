package app.uiPages.gameBuilder;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingWorker;

import app.games.framework.game.Game;
import app.games.framework.game.GameBuilder;
import app.games.framework.game.GameMode;
import app.games.tictactoe.TicTacToeBuilder;
import app.games.tictactoe.TicTacToeGame;
import app.network.Client;
import app.uiPages.TournamentPage;
import app.uiPages.context.Page;
import app.uiPages.context.PageManager;
import app.uiPages.context.widgets.Button;
import app.uiPages.context.widgets.OptionMenu;
import app.util.Config;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;

/**
 * ModePage
 * 
 * This page allows users to choose a game mode
 * 
 * @param <G> is the game class
 * @param <B> is the builder class
 * 
 * The way you make this page is simmilar to making an arraylist
 * making an arraylist with Strings
 * ArrayList &lt; String &gt; strings = new ArrayList &lt; String &gt; ();
 * 
 * example for tic tac toe
 * G = TicTacToeGame
 * B = TicTacToeBuilder
 * 
 * in code:
 * TicTacToeBuilder builder = new TicTacToeBuilder();
 * Page page = new ModePage &lt; TicTacToeGame,TicTacToeBuilder &gt; (builder);
 * 
 * Sources:
 *      swingWorker: https://www.geeksforgeeks.org/java/swingworker-in-java/
 */
public class ModePage<G extends Game, B extends GameBuilder<G, B>> extends Page {
    private final B builder;

    @Override
    public String toString() {
        String toReturn = "mode page";
        return toReturn;
    }

    /**
     * Constructor needs builder to save choosen variables
     * 
     * @param builder the builder
     */
    public ModePage(B builder) {
        this.builder = builder;
    }

    @Override
    public void open() {
        super.open();
    }

    /**
     * Continues to the player page
     */
    private void continueToPlayerPage() {
        // G is the game class, B is the builder class
        // example for tic tac toe
        // G = TicTacToeGame
        // B = TicTacToeBuilder
        Page page = new PlayerPage<G,B>(builder);
        PageManager manager = PageManager.getInstance();
        manager.setCurrentPage(page);
    }

    /**
     * starts an online game
     * 
     * @param mode the online mode to start in
     */
    private void startOnlineMode(GameMode mode) {
        Socket socket;
        Client client;

        // make the socket and client
        try {
            if (Config.DEBUG) {
                socket = new Socket("localhost", Config.LOCALPORT);
            } else {
                socket = new Socket(Config.ONLINEIP, Config.ONLINEPORT);
            }
            client = new Client(socket);
        } catch (Exception e) {
            // TODO: handle exception
            return;
        }

        // use a worker thread for swing
        // to keep ui interactive while preventing bugs
        if (Config.SWING_SUPPORTED) {
            new SwingWorker<Boolean, Void>() {
                // the thing to do
                @Override
                protected Boolean doInBackground() throws Exception {
                    return client.login();
                }

                // executes when its done
                @Override
                protected void done() {
                    try {
                        builder.setClient(client);
                        continueToPlayerPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.execute();
        } else {
            client.login();
        }
    }

    @Override
    public void createSwingUi() {
        // make buttons
        ArrayList<Button> options = new ArrayList<Button>();

        // local mode button
        Button localButton = new Button(event -> {
            builder.setMode(GameMode.LOCAL); 
            continueToPlayerPage();}
            , "Local game");
        options.add(localButton);

        // online mode button
        Button onlineButton = new Button(event -> {
            builder.setMode(GameMode.ONLINE); 
            startOnlineMode(GameMode.ONLINE);}
            , "Online game");
        options.add(onlineButton);

        // tournament mode button
        Button tournamentButton = new Button(event -> {
            builder.setMode(GameMode.TOURNAMENT); 
            startOnlineMode(GameMode.TOURNAMENT);}
            , "Tournament");
        options.add(tournamentButton);

        // make menu
        OptionMenu menu = new OptionMenu(options, "=== " + builder.toString() + ": Mode Selection ===");
        add(menu);
    }

    @Override
    public void createCLI() {
        // print
        System.out.println("=== " + builder.toString() + ": Mode Selection ===");
        System.out.println("1. Local game");
        System.out.println("2. Online game");
        System.out.println("3. Tournament game");
        System.out.print("Enter your choice: ");

        // get input
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField field = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(3)
            .min(1)
            .build();
        int choice = field.getInput();

        // process input
        GameMode gameMode = null;
        switch (choice) {
            case 1:
                gameMode = GameMode.LOCAL;
                break;
            case 2:
                gameMode = GameMode.ONLINE;
                startOnlineMode(gameMode);
                break;
            case 3:
                gameMode = GameMode.TOURNAMENT;
                startOnlineMode(gameMode);
                break;
            default:
                return;
        }

        builder.setMode(gameMode);
        continueToPlayerPage();
    }

    @Override
    public void close() {
        // close the builder
        // builder.close();
    }
}