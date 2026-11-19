package app.games.framework.exceptions;

/**
 * IlligalPlayerOptionException
 * 
 * gets thrown if the builder cant find a legal player
 */
public class IlligalPlayerOptionException extends RuntimeException {
    /**
     * An invalid move exception
     * 
     * @param message message to print
     */
    public IlligalPlayerOptionException(String message) {
        super(message);
    }
}
