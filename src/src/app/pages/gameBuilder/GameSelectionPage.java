package src.app.pages.gameBuilder;

import app.games.tictactoe.TicTacToeBuilder;
import app.games.tictactoe.TicTacToeGame;
import app.pages.MainPage;
import app.pages.context.Page;
import app.pages.context.PageManager;
import app.pages.gameBuilder.BuilderBasePage;
import app.pages.gameBuilder.ModePage;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;

import java.util.Scanner;

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
    public void createSwingUi() {
        // TODO: add ui
        throw new UnsupportedOperationException("Not implemented");
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