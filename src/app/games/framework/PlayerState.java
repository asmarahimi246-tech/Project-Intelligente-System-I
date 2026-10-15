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
    CAN_MOVE,


    // gets OK
    EXECUTED_TURN,

    // finish
    // gets RESULT OF GAME
    FINISHED_GAME
}
