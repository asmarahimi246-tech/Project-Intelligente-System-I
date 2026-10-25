package app.pages.context;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * PageManager
 * 
 * The PageManager is a singleton (explanation v)
 * https://refactoring.guru/design-patterns/singleton
 * 
 * Its a singleton to prevent infinitly nesting pages
 * This makes it easyer to close the application and 
 * prevents unnesacary recourse usage
 * 
 * The menus use a state behavioral pattern (explanation v)
 * https://refactoring.guru/design-patterns/state
 * 
 * 
 */
public class PageManager {
    private static PageManager INSTANCE;
    private Page page;
    private boolean changed = false;

    /**
     * Constructor
     */
    private PageManager() {}

    /**
     * Get an instance of the page manager
     * @return the instance
     */
    public static PageManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }

    /**
     * Sets the curently visable page to a new page
     * 
     * @param page the page to set it to
     */
    public void setCurrentPage(Page page) {
        this.changed = true;
        this.page = page;
    }

    /**
     * Gets the current page
     * 
     * @return the page
     */
    public Page getCurrentPage() {
        return page;
    }

    /**
     * Checks if the current page changed
     * 
     * @return true if it changed
     */
    public boolean changed() {
        boolean toReturn = false;
        if (changed) {
            toReturn = true;
            changed = false;
        }
        return toReturn;
    }
}
