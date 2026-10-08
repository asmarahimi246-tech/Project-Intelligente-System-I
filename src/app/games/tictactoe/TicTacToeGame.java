package app.games.tictactoe;
import app.players.AI;
import app.network.ServerListener;

/**
 * <pre>
 * &gt; connect View with Model
 * &gt; get input &amp; make Model do the work, then tell View what to display
 *
 * EX:
 * &gt; doMove()
 *   --&gt; make model check if move is valid
 *   --&gt; if true, make view update with the move added
 * </pre>
 */
public class TicTacToeGame {
    private ServerListener listener;
    private TicTacToeModel model;
    private TicTacToePage view;
    private AI ai;
    private char player1 = 'X';

    /**
     * Constructoor
     * 
     * @param listener
     */
    public TicTacToeGame(ServerListener listener) {
        this.listener = listener;
        this.model = new TicTacToeModel();
        this.view = new TicTacToePage();
        this.ai = new AI();
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
        while (true) {
            int input = view.readMove();
            input -= 1;

            if (model.placeMove(input, player1)) {
                view.printTussenstand();
                printBoard();

                // als het een online game is stuurt die de zet naar de server
                if (listener != null) {
                    listener.sendMoveToServer(input);
                }
                return;
            }
            view.printInvalidMove();
        }
    }

    /**
     * TODO:
     * deze code is speler specifiek
     * een game kan geen zet doen
     * een speler wel
     * 
     * @param number
     */
    public void move(int number, boolean ownMove) {
        char symbol;
        if (ownMove) {
            symbol = player1;
        } else {
            if (player1 == 'X') { symbol = 'O';
            } else { symbol = 'X';
            }
        }

        if (model.placeMove(number, symbol)) {
            view.printTussenstand();
            printBoard();
        }
    }

    /**
     * TODO:
     * 
     * waarom staat dit appart
     * een speler is een speler of het nou AI is of niet
     */
    public void aiMove() {
        int move = ai.numberAI(model.getBoard(), player1);

        System.out.println("AI move: " + move);

        int index = move - 1;

        if (model.placeMove(index, player1)) {
            view.printTussenstand();
            printBoard();

            // stuurt de AI move naar de server bij een online game
            if (listener != null) {
                listener.sendMoveToServer(index);
            }
        }
    }


    /**
     * TODO:
     * 
     * dit hoort in view
     */
    public void printBoard() {
        view.printBoard(model.getBoard());
    }

    public boolean checkWinner() {
        return model.checkWinner();
    }

    public boolean isBoardFull() {
        return model.isBoardFull();
    }

}
