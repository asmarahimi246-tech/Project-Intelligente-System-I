package games.kasia.app;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import games.kasia.app.client.common.Config;
import games.kasia.app.client.modules.gameManager.GameManager;
/**
 * 
 * App
 * 
 * yes
 */
public class App
{
    private Process localServer;

    /**
     * The main method - entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main( String[] args )
    {
        // create app
        App app = new App();
        app.setup();

        // game manager
        GameManager gameManager =  new GameManager();
        gameManager.test();

        // close app
        app.close();
    }

    /**
     * Does setup things
     * 
     * like starting the local server
     */
    public void setup() {
        try{
            // from config
            Path serverPath = Config.SERVERJAR;
            String adminPassword = Config.ADMINPASSWORD;

            // server setup commands to execute before starting server
            List<List<String>> commandList = new ArrayList<>();
            commandList.add(Arrays.asList("java", "-jar",  serverPath.normalize().toString(), "--set-password", adminPassword));

            // setup for server
            for (List<String> command : commandList) {
                ProcessBuilder setup = new ProcessBuilder(command);
                Process serverSetup = setup.start();
                serverSetup.destroyForcibly();
            }

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
    public void close() {
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
}