package Game.Games.TicTacToe;
import java.util.Scanner;

public class TicTacToeView {
    public int readMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose a number [1-9]: ");
        return scanner.nextInt();
    }

    public void printStatus() {
        System.out.println("Tussenstand: ");
    }

    public void printBoard(char[] board) {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " ");
    }
}
