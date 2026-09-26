package app.games.TikTakToe.players;

import app.games.TikTakToe.TikTakToe;
import app.games.TikTakToe.common.AbstractTikTakToePlayer;

/**
 * Remote
 */
public class Remote extends AbstractTikTakToePlayer{

    /**
     * Constructor
     * 
     * @param name name of the player used on server login
     */
    public Remote(String name, TikTakToe tikTakToe) {
        super(name, tikTakToe);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void doMove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doMove'");
    }
}
