package app.games.framework.game;

import java.util.ArrayList;

import app.games.framework.move.Move;
import app.games.framework.player.Player;
import app.pages.context.Page;

/**
 * <pre>
 * &gt; not a specific game --&gt; INTERFACE
 * &gt; what all games have in common
 *
 * EX:
 * &gt; doMove() &lt;-- takes Move object
 * &gt; GameResult getResult()
 * &gt; bool isGameOver()
 * </pre>
 */
public abstract class Game {
    protected Page page;
    protected ArrayList<Player> players = new ArrayList<Player>();
    protected boolean running;

    /**
     * Creates a game
     * 
     * @param page the page of the game
     */
    public Game(Page page, ArrayList<Player> players) {
        this.page = page;
        this.players = players;
    }

    @Override
    public abstract String toString();

    /**
     * Gets the page
     * 
     * @return the page
     */
    public Page getPage() {
        return page;
    }

    /**
     * Runs the game
     */
    public abstract void run();

    /**
     * Closes the game
     */
    public void close() {
        this.running = false;
    }

    /**
     * Used to proces a move
     * 
     * @param move the move to process
     */
    public abstract void procesMove(Move move);
}