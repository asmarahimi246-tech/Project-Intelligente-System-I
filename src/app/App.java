package app;

import java.util.NoSuchElementException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.pages.context.Page;
import app.pages.context.PageManager;

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
    // a different thread gets an error or 
    // just wants to close the application
    // for example the server listener
    private static volatile boolean RUNNING;
    private final JFrame applicationFrame = new JFrame("applicationFrame");


    /**
     * Constructor
     */
    public App () {
        // swing setup
        
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationFrame.setResizable(false);
        applicationFrame.setSize(500, 500);
        applicationFrame.setLocationRelativeTo(null);
    }

    /**
     * Run the application
     */
    public void run() {
        RUNNING = true;

        // get an instance of the page manager
        PageManager manager = PageManager.getInstance();

        try {
            while (RUNNING) {
                // only update when needed
                // prevents flickering in swing
                if (manager.changed()) {
                    // get the page
                    Page page = manager.getCurrentPage();

                    // set the page for swing 
                    // this works because page extends JPanel
                    // shows a white page if empty
                    setContentPane(page);

                    // open the page 
                    page.open();
                }
            }
        } catch (NoSuchElementException e) {
            System.err.println("Something went wrong.");
            e.printStackTrace(System.err);
        }

        System.out.println("Closing application.");
        applicationFrame.dispose();
    }

    /**
     * Close the application
     * 
     * Static so it can be closed everywhere
     */
    public static void close() {
        RUNNING = false;
    }

    /**
     * shows a page
     * 
     * note: expects the ui to already be build
     * 
     * @param page
     */
    private void setContentPane(JPanel page) {
        applicationFrame.setContentPane(page);
        applicationFrame.revalidate();
        applicationFrame.repaint();
        applicationFrame.setVisible(true);
    }
}