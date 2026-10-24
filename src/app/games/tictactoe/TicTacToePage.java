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
        Board board = game.model.getBoard();
        System.out.println(board.toString());
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
        System.out.println("give row");
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField xfield = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(3)
            .min(1)
            .build();
        int row = xfield.getInput();

        System.out.println("now collom");
        CLIIntegerField yfield = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(3)
            .min(1)
            .build();
        int collomn = yfield.getInput();

        return new TicTacToeMove(row, collomn);
    }
}
