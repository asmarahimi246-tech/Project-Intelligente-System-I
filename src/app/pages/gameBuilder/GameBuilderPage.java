package app.pages.gameBuilder;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.games.players.Localplayer;
import app.games.players.Onlineplayer;
import app.games.tictactoe.TicTacToeGame;
import app.network.Client;
import app.network.ServerListener;
import app.pages.MainPage;
import app.pages.context.Page;
import app.pages.context.PageManager;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;

/**
 * GameBuilderPage
 * 
 * Contains the ui for building a game
 */
public class GameBuilderPage extends Page {
    private String gameMode;
    private String oponenent;

    @Override
    public String toString() {
        String toReturn = "game builder page";
        return toReturn;
    }
    /**
     * Constructor
     */
    public GameBuilderPage () {
        super();
        add(new JLabel(toString()));
    }

    /**
     * TODO: use builder
     */
    @Override
    public void open() {
        modeSubMenu();
        aiSubMenu();

        //temp to test if game runs with menus
        //will be replaced with builder
        boolean aiMode = false;
        if (oponenent == "ai") {
            aiMode = true;
        }


        if (gameMode == "local") {
            TicTacToeGame game = new TicTacToeGame(null);

            // start een lokaal spel op met de AI modus
            Localplayer localplayer = new Localplayer(game, aiMode);
            localplayer.play();
        } else {
            Client client = null;
            try {
                client = new Client();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ServerListener listener = new ServerListener(client);
            TicTacToeGame game = new TicTacToeGame(listener);

            listener.setGame(game);
            listener.aiMode(aiMode);
            try {
                Onlineplayer onlineplayer = new Onlineplayer(client);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String message;
            try {
                while ((message = client.getReader().readLine()) != null) {
                    listener.commandhandler(message);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Page page = new MainPage();
        PageManager manager = PageManager.getInstance();
        manager.setCurrentPage(page);
    }

    /**
     * Submenu for choosing the game mode
     */
    private void modeSubMenu() {
        System.out.println("=== Tic Tac Toe ===");
        System.out.println("1. Local game");
        System.out.println("2. Online game");
        System.out.print("Enter your choice: ");

        // get input
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField field = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(2)
            .min(1)
            .build();
        int choice = field.getInput();

        // process input
        switch (choice) {
            case 1:
                gameMode = "local";
                break;
            case 2:
                gameMode = "online";
                break;
        
            default:
                break;
        }

    }
    
    /**
     * Submenu for choosing an opponent
     */
    private void aiSubMenu() {
        // print
        switch (gameMode) {
            case "online":
                System.out.println("=== Online Game ===");
                break;
            
            case "local":
                System.out.println("=== Local Game ===");
                break;
        
            case "tournament":
                System.out.println("=== Tournament Game ===");
                break;
        
            default:
                break;
        }

        System.out.println("1. Player");
        System.out.println("2. AI");
        System.out.print("Enter your choice: ");

        // get input
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField field = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(2)
            .min(1)
            .build();
        int choice = field.getInput();

        // process input
        switch (choice) {
            case 1:
                oponenent = "player";
                break;
            case 2:
                oponenent = "ai";
                break;
        
            default:
                break;
        }
    }
}
