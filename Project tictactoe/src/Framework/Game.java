package Framework;

import Network.Client;
import java.util.Scanner;

/**
 * Game
 */
public class Game {
    private Client client;
    private char player1 = 'X';
    private char[] board = {'1','2','3','4','5','6','7','8','9'};

    /**
     * Constuctor
     * creates a game
     * 
     * @param client the client
     */
    public Game(Client client) {
        this.client = client;
    }

    /**
     * TODO: documentation
     * i dont know what this does (yuna)
     * 
     * @param symbol ?
     */
    public void Symbol(char symbol) {
        this.player1 = symbol;
    }

    /**
     * Does a game turn
     */
    public void Turn() {
        // prints board
        printBoard();

        // gets input -1 because the server uses 0-8
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose an number [1-9]: ");
        int input = scanner.nextInt();
        input -= 1;
        System.out.println(input);

        // check if there is nothing on the square
        if (board[input] != 'X' && board[input] != 'O') {

            // place the player1char
            board[input] = player1;

            // print current game state
            System.out.println("Tussenstand:");
            printBoard();

            // send move to server
            client.sendCommand("move " + input);
        }
    }

    /**
     * TODO: documentation
     * i dont know what this does (yuna)
     * 
     * @param number ?
     */
    public void Move(int number) {
        if (board[number] != 'X' && board[number] != 'O') {
            char player2;

            if (player1 == 'X') {
                player2 = 'O';
            } else {
                player2 = 'X';
            }

            board[number] = player2;
            printBoard();
        }
    }

    /**
     * TODO: check if this can actualy be private
     * Prints the game board
     * 
     * A 3x3 grid
     */
    private void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }
}
