package app.menus.common;

/**
 * MenuState
 * 
 * The MenuState is a singleton (explanation v)
 * https://refactoring.guru/design-patterns/singleton
 * 
 * Its a singleton to prevent infinitly nesting menus
 * This makes it easyer to close the application and 
 * prevents unnesacary recourse usage
 * 
 * The menus use a state behavioral pattern (explanation v)
 * https://refactoring.guru/design-patterns/state
 * 
 * 
 */
public class MenuContext {
    private static MenuContext INSTANCE;
    private MenuState state;

    /**
     * Constructor
     */
    private MenuContext() {}

    /**
     * Get instance
     * @return the instance
     */
    public static MenuContext getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MenuContext();
        }
        return INSTANCE;
    }

    /**
     * Sets the state
     * 
     * @param menu the menu
     */
    public void setState(MenuState state) {
        this.state = state;
    }

    /**
     * Gets the state
     * 
     * @return the menu state
     */
    public MenuState getState() {
        return this.state;
    }
}
