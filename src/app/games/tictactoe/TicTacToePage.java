package app.games.tictactoe;

import java.util.Scanner;

import app.pages.context.Page;

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
    private Scanner scanner = new Scanner(System.in);

    /**
     * TODO:
     * @return
     */
    public int readMove() {
        System.out.print("Choose a number [1-9]: ");
        return scanner.nextInt();
    }

    /**
     * TODO:
     */
    public void printTussenstand() {
        System.out.println("Tussenstand:");
    }

    /**
     * TODO:
     * @param board
     */
    public void printBoard(char[] board) {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " ");
    }

    /**
     * TODO:
     */
    public void printInvalidMove() {
        System.out.println("Die plek is al bezet doe een andere move!");
    }

    @Override
    public void open() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'open'");
    }
}
