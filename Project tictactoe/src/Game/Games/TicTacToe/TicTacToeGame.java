package Game.Games.TicTacToe;
import Game.Players.AI;
import Network.ServerListener;
import java.util.Scanner;

public class TicTacToeGame {
    private ServerListener listener;
    private char player1 = 'X';
    private char[] board = {'1','2','3','4','5','6','7','8','9'};
    private AI ai = new AI();
    private boolean aiMode;

    public TicTacToeGame(ServerListener listener, boolean aiMode) {
        this.listener = listener;
        this.aiMode = aiMode;
    }

    public void Symbol(char symbol) {
        this.player1 = symbol;
    }

    public void Turn() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Choose an number [1-9]: ");

            int input = scanner.nextInt();
            input -= 1;

            if (board[input] != 'X' && board[input] != 'O') {
                listener.sendMoveToServer(input);
                break;
            }

            System.out.println("Die plek is al bezet maak een andere move!");
        }
    }

    // laat de AI een zet doen
    public void aiMove() {
        int move = ai.numberAI(board, player1);

        System.out.println("AI move: " + move);

        listener.sendMoveToServer(move - 1);
    }

    public void Move(int number, boolean move) {

        // checkt of de gekozen pos vrij is
        if (board[number] != 'X' && board[number] != 'O') {

            // de move is van de eigen client
            if (move) {
                board[number] = player1;
            } else {

                // de move is voor de tegenstander
                if (player1 == 'X') {
                    board[number] = 'O';
                } else {
                    board[number] = 'X';
                }
            }
            System.out.println("Tussenstand:");
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
