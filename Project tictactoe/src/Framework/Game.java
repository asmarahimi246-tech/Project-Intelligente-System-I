package Framework;

import Network.Client;
import java.util.Scanner;

public class Game {
    private Client client;
    private char player1 = 'X';
    private char[] board = {'1','2','3','4','5','6','7','8','9'};

    public Game(Client client) {
        this.client = client;
    }

    public void Symbol(char symbol) {
        this.player1 = symbol;
    }

    public void Turn() {
        printBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose an number [1-9]: ");

        int input = scanner.nextInt();
        input -= 1;
        System.out.println(input);

        if (board[input] != 'X' && board[input] != 'O') {
            board[input] = player1;
            System.out.println("Tussenstand:");
            printBoard();
            client.sendCommand("move " + input);
        }
    }

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

    public void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }
}
