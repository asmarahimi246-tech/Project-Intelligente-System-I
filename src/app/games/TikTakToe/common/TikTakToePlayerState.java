package app.games.TikTakToe.common;

/**
 * All posible playerstates
 */
public enum TikTakToePlayerState {
    // start state
    INITIALIZED,

    // setup
    // gets OK
    LOGGED_IN,
    // gets OK
    SUBSCRIBED,

    // gets OK
    EXECUTED_TURN,

    // finish
    // gets RESULT OF GAME
    FINISHED_GAME
}