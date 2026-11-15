package app.games.framework.move;

import app.games.framework.game.Game;

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
    public Move getMove(Game game);
}

