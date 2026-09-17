package games.kasia.app.client.modules.gameManager;

import games.kasia.app.games.GameTypes;

/**
 * GameManagerView
 */
public class GameManagerView {

    /**
     * Print a simplified ui in the terminal
     */
    public void debugPrint() {
        System.out.println("welke game wil je spelen? \ngeef een nummer:");

        for (GameTypes gameType : GameTypes.values()) {
            System.out.println(gameType.getNumber() + gameType.getGame().toString());
        }
    }
}
