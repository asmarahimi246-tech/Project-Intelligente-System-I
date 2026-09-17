import java.util.Scanner;
import java.util.Random;

public class prototype {
    public static void main(String[] args) {
        char[] board = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        Scanner input = new Scanner(System.in);
        Random computer = new Random();
        
        printBoard(board);

        System.out.print("Choose a position (1-9): ");
        int position = input.nextInt();

        board[position - 1] = 'X'; // position - 1 because the indexes are 0-8

        int computerPosition = computer.nextInt(9); // random number from 0-8

        while (board[computerPosition] == 'X' || board[computerPosition] == 'O') {
            computerPosition = computer.nextInt(9);
        }

        board[computerPosition] = 'O';

        printBoard(board);
    }    

    static void printBoard(char[] board) {
        System.out.println(board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("----------");
        System.out.println(board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("----------");
        System.out.println(board[6] + " | " + board[7] + " | " + board[8]);

    }
}
