package app.pages.gameBuilder;

import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;

import app.games.framework.Game;
import app.games.framework.GameBuilder;
import app.games.framework.Player;
import app.pages.GamePage;
import app.pages.context.Page;
import app.pages.context.PageManager;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;
import app.games.framework.PlayerOption;

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
        this.builder = builder;
    }

    @Override
    public void open() {
        // add the default players for the selected game mode
        builder.addDefaultPlayers();
        int playersLeft = builder.getPlayersToAdd() + 1;

        // loop until all nessacary players are added
        while (builder.getPlayersToAdd() > 0) {
            // get choices
            List<PlayerOption<? extends Player>> options = builder.getPlayerOptions();

            // print
            System.out.println("=== " + builder.toString() + ": Player " + (playersLeft - builder.getPlayersToAdd()) + " Selection ===");
            for (int index = 1; index <= options.size(); index ++) {
                System.out.println(index + ". " + options.get(index - 1).label());
            }

            // get input
            Scanner scanner = ScannerSingleton.getInstance();
            CLIIntegerField field = new CLIIntegerField.Builder(scanner, "Something went wrong.")
                .max(options.size())
                .min(1)
                .build();
            int choice = field.getInput();

            // proccess input
            builder.addPlayer(options.get(choice - 1));
        }

        // game page
        PageManager manager = PageManager.getInstance();
        Page page = new GamePage<G,B>(builder);
        manager.setCurrentPage(page);
    }

    @Override
    public void createSwingUi() {
        // TODO: add ui
        add(new JLabel("Ui not implemented"));
    }
}