package app.games.tictactoe.players.moveStrategies;

import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.TicTacToePage;
import app.games.tictactoe.model.TicTacToeMove;
/**
 * UserStrategy
 * 
 * A strategy to do a move by geting user input
 * Uses the page to get input from
 * 
 * TODO: input from page (send message to Yuna if this is not possible)
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

        // request input from the page
        // and check if its valid
        while (!ticTacToe.model.validateMove(toReturn)){
            toReturn = page.requestMove();
        }
        // return the move if its valid
        return toReturn;
    }
}
