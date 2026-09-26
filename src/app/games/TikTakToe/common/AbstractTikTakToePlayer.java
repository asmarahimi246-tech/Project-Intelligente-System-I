package app.games.TikTakToe.common;

import java.io.IOException;
import java.net.Socket;

import app.common.network.Client;
import app.common.util.Config;

/**
 * Abstract player
 */
public abstract class AbstractTikTakToePlayer {
    protected String name;
    protected TikTakToePlayerState state;
    protected Client client;

    /**
     * Makes a player
     * 
     * TODO: this
     */
    public AbstractTikTakToePlayer() {
        try {
            this.connectToServer();
            this.state = TikTakToePlayerState.INITIALIZED;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**    (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override 
    public String toString(){
        return this.name;
    }

    /**
     * Does a move
     */
    public abstract void doMove();

    /**
     * connects to a server
     */
    private void connectToServer() throws IOException{
        Socket temp = new Socket("localhost", Config.LOCALPORT);
        this.client = new Client(temp);
    }

    /**
     * disconnects server
     */
    public  abstract void disconnect();

    /**
     * gets the state
     * @return the state
     */
    public TikTakToePlayerState getState() {
        return state;
    };

    /**
     * Logs in
     */
    public void login() {
        this.client.sendCommand("login " + this.name);
        if (this.client.isOk()) {
            this.state = TikTakToePlayerState.LOGGED_IN;
        }
    }

    /**
     * Subscribe to the game
     */
    public void subscribe(String gameName) {
        this.client.sendCommand("subscribe " + gameName);
        if (this.client.isOk()) {
            this.state = TikTakToePlayerState.SUBSCRIBED;
        }
    }
}