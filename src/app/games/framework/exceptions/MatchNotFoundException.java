package app.games.framework.exceptions;

/**
 * Gets thrown when the turn limit is exceeded
 */
public class MatchNotFoundException extends RuntimeException {
    /**
     * An exception for when a match cant be found
     * 
     */
    public MatchNotFoundException() {
        super();
    }
}
