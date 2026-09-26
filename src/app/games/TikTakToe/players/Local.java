package app.games.TikTakToe.players;

import app.games.TikTakToe.common.AbstractTikTakToePlayer;
import app.games.TikTakToe.common.TikTakToePlayerState;

/**
 * TikTakToePlayer
 */
public class Local extends AbstractTikTakToePlayer {
    /**
     * Constructor
     * 
     * @param name name of the player used on server login
     */
    public Local(String name) {
        super(name);
    }

    @Override
    public void doMove() {
        
    }
}
