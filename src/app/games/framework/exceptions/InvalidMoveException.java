package app.games.framework.exceptions;

/**
 * Gets thrown when a move is invalid
 */
public class InvalidMoveException extends RuntimeException {

    /**
     * An invalid move exception
     * 
     * @param message message to print
     */
    public InvalidMoveException(String message) {
        super(message);
    }
}
