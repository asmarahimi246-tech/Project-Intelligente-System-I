package app.Game.Games.TicTacToe;
import java.util.Scanner;

public class TicTacToeView {
    private Scanner scanner = new Scanner(System.in);

    public int readMove() {
        System.out.print("Choose a number [1-9]: ");
        return scanner.nextInt();
    }

    public void printTussenstand() {
        System.out.println("Tussenstand:");
    }

    public void printBoard(char[] board) {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " ");
    }

    public void printInvalidMove() {
        System.out.println("Die plek is al bezet doe een andere move!");
    }
}
