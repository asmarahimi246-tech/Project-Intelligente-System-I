import ui.DisplayManager;

public class AppManager {
    private volatile boolean running = true;
    private final DisplayManager display;


    public AppManager() {
        this.display = new DisplayManager();
    }

    /**
     * Check if the DisplayManager is running
     * @return the value of running
     */
    public boolean isRunning() {
        return running;
    }

    public void shutdown() {
        running = false;
        if (!display.isRunning()) {
            System.out.println("Shutting down...");
        } else {
            System.out.println("Display is still running");
        }
        // Stop accepting new application actions.
        // Disconnect cleanly from the server.
        // Release application resources.
    }

    public DisplayManager getDisplay() { return display; }
}