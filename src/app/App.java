package app;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.pages.ClosingPage;
import app.pages.context.Page;
import app.pages.context.PageManager;
import app.util.Config;
import app.util.Logger;

/**
 * Runs the app and controls its flow from a high level.
 *
 * Examples:
 * Show the main page.
 * Return to the menu or exit.
 *
 * Sources:
 * Swing event dispatch thread info: https://www.youtube.com/watch?v=PDKyjl9I0EA
 *      Use: for making it sleep while the page is changing
 */
public class App {
    /** 
     * volatile so the application can close when 
     * a different thread gets an error or 
     * just wants to close the application
     * for example the server listener
     */
    private static volatile boolean RUNNING;
    /**
     * The application frame of the app
     */
    private final JFrame applicationFrame;


    /**
     * Constructor
     */
    public App () {
        // swing setup
        if (Config.SWING_SUPPORTED) {
            applicationFrame = new JFrame("applicationFrame");
            applicationFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            applicationFrame.setResizable(false);
            applicationFrame.setSize(1000, 1000);
            applicationFrame.setLocationRelativeTo(null);

            // add a listener to close the application on pressing X
            applicationFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    close();
                }
            });
        } else {
            applicationFrame = null;
        }
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
                Logger.logDebug("[app]");

                Page page;

                // get the page
                page = manager.waitForNewPage();

                // set the page for swing 
                // this works because page extends JPanel
                // shows a white page if empty
                if (Config.SWING_SUPPORTED) {
                    try {
                        // block EDT until content pane is set
                        // wait for the swing ui to be set before doing open
                        SwingUtilities.invokeAndWait(() -> setContentPane(page));
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                Logger.logDebug("current page: " + page.toString());

                // open the page 
                page.open();
            }
        } catch (NoSuchElementException e) {
            System.err.println("Something went wrong.");
            e.printStackTrace(System.err);
        } catch (InterruptedException e) {
            System.err.println("Something went wrong.");
            e.printStackTrace(System.err);
        }

        if (Config.SWING_SUPPORTED) {
            applicationFrame.dispose();
        }
    }

    /**
     * Close the application
     * 
     * Static so it can be closed everywhere
     */
    public static void close() {
        Logger.logDebug("closing app");

        PageManager manager = PageManager.getInstance();

        // set to clossing page
        // prevents double swing gui and looks nice
        Page page = new ClosingPage();
        manager.setCurrentPage(page);

        // close the manager
        manager.close();
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
        applicationFrame.getContentPane().setBackground(Config.BACKGROUND_COLOR);
        applicationFrame.revalidate();
        applicationFrame.repaint();
        applicationFrame.setVisible(true);
    }
}