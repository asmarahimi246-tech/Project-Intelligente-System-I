package app.games.framework.move;

import app.games.framework.components.Board;
import app.games.framework.player.Player;

/**
 * Move
 * 
 * A move for the game
 */
public interface MoveSrategy {
    /**
     * Gets a move
     * @param board the board to place a move on
     * @param player the player doing the move
     * @return the move
     */
    public Move getMove(Board board, Player player);
}

