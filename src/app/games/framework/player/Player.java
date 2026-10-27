package app.games.framework.player;

import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.util.NameGenerator;

/**
 * Player
 */
public abstract class Player {
    /** The player name */
    protected String name;
    /** The state of the player */
    protected PlayerState state;
    /** The strategy used to calculate the turn */
    protected MoveSrategy strategy;

    /**
     * Makes a player
     * 
     * With a random name
     */
    public Player() {
        NameGenerator generator = new NameGenerator();
        setName(generator.getName());
    }

    /**
     * Does a move
     * 
     * @param game the game to check rules with
     */
    public abstract void doMove(Game game);

    /**
     * Gets the player state
     * 
     * @return the player state
     */
    public PlayerState getState() {
        return state;
    }

    /**
     * Gets the player name
     * 
     * @return the player name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player name
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
} 