package app.games;

/**
 * <pre>
 * &gt; Represents why a game ended.
 * &gt; Used to display game information in the UI.
 * &gt; Separate from server WIN/LOSS/DRAW messages so it can be used
 *   in both local and online games.
 * &gt; Determined and used by a game's Model class.
 * </pre>
 */
public enum GameEndReason {
    NORMAL,
    FORFEITED,
    DISCONNECTED
}