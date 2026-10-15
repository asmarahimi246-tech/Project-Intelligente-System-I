package app.pages.gameBuilder;

import java.util.Scanner;

import javax.swing.JLabel;

import app.games.framework.Game;
import app.games.framework.GameBuilder;
import app.pages.context.Page;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;

/**
 * PlayerPage
 * 
 * This page allows users to add players to a game
 * 
 * @param <G> is the game class
 * @param <B> is the builder class
 * 
 * The way you make this page is simmilar to making an arraylist
 * making an arraylist with Strings
 * ArrayList &lt; String &gt;  strings = new ArrayList &lt; String &gt; ();
 * 
 * example for tic tac toe
 * G = TicTacToeGame
 * B = TicTacToeBuilder
 * 
 * in code:
 * TicTacToeBuilder builder = new TicTacToeBuilder();
 * Page page = new PlayerPage &lt; TicTacToeGame,TicTacToeBuilder &gt; (builder);
 */
public class PlayerPage<G extends Game, B extends GameBuilder<G, B>> extends Page {
    private final B builder;

    @Override
    public String toString() {
        String toReturn = "player page";
        return toReturn;
    }

    /**
     * Constructor needs builder to save choosen variables
     * 
     * @param builder the builder
     */
    public PlayerPage(B builder) {
        super();
        this.builder = builder;
        add(new JLabel(toString()));
    }

    public void selectMode(String mode) {
        builder.setMode(mode);
    }

    @Override
    public void open() {
        // print
        System.out.println("=== " + builder.toString() + ": Player Selection ===");


        builder.printOptions();

        // get input
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField field = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(2)
            .min(1)
            .build();
        int choice = field.getInput();

        // process input
        String gameMode = null;
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
        builder.setMode(gameMode);
        
        System.out.println(gameMode);
    }
}