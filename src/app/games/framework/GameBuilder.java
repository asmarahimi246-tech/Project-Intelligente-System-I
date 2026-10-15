package app.games.framework;

import java.util.ArrayList;

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
    protected ArrayList<Player> players = new ArrayList<>();

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
     * @param mode one of the following
     * @return the builder
     */
    public B setMode(String mode) {
        this.mode = mode;
        return self();
    }

    /**
     * Builds the game
     * @return the game
     */
    public abstract Game build();

    /**
     * For testing
     */
    public abstract void printOptions();
}
