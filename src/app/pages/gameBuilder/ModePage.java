package app.pages.gameBuilder;

import java.util.ArrayList;
import java.util.Scanner;

import app.games.framework.game.Game;
import app.games.framework.game.GameBuilder;
import app.games.framework.game.GameMode;
import app.games.tictactoe.TicTacToeBuilder;
import app.games.tictactoe.TicTacToeGame;
import app.pages.context.Page;
import app.pages.context.PageManager;
import app.pages.context.widgets.Button;
import app.pages.context.widgets.OptionMenu;
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

        if (!Config.SWING_SUPPORTED) {
            continueToPlayerPage();
        }
    }

    /**
     * Continues to the player page
     */
    private void continueToPlayerPage() {
        // G is the game class, B is the builder class
        // example for tic tac toe
        // G = TicTacToeGame
        // B = TicTacToeBuilder
        Page page = new BuilderBasePage(new PlayerPage<G,B>(builder), this);
        PageManager manager = PageManager.getInstance();
        manager.setCurrentPage(page);
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
            continueToPlayerPage();}
            , "Online game");
        options.add(onlineButton);

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
        System.out.print("Enter your choice: ");

        // get input
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField field = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(2)
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
                break;
        
            default:
                break;
        }
        builder.setMode(gameMode);
    }
}