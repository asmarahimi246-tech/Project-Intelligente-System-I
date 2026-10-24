package app.games.tictactoe;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import app.games.tictactoe.model.Board;
import app.games.tictactoe.model.TicTacToeMove;
import app.pages.context.Page;
import app.util.Config;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;
import app.games.framework.player.Player;

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
    private final JButton[][] buttons = new JButton[3][3];
    private final BlockingQueue<TicTacToeMove> moves =
            new ArrayBlockingQueue<>(1);

    private boolean acceptingMoves = false;

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
        removeAll();
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 120));

            final int row = i / 3;
            final int col = i % 3;

            buttons[row][col] = button;

            button.addActionListener(event -> {
                if (!acceptingMoves) {
                    return;
                }

                acceptingMoves = false;
                refreshBoard();

                TicTacToeMove move = new TicTacToeMove(row, col);
                moves.offer(move);
            });

            add(button);
        }

        refreshBoard();
    }

    public void refreshBoard() {
        Board board = game.model.getBoard();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Player owner = board.getOwner(row, col);

                if (owner == null) {
                    buttons[row][col].setText("");
                } else {
                    buttons[row][col].setText(game.getSymbol(owner));
                }

                buttons[row][col].setEnabled(
                    acceptingMoves && owner == null
                );
            }
        }
    }

    @Override
    public void createCLI() {
        System.out.println(game.model.getCurrentPlayer().getName());

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
        if (!Config.SWING_SUPPORTED) {
            return cliInput();
        } 

        SwingUtilities.invokeLater(() -> {
            acceptingMoves = true;
            refreshBoard();
        });

        try {
            return moves.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Waiting for a move was interrupted", e);
        }
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
