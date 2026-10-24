package app.games.tictactoe;
import java.util.ArrayList;

import app.games.framework.Game;
import app.games.framework.Player;
import app.games.tictactoe.players.moveStrategies.TicTacToeMove;

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
public class TicTacToeGame extends Game<TicTacToeMove> {

    /**
     * Makes a game
     * 
     * @param page the view
     * @param players the players
     */
    public TicTacToeGame(TicTacToePage page, ArrayList<Player> players) {
        super(page, players);
        
        super.running = true;
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }

    @Override
    public void run() {
        // game loop
        while (running) {

            // each player does a move
            for (Player player : players) {
                player.doMove();
            }
        }
    }

    @Override
    public void procesMove(TicTacToeMove move) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'procesMove'");
    }

}
