package app.games.TikTakToe.common;

import java.io.IOException;
import java.net.Socket;

import app.common.network.Client;
import app.common.network.ServerListener;
import app.common.util.Config;
import app.common.util.Logger;

/**
 * Abstract player
 */
public abstract class AbstractTikTakToePlayer {
    protected String name;
    protected TikTakToePlayerState state;
    public Client client;

    /**
     * Makes a player
     * 
     * TODO: this
     */
    public AbstractTikTakToePlayer(String name) {
        try {
            this.name = name;
            this.connectToServer();
            this.state = TikTakToePlayerState.INITIALIZED;
            Logger.logDebug("[" + this.name + "] connected to server");
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
    protected abstract void doMove();

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
    public void disconnect() {
        this.client.sendCommand("bye");
        Logger.logDebug("[" + this.name + " > server] bye");
        client.close();
    };

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
        // send command
        this.client.sendCommand("login " + this.name);
        Logger.logDebug("[" + this.name + " > server] login " + this.name);

        // wait for reply
        if (this.client.isOk()) {
            this.state = TikTakToePlayerState.LOGGED_IN;
            Logger.logDebug("[server > " + this.name + "] OK");
        } else {
            //TODO: error handling
        }
    }

    /**
     * Subscribe to the game
     */
    public void subscribe(String gameName) {
        // send command
        this.client.sendCommand("subscribe " + gameName);
        Logger.logDebug("[" + this.name + " > server] subscribe " + gameName);

        // wait for reply
        if (this.client.isOk()) {
            this.state = TikTakToePlayerState.SUBSCRIBED;
            Logger.logDebug("[server > " + this.name + "] OK");
        } else {
            //TODO: error handling
        }
    }

    public ServerListener getListener() {
        return this.client.getListener();
    }
}