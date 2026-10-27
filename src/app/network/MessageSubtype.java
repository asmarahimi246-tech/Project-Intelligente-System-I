package app.network;

/**
 * Identifies the subtype of a network message and the information it carries.
 */
public enum MessageSubtype {
    /** Requests or provides the list of available games. */
    GAMELIST,

    /** Requests or provides the list of players. */
    PLAYERLIST,

    /** Requests or provides help information. */
    HELP,

    /** Requests or provides match information. */
    MATCH,

    /** Indicates that it is the player's turn. */
    YOURTURN,

    /** Communicates a player move. */
    MOVE,

    /** Communicates a win, loss, or draw result. */
    WIN_LOSS_DRAW,

    /** Sends or responds to a challenge. */
    CHALLENGE
}