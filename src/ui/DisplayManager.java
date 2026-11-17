
package ui;

import ui.commands.*;
import ui.menus.MainMenu;
import ui.menus.Menu;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class DisplayManager {

    private final Map<MenuOption, Command> globalCommands =
            new EnumMap<>(MenuOption.class);

    private final UI ui;

    private Menu currentMenu;
    private final LinkedList<Menu> menuHistory = new LinkedList<>();

    // Keep this atomic only if another thread reads/resets it.
    private final AtomicBoolean changed = new AtomicBoolean(true);

    private boolean running = true;


    public DisplayManager() {
        this.ui = new UI();

        this.currentMenu = new MainMenu(ui);
        menuHistory.addLast(currentMenu);

        registerGlobalCmds();
    }

    private void registerGlobalCmds() {

        globalCommands.put(
                MenuOption.BACK,
                new BackCmd(this)
        );

        globalCommands.put(
                MenuOption.MAIN,
                new ToMainMenuCmd(this)
        );

        globalCommands.put(
                MenuOption.EXIT,
                new ExitCmd(this)
        );

        globalCommands.put(
                MenuOption.PLAY,
                new PlayCmd(this)
        );

        globalCommands.put(
                MenuOption.INVALID,
                new InvalidOptionCmd(this)
        );
    }

    /**
     * Main UI execution loop.
     */
    public void run() {

        while (running) {
            displayCurrentMenu();
            handleMenuChoice();
        }
    }

    public void displayCurrentMenu() {
        currentMenu.displayMenu();
    }

    public void handleMenuChoice() {

        MenuOption choice = currentMenu.getMenuChoice();

        // check global commands first
        Command command = globalCommands.get(choice);

        if (command != null) {
            command.execute();
            return;
        }

        // then let the current menu handle it
        currentMenu.executeCommand(choice);
    }

    /**
     * Navigate to a new menu.
     */
    public void setCurrentMenu(Menu menu) {
        currentMenu = menu;
        menuHistory.addLast(menu);
        changed.set(true);
    }

    /**
     * Return to the previous menu.
     */
    public void goBack() {
        menuHistory.removeLast();
        currentMenu = menuHistory.getLast();
        changed.set(true);
    }

    public void refresh() {
        currentMenu = menuHistory.getLast();
        changed.set(true);
    }

    /**
     * Return to the main menu and reset navigation history.
     */
    public void returnToMainMenu() {
        Menu mainMenu = new MainMenu(ui);

        menuHistory.clear();
        menuHistory.addLast(mainMenu);

        currentMenu = mainMenu;

        changed.set(true);
    }

    /**
     * Stop the UI loop.
     */
    public void stop() {
        running = false;
        ui.close();
    }

    public boolean hasChanged() {
        return changed.getAndSet(false);
    }

    public UI getUI() { return ui; }

    public boolean isRunning() { return running; }
}