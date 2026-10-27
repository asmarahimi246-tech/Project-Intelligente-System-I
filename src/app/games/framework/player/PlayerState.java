package app.games.framework.player;

/**
 * Represents a player's current state in the game framework.
 * Some states are reached after receiving an {@code OK} response;
 * the finished state is reached after receiving the game result.
 */
public enum PlayerState {
    /** The player has been initialized. */
    INITIALIZED,

    /** The player has logged in. */
    LOGGED_IN,

    /** The player has subscribed. */
    SUBSCRIBED,

    /** The player has completed their turn. */
    EXECUTED_TURN,

    /** The player is allowed to make a move. */
    CAN_MOVE,

    /** The game has finished for the player. */
    FINISHED_GAME
}