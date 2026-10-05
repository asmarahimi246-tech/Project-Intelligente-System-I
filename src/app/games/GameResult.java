package app.games;

/**
 * > represents result/status of a game
 * > used for showing game info in (G)UI
 * > is separate from server WIN/LOSS/DRAW messages
 *         so can be used in both local AND online games
 * > used inside/determined by Model class of a game
 */
public enum GameResult {
    IN_PROGRESS,
    PLAYER_WON,
    PLAYER_LOST,
    DRAW
}