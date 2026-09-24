package app.menus.common;

/**
 * MenuState
 * 
 * The MenuState is a singleton (explenation v)
 * https://refactoring.guru/design-patterns/singleton
 * 
 * Its a singleton to prevent infinitly nesting menus
 * This makes it easyer to close theapplication and 
 * prevents unnesacary recourse usage
 */
public class MenuState {
    private static MenuState INSTANCE;
    private Menu state;

    /**
     * Constructor
     */
    private MenuState() {}

    /**
     * Get instance
     * @return the instance
     */
    public static MenuState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MenuState();
        }
        return INSTANCE;
    }

    /**
     * Sets the state
     * 
     * @param menu the menu
     */
    public void setState(Menu menu) {
        this.state = menu;
    }

    /**
     * Gets the state
     * 
     * @return the menu state
     */
    public Menu getState() {
        return this.state;
    }
}
