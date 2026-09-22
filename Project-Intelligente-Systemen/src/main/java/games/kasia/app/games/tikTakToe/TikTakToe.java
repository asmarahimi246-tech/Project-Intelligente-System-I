package games.kasia.app.games.tikTakToe;
import java.util.List;
import java.util.Scanner;

import games.kasia.app.games.Game;
import games.kasia.app.games.common.Player;
import games.kasia.app.games.tikTakToe.players.TikTakToePlayer;

/**
 * TikTakToe
 */
public class TikTakToe extends Game {
    List<Player> playerList;
    /**
     * Constructor
     */
    public TikTakToe() {
        super();
        // this needs to be the same as on the server
        super.name = "tic-tac-toe";
    }

    private void temp() {
        for (Player player : playerList) {

        }
    }

    //TODO: make actual game
    @Override
    public void run() {

        System.out.println("ik ben boter kaas en eieren");
        System.out.println("speler test");
        Player player1 = new TikTakToePlayer("madeline", this);
        Player player2 = new TikTakToePlayer("badeline", this);

        player1.subscribe();
        player2.subscribe();

        //gameloop
        int movesLeft = 9;
        while(movesLeft > 0) {
            player1.doMove();
            movesLeft--;
            System.out.println(movesLeft);
            player2.doMove();
            movesLeft--;
            System.out.println(movesLeft);
        }

        player1.disconnect();
        player2.disconnect();
        super.closeInSeconds();
    }
}
