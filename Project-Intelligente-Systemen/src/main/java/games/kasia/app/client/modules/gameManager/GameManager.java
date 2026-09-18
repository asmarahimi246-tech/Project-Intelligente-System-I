package games.kasia.app.client.modules.gameManager;

import java.util.Scanner;

import games.kasia.app.games.GameTypes;
import games.kasia.app.client.common.ScannerSingleton;
import games.kasia.app.client.common.widgets.menu.Menu;
import games.kasia.app.client.common.widgets.menu.parts.Option;

/**
 * 
 * GameManager
 */
public class GameManager {
    private GameManagerView view = new GameManagerView();
    private boolean online;

    /**
     * Constructor
     */
    public GameManager() {
    }

    /**
     * Opens a local game
     */
    public void openLocal() {
        // Set online to false
        this.online = false;

        // Make menu
        Menu menu = new Menu("What do you want to play?", " ");

        // Add options
        Option toAdd;
        for (GameTypes gameType : GameTypes.values()) {
            toAdd = new Option(gameType.getGame().toString(), () -> gameType.getGame().run());
            menu.addOption(toAdd);
        }
        toAdd = new Option("<- go back", () -> {});
        menu.addOption(toAdd);

        // print temp menu
        view.debugPrint(menu);

        // Run menu
        menu.runOption(ScannerSingleton.getInstance());
    }
}
