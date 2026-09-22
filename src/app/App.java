package app;
import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import app.common.util.Config;

/**
 * 
 * App
 * 
 * yes
 */
public class App
{
    private Process localServer;
    private boolean running;

    /**
     * The main method - entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main( String[] args )
    {
        // create and run the application
        App app = new App();
        app.setup();
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
        //TODO: main

        try {
            System.out.println();
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
    public void setup() {
        // set app to running
        this.running = true;
        
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

    }

    /**
     * Closes the application
     */
    private void close() {
        // close scanner

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
    public void sendCloseSignal() {
        this.running = false;
    }
}