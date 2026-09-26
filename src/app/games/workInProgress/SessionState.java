package app.games.workInProgress;

/**
 * Menu
 * 
 * This interface has all functions that a game session must implement
 * 
 * It also allows the storing of any game as Game
 */
public interface SessionState {
    public abstract void setup();
    public abstract void run();
    public abstract void close();
}
