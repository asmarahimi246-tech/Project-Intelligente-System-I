package app;
import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import app.common.util.Config;
import app.common.util.ScannerSingleton;
import app.menus.MainMenu;
import app.menus.common.MenuContext;
import app.menus.common.MenuState;

/**
 * App
 * 
 * The app is a singleton (explenation v)
 * https://refactoring.guru/design-patterns/singleton
 * 
 * Its a singleton to make send close signal globaly accesable
 * 
 * It also prevents the making of multiple Apps 
 * thus solving the problem of multiple local server runnig on the same port
 */
public class App
{
    private static App INSTANCE;
    private Process localServer;
    private static boolean RUNNING;

    /**
     * Constructor
     */
    private App() {
        this.setup();

        // set app to running
        RUNNING = true;
    }

    /**
     * Get instance
     * @return the instance
     */
    public static App getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new App();
        }
        return INSTANCE;
    }

    /**
     * The main method - entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main( String[] args )
    {
        // create and run the application
        App app = App.getInstance();
        app.run();

        // For when it crashes
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Something went wrong. Closing application");
            app.close();
        }));
    }

    /**
     * runs the application
     */
    private void run() {
        // get an instance of the current menu state
        MenuContext context = MenuContext.getInstance();

        try {
            while (RUNNING){
                MenuState state = context.getState();
                state.open();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Something went wrong. Closing application");
        } finally {
            this.close();
        }
    }

    /**
     * Does setup things
     * 
     * like starting the local server
     */
    private void setup() {        
        // setup local server
        try{
            // from config
            Path serverPath = Config.SERVERJAR;

            // starting server
            ProcessBuilder server = new ProcessBuilder("java", "-jar", serverPath.normalize().toString());
            localServer = server.start();

            // print where the server is listening
            System.out.println("Local server listening on: localhost:" + Config.LOCALPORT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // set a entry menu as the menu state
        MenuState entryMenu = new MainMenu();
        MenuContext context = MenuContext.getInstance();
        context.setState(entryMenu);
    }

    /**
     * Closes the application
     */
    private void close() {
        // close scanner
        ScannerSingleton.closeInstance();

        // close local server
        if (localServer != null) {
            localServer.destroy();

            try {
                // give it some time
                boolean finished = localServer.waitFor(5, TimeUnit.SECONDS);
                if (finished) {
                    int exitCode = localServer.exitValue();
                    System.out.println("Local server exited with code: " + exitCode);

                // force if to slow
                } else {
                    System.out.println("Local server did not exit within the timeout period");
                    System.out.println("Force closing local server");
                    localServer.destroyForcibly();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sends a signal to tell the application to close
     */
    public static void sendCloseSignal() {
        RUNNING = false;
    }
}