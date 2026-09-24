package app.games.common;

/**
 * All posible playerstates
 */
public enum PlayerState {
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