package Framework;

import Network.Client;
import java.util.Scanner;

public class Game {
    private Client client;
    private char symbool = 'X';
    private char[] board = {'1','2','3','4','5','6','7','8','9'};

    public Game(Client client) {
        this.client = client;
    }

    public void Symbol(char symbol) {
        this.symbool = symbol;
    }

    public void Turn() {
        printBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose an number [1-9]: ");

        int invoer = scanner.nextInt();
        invoer -= 1;
        System.out.println(invoer);

        if (board[invoer] != 'X' && board[invoer] != 'O') {
            board[invoer] = symbool;
            System.out.println("Tussenstand:");
            printBoard();
            client.sendCommand("move " + invoer);
        }
    }

    public void Move(int number) {
        if (board[number] != 'X' && board[number] != 'O') {
            char symbol = (symbool == 'X') ? 'O' : 'X';
            board[number] = symbol;
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
