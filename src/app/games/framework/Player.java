package app.games.framework;

/**
 * Player
 */
public interface Player {
    /**
     * Does a move
     */
    abstract void doMove();

    /**
     * Gets the player state
     */
    abstract PlayerState getState();
} 