package games.kasia.app.client.modules.mainMenu;

import java.util.Scanner;

import games.kasia.app.App;
import games.kasia.app.client.common.ScannerSingleton;
import games.kasia.app.client.common.widgets.menu.Menu;
import games.kasia.app.client.common.widgets.menu.parts.Option;
import games.kasia.app.client.modules.gameManager.GameManager;

/**
 * 
 * GameManager
 */
public class MainMenu {
    private App app;
    private MainMenuView view = new MainMenuView();

    /**
     * Constructor
     */
    public MainMenu(App app) {
        this.app = app;
    }

    /**
     * Gets the selected game
     */
    public void open() {
        GameManager gameManager = new GameManager();

        // Make menu
        Menu menu = new Menu("What do you want to do?", " ");

        // Add options
        Option toAdd = new Option("start local game", () -> gameManager.openLocal());
        menu.addOption(toAdd);
        toAdd = new Option("<- exit application", () -> app.sendCloseSignal());
        menu.addOption(toAdd);

        // print temp menu
        view.debugPrint(menu);

        // Run menu
        menu.runOption(ScannerSingleton.getInstance());
    }
}
