package app.games.reversi;

import java.util.Scanner;

import app.games.reversi.model.ReversiMove;
import app.uiPages.context.Page;
import app.util.Config;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;
import app.games.framework.components.Board;
import app.games.framework.game.Game;
import app.games.framework.game.GamePage;
import app.games.framework.player.Player;


/**
 * ReversiPage
 */
public class ReversiPage extends Page implements GamePage {
    private ReversiGame game;

    @Override
    public void createSwingUi() {
    }

    /**
     * Prints a board
     *
     * @param board board
     */
    public void printBoard(Board board) {
        String toPrint = "";

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Player owner = board.getByPos(row, col).getOwner();

                if (col != 0) {
                    toPrint += "|";
                }

                if (owner == null) {
                    int id = (row) * 8 + col + 1;

                    if (id < 10) {
                        toPrint += " " + id;
                    } else {
                        toPrint += "" + id;
                    }
                } else {
                    String symbol = game.getSymbol(owner);
                    toPrint += " " + symbol;
                }
            }
            toPrint += "\n";
        }
        System.out.println(toPrint);
    }

    /**
     * show result
     *
     * @param message the message to show
     */
    public void showResult(String message) {
        if (!Config.SWING_SUPPORTED) {
            System.out.println(message);
            Board board = game.model.getBoard();
            printBoard(board);
        }
    }

    @Override
    public void createCLI() {
        System.out.println();
        System.out.println("=== Reversi ===");
        System.out.println("the board looks like this");
        Board board = game.model.getBoard();
        printBoard(board);

        String name = game.model.getCurrentPlayer().getName();
        System.out.println("its your turn " + name);
    }

    /**
     * sets the game
     *
     * @param game
     */
    @Override
    public void setGame(Game game) {
        this.game = (ReversiGame) game;
    }

    /**
     * Requests a move from the page
     * 
     * @return the move
     */
    public ReversiMove requestMove() {

        if (!Config.SWING_SUPPORTED) {
            return cliInput();
        }
        throw new IllegalStateException("test");

//        SwingUtilities.invokeLater(() -> {
//            acceptingMoves = true;
//            refreshBoard();
//        });
//
//        try {
//            return moves.take();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            throw new IllegalStateException("Waiting for a move was interrupted", e);
//        }
    }

    /**
     * Gets input from the CLI
     * 
     * @return a reversiMove
     */
    private ReversiMove cliInput() {

        // get input
        System.out.println("give number (1-64)");
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField xfield = new CLIIntegerField.Builder(scanner, "Something went wrong.")
                .max(64)
                .min(1)
                .defaultInput(1)
                .build();
        int number = xfield.getInput();

        int index = number - 1;
        int row = index / 8;
        int col = index % 8;


        return new ReversiMove(row, col);
    }
}
