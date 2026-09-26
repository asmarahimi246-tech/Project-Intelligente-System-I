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
     * @param game the game
     */
    public Local(String name) {
        super();
        super.name = name;
    }

    @Override 
    public TikTakToePlayerState getState() {
        return super.state;
    }

    @Override
    public void doMove() {
        
    }

    @Override
    public void disconnect() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
    }
}
