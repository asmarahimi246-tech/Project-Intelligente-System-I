package app.games.tictactoe.players.moveStrategies;

import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.tictactoe.TicTacToePage;
import app.games.framework.components.Board;
import app.games.tictactoe.model.TicTacToeMove;

/**
 * UserStrategy
 * 
 * A strategy to do a move by geting user input
 * Uses the page to get input from
 */
public class UserStrategy implements MoveSrategy {
    TicTacToePage page;

    /**
     * Makes a user strategy
     * 
     * @param page for gui input
     */
    public UserStrategy(TicTacToePage page) {
        this.page = page;
    }

    @Override
    public TicTacToeMove getMove(Board board, Player player) {
        TicTacToeMove toReturn = null;

        // request input from the page
        // and check if its valid
        while (toReturn == null || !board.getByPos(toReturn.row(), toReturn.column()).isEmpty()){
            toReturn = page.requestMove();

            // return null if its the closing move
            // the closing move closes the game
            // TODO: can probably be better
            // maybe in a way where it exits this
            // with a notify
            if (toReturn.row() == -1) {
                return null;
            }
        }
        // return the move if its valid
        return toReturn;
    }
}
