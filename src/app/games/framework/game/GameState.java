package app.games.framework.game;

import app.games.framework.player.Player;

/**
 * GameState
 * 
 * The state of the game
 */
public final class GameState {
    /**
     * The current state
     */
    public enum Current {
        BEING_SETUP("Being setup: "),
        IN_PROGRESS("In progress: "),
        PLAYER_WON("Player won: "),
        DRAW("Draw: "),
        FORFEITED("Player forfeited: "),
        FAIL("Game crashed: ");

        private final String label;

        Current(String label) {
            this.label = label;
        }
    }

    private final Current status;
    private final String detail;
    private final Exception exception;

    /**
     * Makes a game state
     * 
     * @param status the state
     * @param detail details
     * @param exception exception
     */
    private GameState(Current status, String detail, Exception exception) {
        this.status = status;
        this.detail = detail;
        this.exception = exception;
    }

    /**
     * Makes a gameState and sets the state to BEING_SETUP
     * 
     * @return self
     */
    public static GameState beingSetup(String detail) {
        return new GameState(Current.BEING_SETUP, detail, null);
    }

    /**
     * Makes a gameState and sets the state to IN_PROGRESS
     * 
     * @return self
     */
    public static GameState inProgress() {
        return new GameState(Current.IN_PROGRESS, null, null);
    }

    /**
     * Makes a gameState and sets the state to PLAYER_WON
     * 
     * @param player the player that won
     * @return self
     */
    public static GameState playerWon(Player player) {
        return new GameState(Current.PLAYER_WON, player.getName(), null);
    }

    /**
     * Makes a gameState and sets the state to DRAW
     * 
     * @return self
     */
    public static GameState draw() {
        return new GameState(Current.DRAW, null, null);
    }

    /**
     * Makes a gameState and sets the state to FORFEITED
     * 
     * @param player the player that forfeited
     * @return self
     */
    public static GameState forfeited(Player player) {
        return new GameState(Current.FORFEITED, player.getName(), null);
    }

    /**
     * Makes a gameState and sets the state to FAILED
     * 
     * @param exception the exception that happened
     * @return self
     */
    public static GameState failed(Exception exception) {
        return new GameState(Current.FAIL, null, exception);
    }

    /**
     * Gets the State
     * 
     * @return the state
     */
    public Current getStatus() {
        return status;
    }

    /**
     * Gets details about the state
     * 
     * @return the details
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Gets the exception that caused the fail
     * 
     * @return the exception
     */
    public Exception getException() {
        return exception;
    }
}