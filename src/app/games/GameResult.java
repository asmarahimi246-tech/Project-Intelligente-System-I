package app.games;

/**
 * Represents the result or current status of a game.
 * Used to display game information in the user interface.
 * This enum is separate from server {@code WIN}/{@code LOSS}/{@code DRAW}
 * messages so it can be used for both local and online games.
 * A game's model determines and uses the result.
 */
public enum GameResult {
    /** The game is still in progress. */
    IN_PROGRESS,

    /** The player won the game. */
    PLAYER_WON,

    /** The player lost the game. */
    PLAYER_LOST,

    /** The game ended in a draw. */
    DRAW
}