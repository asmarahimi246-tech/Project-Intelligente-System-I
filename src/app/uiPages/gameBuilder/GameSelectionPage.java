package app.uiPages.gameBuilder;

import java.util.ArrayList;
import java.util.Scanner;

import app.games.tictactoe.TicTacToeBuilder;
import app.games.tictactoe.TicTacToeGame;
import app.uiPages.MainPage;
import app.uiPages.context.Page;
import app.uiPages.context.PageManager;
import app.uiPages.context.widgets.Button;
import app.uiPages.context.widgets.OptionMenu;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;

/**
 * GameSelectionPage
 * 
 * An entry point for the game builder
 * 
 * Contains the ui for selecting a game
 * 
 * Everything from the game builder in seperate page
 * 
 * start
 * 1. game select
 * 2. game modes (local, online, tournament)
 * 3. player (options received from game)
 * 4. build and run game
 */
public class GameSelectionPage extends Page {
    @Override
    public String toString() {
        String toReturn = "game selection page";
        return toReturn;
    }

    @Override
    public void createSwingUi() {
        // make buttons
        ArrayList<Button> options = new ArrayList<Button>();

        // manager
        PageManager manager = PageManager.getInstance();

        // game builder button
        TicTacToeBuilder builder = new TicTacToeBuilder();
        Page modePage = new BuilderBasePage(new ModePage<TicTacToeGame,TicTacToeBuilder>(builder), this);
        Button modeButton = new Button(event -> {manager.setCurrentPage(modePage);}, "Tic Tac Toe");
        options.add(modeButton);

        // make menu
        OptionMenu menu = new OptionMenu(options, "=== Game Selection ===");
        add(menu);
    }

    @Override
    public void createCLI() {
        // print
        System.out.println("=== Game Selection ===");
        System.out.println("1. Tic Tac Toe");
        System.out.print("Enter your choice: ");

        // get input
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField field = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(1)
            .min(1)
            .build();
        int choice = field.getInput();

        // process input
        Page page = null;
        switch (choice) {
            case 1:
                // start building a tic tac toe game
                TicTacToeBuilder builder = new TicTacToeBuilder();

                //TODO: need the builder base page not this
                page = new BuilderBasePage(new ModePage<TicTacToeGame,TicTacToeBuilder>(builder), this);
                break;
        
            default:
                break;
        }

        // error handling
        if (page == null) {
            System.err.println("Page is null");
            System.err.println("Returning to main menu");
            page = new MainPage();
        }

        PageManager manager = PageManager.getInstance();
        manager.setCurrentPage(page);
    }
}
