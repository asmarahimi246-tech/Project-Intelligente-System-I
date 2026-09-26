package app.games.TikTakToe.players;

import app.games.TikTakToe.TikTakToe;
import app.games.TikTakToe.common.AbstractTikTakToePlayer;

/**
 * Bot
 */
public class Bot extends AbstractTikTakToePlayer {

    /**
     * Constructor
     * 
     * @param name name of the player used on server login
     */
    public Bot(String name, TikTakToe tikTakToe) {
        super(name, tikTakToe);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void doMove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doMove'");
    }
}
