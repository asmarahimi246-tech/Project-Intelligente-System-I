package app.games.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import app.pages.context.Page;
import app.games.framework.Game;

/**
 * <pre>
 * GameBuilder
 * &gt; Creates a game of the selected GameType.
 * &gt; Keeps game-type checks separate from GameApp, which should focus
 *   on running the games.
 *
 * EX:
 * &gt; Game class
 *   &gt; Input: GameType
 *   &gt; Check which game it is (e.g., with switch/case)
 *   &gt; Return a new instance of the corresponding game model
 * </pre>
 * 
 * @param <B> is the game specific
 * @param <G> is the game
 */
@SuppressWarnings({"unused"})
public abstract class GameBuilder<G extends Game, B extends GameBuilder<G, B>> {
    protected String mode = null;
    protected int playersToAdd;
    protected ArrayList<PlayerOption<? extends Player>> players = new ArrayList<PlayerOption<? extends Player>>();

    /**
     * Constructor
     */
    protected GameBuilder() {}

    /**
     * Get self
     * @return the builder
     */
    protected abstract B self();

    /**
     * Sets the game mode
     * 
     * @param mode one of the following
     * @return the builder
     */
    public B setMode(String mode) {
        this.mode = mode;
        return self();
    }

    /**
     * Adds default players
     * 
     * Example:
     * A Remote player for online games
     * 
     * @return the builder
     */
    public abstract B addDefaultPlayers();

    /**
     * Builds the game
     * @return the game
     */
    public abstract Game build();

    /**
     * Gets the player types to choose from
     * 
     * @return the options
     */
    public abstract List<PlayerOption<? extends Player>> getPlayerOptions();

    /**
     * Adds a player to the game
     * 
     * @return the builder
     */
    public B addPlayer(PlayerOption<? extends Player> player) {
        this.players.add(player);
        playersToAdd -= 1;
        return self();
    }

    /**
     * Gets the amount of players to add
     * 
     * @return the amount
     */
    public int getPlayersToAdd() {
        return playersToAdd;
    }
}
