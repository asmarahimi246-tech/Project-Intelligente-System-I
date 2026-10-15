package app.games.tictactoe;
import java.util.ArrayList;

import app.games.framework.Game;
import app.games.framework.Player;
import app.network.ServerListener;
import app.pages.context.Page;

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
public class TicTacToeGame extends Game {
    private ServerListener listener;
    private TicTacToeModel model;
    private TicTacToePage view;
    private char player1 = 'X';

    /**
     * Creates a TicTacToe game
     *
     * @param page the page used to display the game
     * @param players the players participating in the game
     */
    public TicTacToeGame(Page page, ArrayList<Player> players) {
        super(page, players);
        this.model = new TicTacToeModel();
        this.view = new TicTacToePage();
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
            view.printTussenstand();
            printBoard();

            // stuurt de AI move naar de server bij een online game
            if (listener != null) {
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


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }

}
