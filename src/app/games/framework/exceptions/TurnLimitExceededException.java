package app.games.framework.exceptions;

/**
 * Gets thrown when the turn limit is exceeded
 */
public class TurnLimitExceededException extends RuntimeException {
    /**
     * An turn limit exceeded exception
     * 
     */
    public TurnLimitExceededException() {
        super();
    }

    /**
     * An turn limit exceeded exception with a message
     * 
     * @param message the message
     */
    public TurnLimitExceededException(String message) {
        super(message);
    }
}
