package app.games.framework;

/**
 * Player
 */
public abstract class Player {
    protected String name;
    protected PlayerState state;
    // maybe move strategie in here

    /**
     * Does a move
     */
    public abstract void doMove();

    /**
     * Gets the player state
     */
    public PlayerState getState() {
        return state;
    }
} 