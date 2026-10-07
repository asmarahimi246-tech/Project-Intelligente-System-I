package app.games.tictactoe;
import app.players.AI;
import app.network.ServerListener;
import java.util.Scanner;

/**
 * > connect View with Model
 * > get input & make Model do the work then tell View what to display
 *
 * EX:
 * > doMove()
 *  --> make model check if move is valid
 *  --> if true, make view update with the move added
 */
public class TicTacToeController {
    private ServerListener listener;
    private char player1 = 'X';
    private char[] board = {'1','2','3','4','5','6','7','8','9'};
    private AI ai = new AI();

    /**
     * Constructoor
     * 
     * @param listener
     */
    public TicTacToeController(ServerListener listener) {
        this.listener = listener;
    }

    /**
     * TODO: 
     * ik snap dit niet
     * 
     * @param symbol
     */
    public void symbol(char symbol) {
        this.player1 = symbol;
    }


    /**
     * TODO:
     * de input hoort bij de speler
     * 
     * dit moet de gameloop zijn
     */
    public void turn() {
        // TODO: read this
        // do scanner via a singleton
        // this will cause problem
        // like explanained in the previous refactor
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose an number [1-9]: ");

        int input = scanner.nextInt();
        input -= 1;

        if (board[input] != 'X' && board[input] != 'O') {
            board[input] = player1;

            System.out.println("Tussenstand:");
            printBoard();

            listener.sendMoveToServer(input);
        }

        // always close a scanner
        // never leave it open
        // leaving it open causes unnesacary resource usage
        // and unpredictable behauvior

        // but a scanner cant be reopened once closed
        // scanner.close() < this will permanently close the scanner
        // that why i choose a singleton
    }

    /**
     * TODO:
     * 
     * waarom staat dit appart
     * een speler is een speler of het nou AI is of niet
     */
    public void aiMove() {

    }

    /**
     * TODO:
     * deze code is speler specifiek
     * een game kan geen zet doen
     * een speler wel
     * 
     * @param number
     */
    public void move(int number) {
        if (board[number] != 'X' && board[number] != 'O') {
            char player2;

            if (player1 == 'X') {
                player2 = 'O';
            } else {
                player2 = 'X';
            }

            board[number] = player2;

            System.out.println("Tussenstand:");
            printBoard();
        }
    }

    /**
     * TODO:
     * 
     * dit hoort in view
     */
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