package app.games.framework.exceptions;

/**
 * IlligalBoardStateException
 * 
 * gets thrown if the board is in an illigal state
 */
public class IlligalBoardStateException extends RuntimeException {
    /**
     * An invalid board exception
     * 
     * @param message message to print
     */
    public IlligalBoardStateException(String message) {
        super(message);
    }
}
