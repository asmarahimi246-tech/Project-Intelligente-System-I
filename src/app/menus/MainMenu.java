package app.menus;

import app.App;
import app.common.util.ScannerSingleton;
import app.common.widgets.cliMenu.CLIMenu;
import app.common.widgets.cliMenu.parts.Option;
import app.menus.common.MenuContext;
import app.menus.common.MenuState;

/**
 * MainMenu
 */
public class MainMenu implements MenuState {
    /**
     * Constructor
     */
    public MainMenu() { }

    @Override
    public void open() {
        // Get refference to the menu context
        MenuContext context = MenuContext.getInstance();

        MenuState gameMenu = new GameMenu();

        // Make menu
        CLIMenu menu = new CLIMenu("What do you want to do?", " ");

        // Add options
        Option toAdd = new Option("start local game", () -> context.setState(gameMenu));
        menu.addOption(toAdd);
        toAdd = new Option("<- exit application", () -> App.sendCloseSignal());
        menu.addOption(toAdd);

        // print temp menu
        menu.printOptionsList();

        // Run menu
        menu.runOption(ScannerSingleton.getInstance());
    }
}
