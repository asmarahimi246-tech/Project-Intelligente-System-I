package app.games.tictactoe;
import java.util.ArrayList;

import app.games.framework.Game;
import app.games.framework.Player;
import app.games.tictactoe.model.TicTacToeModel;
import app.network.old.ServerListener;
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

    public TicTacToeGame(Page page, ArrayList<Player> players) {
        super(page, players);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

}
