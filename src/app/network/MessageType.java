package app.network;

/**
 * Identifies the general category of a network message.
 */
public enum MessageType {
    /** Indicates a successful response. */
    OK,

    /** Indicates an error response. */
    ERR,

    /** Indicates a server message. */
    SRV
}