package app.games.workInProgress;

/**
 * MenuState
 * 
 * The MenuState is a singleton (explanation v)
 * https://refactoring.guru/design-patterns/singleton
 * 
 * Its a singleton to make creating and managing a game session
 * accross multiple menus easyer
 * 
 * The menus use a state behavioral pattern (explanation v)
 * https://refactoring.guru/design-patterns/state
 */
public class SessionContext {
    private static SessionContext INSTANCE;
    private SessionState state;

    /**
     * Constructor
     */
    private SessionContext() {}

    /**
     * Get instance
     * @return the instance
     */
    public static SessionContext getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SessionContext();
        }
        return INSTANCE;
    }

    /**
     * Sets the state
     * 
     * @param menu the menu
     */
    public void setState(SessionState state) {
        this.state = state;
    }

    /**
     * Gets the state
     * 
     * @return the session state
     */
    public SessionState getState() {
        return this.state;
    }
}
