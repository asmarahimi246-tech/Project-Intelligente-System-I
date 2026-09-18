package Framework;

import Network.Client;
import java.util.Scanner;

public class ServerListener {
    private Client client;

    public ServerListener(Client client) {
        this.client = client;
    }

    public void Commandhandler(String message) {
        System.out.println(message);

        if (message.startsWith("SVR GAME YOURTURN")) {
            printBoard();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose an number [1-9]: ");

            int invoer = scanner.nextInt();
            int number = invoer - 1;
            System.out.println(invoer);

            client.sendCommand("move " + number);
        }

    }
    static char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    static void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }
}
