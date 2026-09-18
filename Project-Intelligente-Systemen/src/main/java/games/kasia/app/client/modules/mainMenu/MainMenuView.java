package games.kasia.app.client.modules.mainMenu;

import games.kasia.app.client.common.widgets.menu.Menu;

/**
 * MainMenuView
 */
public class MainMenuView {

    /**
     * Print a simplified ui in the terminal
     */
    public void debugPrint(Menu menu) {
        menu.clearScreen();

        System.out.println("Main menu");
        System.out.println("");
        System.out.println("");
        
        menu.printOptionsList();
    }
}
