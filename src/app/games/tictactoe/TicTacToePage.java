package app.games.tictactoe;

import java.util.Scanner;

import javax.swing.JLabel;

import app.games.tictactoe.model.Board;
import app.games.tictactoe.model.TicTacToeMove;
import app.pages.context.Page;
import app.util.Config;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;

/**
 * <pre>
 * &gt; displays the game to the user
 * &gt; prints to the terminal for now
 *
 * EX:
 * &gt; printBoard() --&gt; gets the board state from the game and prints it
 * </pre>
 */
public class TicTacToePage extends Page {
    private TicTacToeGame game;

    /**
     * TODO:
     */
    public void printBoardScore() {
        System.out.println("Board:");
    }

    /**
     * TODO:
     */
    public void printInvalidMove() {
        System.out.println("Die plek is al bezet doe een andere move!");
    }

    @Override
    public void createSwingUi() {
        // TODO: add ui
        throw new UnsupportedOperationException("UI not implemented");
    }

    @Override
    public void createCLI() {
        System.out.println();
        System.out.println("=== Tic Tac Toe ===");
        System.out.println("the board looks like this");
        Board board = game.model.getBoard();
        System.out.println(board.toString());

        String name = game.model.getCurrentPlayer().getName();

        if (Config.X_AND_O) {
            name = name.substring(1);
        }

        System.out.println("its your turn " + name);
    }

    /**
     * Sets the game
     * @param game the game
     */
    public void setGame(TicTacToeGame game) {
        this.game = game;
    }

    /**
     * Requests a move
     * 
     * @return the move
     */
    public TicTacToeMove requestMove() {
        TicTacToeMove toReturn = null;

        if (Config.SWING_SUPPORTED) {

        } else {
            toReturn = cliInput();
        }

        return toReturn;
    }

    /**
     * Gets input from the cli
     * 
     * @return a move
     */
    private TicTacToeMove cliInput() {

        // get input
        System.out.println("give row v (1-3)");
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField xfield = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(3)
            .min(1)
            .defaultInput(2)
            .build();
        int row = xfield.getInput() - 1;

        System.out.println("now collom > (1-3)");
        CLIIntegerField yfield = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(3)
            .min(1)
            .defaultInput(2)
            .build();
        int collomn = yfield.getInput() - 1;

        return new TicTacToeMove(row, collomn);
    }
}
