package Game.Games.TicTacToe;
import Network.ServerListener;
import java.util.Scanner;
import Game.Players.AI;

public class TicTacToeGame {
    private ServerListener listener;
    private char player1 = 'X';
    private char[] board = {'1','2','3','4','5','6','7','8','9'};
    private AI ai = new AI();
    private boolean useAI;

    public boolean useAI() {
        return useAI;
    }

    public TicTacToeGame(ServerListener listener) {
        this.listener = listener;
    }

    public void Symbol(char symbol) {
        this.player1 = symbol;
    }

    public void aiTurn() {
        printBoard();

        char player2;

        if (player1 == 'X') {
            player2 = 'O';
        } else {
            player2 = 'X';
        }

        int input = ai.numberAI(board, player2);
        input -= 1;
        if (board[input] != 'X' && board[input] != 'O') {
            board[input] = player2;
            System.out.println("Tussenstand:");
            printBoard();
            listener.sendMoveToServer(input);
        }
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
            listener.sendMoveToServer(input);
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
