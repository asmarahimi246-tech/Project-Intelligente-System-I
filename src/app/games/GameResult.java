package app.games;

/**
 * <pre>
 * &gt; Represents the result or status of a game.
 * &gt; Used to display game information in the UI.
 * &gt; Separate from server WIN/LOSS/DRAW messages so it can be used
 *   in both local and online games.
 * &gt; Determined and used by a game's Model class.
 * </pre>
 */
public enum GameResult {
    IN_PROGRESS,
    PLAYER_WON,
    PLAYER_LOST,
    DRAW
}