package games.kasia.app.client.modules.mainMenu;

import java.util.Scanner;

import games.kasia.app.App;
import games.kasia.app.client.common.widgets.menu.Menu;
import games.kasia.app.client.common.widgets.menu.parts.Option;
import games.kasia.app.client.modules.gameManager.GameManager;

/**
 * 
 * GameManager
 */
public class MainMenu {
    private App app;
    private Scanner userInput;
    private MainMenuView view = new MainMenuView();

    /**
     * Constructor
     */
    public MainMenu(App app) {
        this.app = app;
        this.userInput = app.getScanner();
    }

    /**
     * Gets the selected game
     */
    public void open() {
        GameManager gameManager = new GameManager();

        // Make menu
        Menu menu = new Menu("What do you want to do?", " ");

        // Add options
        Option toAdd = new Option("start local game", () -> gameManager.openLocal(this.userInput));
        menu.addOption(toAdd);
        toAdd = new Option("<- exit application", () -> app.sendCloseSignal());
        menu.addOption(toAdd);

        // print temp menu
        view.debugPrint(menu);

        // Run menu
        menu.runOption(this.userInput);
    }
}
