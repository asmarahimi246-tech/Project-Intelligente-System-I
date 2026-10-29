package app;

import app.uiPages.MainPage;
import app.uiPages.context.Page;
import app.uiPages.context.PageManager;
import app.util.Config;
import app.util.ScannerSingleton;

import java.io.IOException;

import javax.swing.UIManager;

/**
 * <pre>
 * Entry point for the program.
 * Contains no application logic.
 *
 * Example:
 * &gt; Create a {@code GameApp} instance.
 * &gt; Call {@code app.run()}.
 * </pre>
 */
public class Main {
    /**
     * Concstructor.
     * 
     * @param args args
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {
        // new code v
        // set a start page
        PageManager manager = PageManager.getInstance();
        Page page = new MainPage();
        manager.setCurrentPage(page);

        // make an app and run it
        App app = new App();
        app.run();
    }
}