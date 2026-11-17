import ui.DisplayManager;

import java.util.NoSuchElementException;

public class App {
// handle App view/visual changes
    public void run() {
        AppManager manager = new AppManager();
        DisplayManager display = manager.getDisplay();

        try {
            while (manager.isRunning()) {
                if (display.isRunning()) {
                    if (display.hasChanged()) {
                        display.displayCurrentMenu();
                        display.handleMenuChoice();
                    }
                } else {
                        manager.shutdown();
                    }
                }
        } catch (NoSuchElementException e) {
            System.err.println("Something went wrong with the app.");
            e.printStackTrace(System.err);
        }
    }
}