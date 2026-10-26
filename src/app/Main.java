package app;

import app.pages.MainPage;
import app.pages.context.PageManager;
import app.pages.context.Page;
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
        // set swing style
        if (Config.SWING_SUPPORTED) {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // new code v
        // set a start page
        PageManager manager = PageManager.getInstance();
        Page page = new MainPage();
        manager.setCurrentPage(page);

        // make an app and run it
        App app = new App();
        app.run();

        // close the scanner
        ScannerSingleton.closeInstance();
    }
}