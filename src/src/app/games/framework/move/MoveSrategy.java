package src.app.games.framework.move;


import app.games.framework.game.Game;
import app.games.tictactoe.model.TicTacToeMove;

/**
 * Move
 * 
 * A move for the game
 */
public interface MoveSrategy {
    /**
     * Gets a move
     * @param game the game
     * @return the move
     */
    public TicTacToeMove getMove(Game game);
}