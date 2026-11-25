package app.games.reversi.players.moveStrategies;

import app.games.framework.components.Board;
import app.games.framework.move.Move;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.reversi.ReversiPage;
import app.games.reversi.model.ReversiMove;

/**
 * 
 * UserStrategy
 */
public class UserStrategy implements MoveSrategy {
    ReversiPage page;

    /**
     * Makes a user strategy
     *
     * @param page for gui input
     */
    public UserStrategy(ReversiPage page) {
        this.page = page;
    }

    @Override
    public Move getMove(Board board, Player player) {
        ReversiMove toReturn = null;

        while (toReturn == null || !board.getByPos(toReturn.row(), toReturn.column()).isEmpty()){
            toReturn = page.requestMove();

            if (toReturn.row() == -1) {
                return null;
            }
        }
        return toReturn;
    }
}
