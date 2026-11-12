package app;

import app.uiPages.MainPage;
import app.uiPages.context.Page;
import app.uiPages.context.PageManager;

import java.io.IOException;

/**
 * Entry point for the program.
 * Contains no application logic.
 *
 * Example:
 * Create a {@code GameApp} instance.
 * Call {@code app.run()}.
 */
public class Main {
    /**
     * Concstructor.
     * 
     * @param args args
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {
        // set a start page
        PageManager manager = PageManager.getInstance();
        Page page = new MainPage();
        manager.setCurrentPage(page);

        // make an app and run it
        App app = new App();
        app.run();
    }
}