package app;

import java.util.NoSuchElementException;

import app.pages.context.PageContext;
import app.pages.context.PageState;

/**
 * <pre>
 * Runs the app and controls its flow from a high level.
 *
 * Examples:
 * &gt; Show the main page.
 * &gt; Return to the menu or exit.
 *
 * </pre>
 */
public class App {
    // volatile so the application can close when 
    // a differnt thread gets an error or 
    // just wants to close the application
    // for example the server listener
    private static volatile boolean RUNNING;

    /**
     * Run the application
     */
    public void run() {
        RUNNING = true;

        // get an instance of the current page state
        PageContext context = PageContext.getInstance();

        // open the page page
        try {
            while (RUNNING) {
                PageState page = context.getState();
                page.open();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Something went wrong. Closing application.");
        }
    }

    /**
     * Close the application
     * 
     * Static so it can be closed everywhere
     */
    public static void close() {
        RUNNING = false;
    }
}