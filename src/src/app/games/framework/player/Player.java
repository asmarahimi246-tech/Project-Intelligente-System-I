package src.app.games.framework.player;

import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.PlayerState;
import app.util.NameGenerator;

/**
 * Player
 */
public abstract class Player {
    protected String name;
    protected app.games.framework.player.PlayerState state;
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
     */
    public PlayerState getState() {
        return state;
    }

    /**
     * Gets the player name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player name
     */
    public void setName(String name) {
        this.name = name;
    }
} 