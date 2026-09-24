package app.menus;

import app.App;
import app.common.util.ScannerSingleton;
import app.common.widgets.menu.MenuWidget;
import app.common.widgets.menu.parts.Option;
import app.menus.common.Menu;
import app.menus.common.MenuState;

/**
 * MainMenu
 */
public class MainMenu extends Menu {
    /**
     * Constructor
     */
    public MainMenu() { }

    @Override
    public void open() {
        // Get refference to the menu state
        MenuState state = MenuState.getInstance();

        Menu gameMenu = new GameMenu();

        // Make menu
        MenuWidget menu = new MenuWidget("What do you want to do?", " ");

        // Add options
        Option toAdd = new Option("start local game", () -> state.setState(gameMenu));
        menu.addOption(toAdd);
        toAdd = new Option("<- exit application", () -> App.sendCloseSignal());
        menu.addOption(toAdd);

        // print temp menu
        menu.printOptionsList();

        // Run menu
        menu.runOption(ScannerSingleton.getInstance());
    }
}
