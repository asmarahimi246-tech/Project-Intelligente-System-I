package app.games.tictactoe.players.moveStrategies;

import java.util.ArrayList;
import java.util.function.Predicate;

import app.games.tictactoe.TicTacToePage;
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
    public TicTacToeMove getMove(ArrayList<String> board, Predicate<TicTacToeMove> validateMove) {
        TicTacToeMove toReturn = null;

        while (!validateMove.test(toReturn)){
            toReturn = page.requestMove();
        }
        return toReturn;
    }
}
