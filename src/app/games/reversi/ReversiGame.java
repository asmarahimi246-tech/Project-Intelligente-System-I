package app.games.reversi;

import java.util.ArrayList;

import app.games.framework.game.Game;
import app.games.framework.move.Move;
import app.games.framework.player.Player;
import app.games.reversi.model.ReversiModel;
import app.uiPages.context.Page;

/**
 * ReversiGame
 */
public class ReversiGame extends Game {
    public ReversiModel model = new ReversiModel();

    /**
     * Makes a game
     * 
     * @param page the view
     * @param players the players
     */
    public ReversiGame(Page page, ArrayList<Player> players) {
        super(page, players);

        super.running = true;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    @Override
    public void procesMove(Move move) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'procesMove'");
    }
}
