package app.pages.gameBuilder;

import java.util.Scanner;

import app.games.tictactoe.TicTacToeBuilder;
import app.games.tictactoe.TicTacToeGame;
import app.pages.MainPage;
import app.pages.context.Page;
import app.pages.context.PageManager;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;

/**
 * GameBuilderPage
 * 
 * The Game builder page
 */
public class GameBuilderPage extends Page {

    @Override
    public void open() {
        // print
        System.out.println("=== Game Builder ===");
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
                page = new ModePage<TicTacToeGame,TicTacToeBuilder>(builder);
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
