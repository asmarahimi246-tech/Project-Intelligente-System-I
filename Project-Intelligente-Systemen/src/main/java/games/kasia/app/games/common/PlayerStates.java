package games.kasia.app.games.common;

/**
 * All posible playerstates
 */
public enum PlayerStates {
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