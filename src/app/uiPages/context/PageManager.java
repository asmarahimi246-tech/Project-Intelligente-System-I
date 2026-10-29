package app.uiPages.context;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.util.Logger;

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
 * It also uses a observer pattern to prevent keeping the thread on 100% usage
 * Most things are synchronized because swing and the cli are on different threads
 * 
 * Sources:
 * Wait and Notify: https://www.baeldung.com/java-wait-notify
 */
public class PageManager {
    private static PageManager INSTANCE;
    private Page page;
    private boolean changed = false;
    private boolean running = true;

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
    public synchronized void setCurrentPage(Page page) {
        // close old page
        if (this.page != null) {
            Logger.logDebug("closing: " + this.page.toString());
            this.page.close();
        }

        // set new page
        this.page = page;
        this.changed = true;
        Logger.logDebug("page changed to: " + page.toString());

        // notify that the page has changed
        // else it wil get stuck in wait for new page
        notifyAll();
    }

    /**
     * Gets the current page
     * 
     * Synchronized makes sure swing actualy gets the new page
     * without it the old ui wil get added again to the page
     * causing bugs
     * 
     * @return the page
     */
    public synchronized Page waitForNewPage() throws InterruptedException {
        // only update when needed
        // prevents flickering in swing
        while (!changed && running) {
            wait();
        }
        changed = false;
        return page;
    }

    /**
     * Closes the page manager
     */
    public synchronized void close() {
        running = false;
        notifyAll();
    }
}
