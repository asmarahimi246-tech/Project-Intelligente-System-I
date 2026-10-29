package app.games.framework.game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import app.games.framework.game.Game;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerOption;
import app.uiPages.context.Page;

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
    /** The game mode of the game options include: online, local, tournament */
    protected GameMode mode = null;
    /** The amount of players to add */
    protected int playersToAdd;
    /** The players to add as option. can be made into a player with .build() */
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
    public B setMode(GameMode mode) {
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
     * @param player the player to add
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
