package games.kasia.app.client.modules.gameManager;

import java.util.Scanner;

import games.kasia.app.games.Game;
import games.kasia.app.games.GameTypes;
import games.kasia.app.client.common.Config;

/**
 * 
 * GameManager
 */
public class GameManager {
    GameManagerView view = new GameManagerView();

    /**
     * Constructor
     */
    public GameManager() {
    }

    /**
     * a test method
     */
    public void test() {
        // print debug
        if (Config.DEBUG){
            this.view.debugPrint();
        }

        // scan for input
        Scanner scanner  = new Scanner(System.in);
        int gameNumber = scanner.nextInt();
        scanner.close();

        // get game
        Game game = GameTypes.getByNumber(gameNumber);

        // test de game
        game.run();
    }
}
