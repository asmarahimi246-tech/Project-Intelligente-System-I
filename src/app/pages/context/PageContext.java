package app.pages.context;

/**
 * PageContext
 * 
 * The PageContext is a singleton (explanation v)
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
public class PageContext {
    private static PageContext INSTANCE;
    private PageState state;

    /**
     * Constructor
     */
    private PageContext() {}

    /**
     * Get instance
     * @return the instance
     */
    public static PageContext getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageContext();
        }
        return INSTANCE;
    }

    /**
     * Sets the state
     * 
     * @param menu the menu
     */
    public void setState(PageState state) {
        this.state = state;
    }

    /**
     * Gets the state
     * 
     * @return the menu state
     */
    public PageState getState() {
        return this.state;
    }
}
