package games.kasia.app.games.tikTakToe;
import games.kasia.app.games.Game;

/**
 * TikTakToe
 */
public class TikTakToe extends Game {
    /**
     * Constructor
     */
    public TikTakToe() {
        super();
        // this needs to be the same as on the server
        super.name = "tic-tac-toe";
    }

    @Override
    public void run() {
        System.out.println("ik ben boter kaas en eieren");
        super.closeInSeconds();
    }
}
