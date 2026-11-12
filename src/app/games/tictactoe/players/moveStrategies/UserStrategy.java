package app.games.tictactoe.players.moveStrategies;

import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.TicTacToePage;
import app.games.tictactoe.model.Board;
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
    public TicTacToeMove getMove(Game game) {
        TicTacToeMove toReturn = null;

        // get the game
        TicTacToeGame ticTacToe = null;
        if (game instanceof TicTacToeGame) {
            ticTacToe = (TicTacToeGame) game;
        }

        // get the board
        Board board = ticTacToe.model.getBoard();

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
