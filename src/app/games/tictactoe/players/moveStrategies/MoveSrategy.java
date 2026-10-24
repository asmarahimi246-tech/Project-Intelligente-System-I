package app.games.tictactoe.players.moveStrategies;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Move
 * 
 * A move for the game
 */
public interface MoveSrategy {
    /**
     * Gets a move
     * @param board the board
     * @param validateMove a predacite that true when the move is valid
     * @return the move
     */
    public TicTacToeMove getMove(ArrayList<String> board, Predicate<TicTacToeMove> validateMove);
}

