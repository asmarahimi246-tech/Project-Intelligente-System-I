package app.games.framework;

/**
 * PlayerState
 */
public enum PlayerState {
    // start state
    INITIALIZED,

    // online only states
    // gets OK
    LOGGED_IN,
    // gets OK
    SUBSCRIBED,

    // gets OK
    EXECUTED_TURN,
    // gets OK
    CAN_MOVE,

    // finish
    // gets RESULT OF GAME
    FINISHED_GAME
}
