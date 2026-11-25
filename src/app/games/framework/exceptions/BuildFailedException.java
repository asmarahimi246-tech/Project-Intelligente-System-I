package app.games.framework.exceptions;

/**
 * BuildFailedException
 * 
 * Gets thrown when a game cant be build
 */
public class BuildFailedException extends RuntimeException {
    /**
     * An build failed exception
     * 
     * @param message message to print
     */
    public BuildFailedException(String message) {
        super(message);
    }
}
