package app.games.framework.game;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.SwingWorker;

import app.games.framework.game.Game;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerOption;
import app.network.Client;
import app.uiPages.context.Page;
import app.util.Config;

/**
 * <pre>
 * GameBuilder
 * Creates a game of the selected GameType.
 * Keeps game-type checks separate from GameApp, which should focus
 *   on running the games.
 *
 * EX:
 * Game class
 *   Input: GameType
 *   Check which game it is (e.g., with switch/case)
 *   Return a new instance of the corresponding game model
 * </pre>
 * 
 * @param <B> is the game specific
 * @param <G> is the game
 * 
 * Sources:
 * Type parameters: https://www.w3schools.com/java/java_generics.asp
 *       Use: to make it possible for the extended gameBuilders to return a specific type
 *       EX: the TicTacToeBuilder returns a TicTacToeGame
 * Shuffle: https://www.geeksforgeeks.org/java/collections-shuffle-method-in-java-with-examples/
 *       Use: putting local players in a random order
 */
@SuppressWarnings({"unused"})
public abstract class GameBuilder<G extends Game, B extends GameBuilder<G, B>> {
    /** The game mode of the game options include: online, local, tournament */
    protected GameMode mode = null;
    /** The amount of players to add */
    protected int playersToAdd;
    /** The players to add as option. can be made into a player with .build() */
    protected ArrayList<PlayerOption<? extends Player>> players = new ArrayList<PlayerOption<? extends Player>>();
    /** A client */
    protected Client client;
    /** Game Name */
    protected String gameName;
    /** Game Page */
    protected GamePage page;
    /** Game State */
    protected GameState state;

    /**
     * Constructor
     */
    protected GameBuilder() {
        state = GameState.beingSetup("Builder made");
    }

    @Override
    public String toString() {
        return gameName + " Builder";
    }

    /**
     * Get self
     * @return the builder
     */
    protected abstract B self();

    /**
     * Sets the game mode
     * 
     * @param mode one of the following
     * @return the builder
     */
    public B setMode(GameMode mode) {
        this.mode = mode;
        // add the default players for the selected game mode
        addDefaultPlayers();
        return self();
    }

    /**
     * Gets the game mode
     * 
     * @return the mode
     */
    public GameMode getMode() {
        return mode;
    }

    /**
     * Adds default players
     * 
     * Example:
     * A Remote player for online games
     * 
     * @return the builder
     */
    protected abstract B addDefaultPlayers();

    /**
     * Builds the game
     * @return the game
     */
    public abstract G build();

    /**
     * Does setup for local players
     * 
     * @param builtPlayers the players to setup
     * 
     * @return a list of players setup to start a game
     * 
     * TODO: abstract the players so it doesnt have to be made for every builder
     */
    protected abstract ArrayList<Player> setupLocalPlayers(ArrayList<Player> builtPlayers);

    /**
     * Does setup for online players
     * 
     * @param builtPlayers the players to setup
     * 
     * @return a list of players setup to start a game
     * 
     * TODO: abstract the players so it doesnt have to be made for every builder
     */
    protected abstract ArrayList<Player> setupOnlinePlayers(ArrayList<Player> builtPlayers);

    /**
     * Does setup for tournament players
     * 
     * @param builtPlayers the players to setup
     * 
     * @return a list of players setup to start a game
     * 
     * TODO: abstract the players so it doesnt have to be made for every builder
     */
    protected abstract ArrayList<Player> setupTournamentPlayers(ArrayList<Player> builtPlayers);

    /**
     * Gets the player types to choose from
     * 
     * @return the options
     */
    public abstract List<PlayerOption<? extends Player>> getPlayerOptions();

    /**
     * Adds a player to the game
     * 
     * @param player the player to add
     * 
     * @return the builder
     */
    public B addPlayer(PlayerOption<? extends Player> player) {
        this.players.add(player);
        playersToAdd -= 1;
        return self();
    }

    /**
     * Gets the amount of players to add
     * 
     * @return the amount
     */
    public int getPlayersToAdd() {
        return playersToAdd;
    }

    /**
     * Gets the added players options list
     * 
     * @return the players
     */
    public ArrayList<PlayerOption<? extends Player>> getPlayers() {
        return players;
    }

    /**
     * Sets the client
     * 
     * @param client to set to
     */
    //public void setClient(Client client) {
    //    this.client = client;
    //}

    /**
     * Closes the builder
     */
    public abstract void close();

    /**
     * Does preperation before building a game
     * 
     * @return self
     */
    public B prepare() {

        if (mode != GameMode.LOCAL) {
            startOnlineMode();
        }

        if (mode == GameMode.ONLINE) {
            client.generateUniqueName();
            client.login();
            client.subscribe(gameName);
        } else if (mode == GameMode.TOURNAMENT) {
            if (Config.DEBUG) {
                client.generateUniqueName();
            } else {
                client.setName(Config.TEAM_NAME);
            }
            client.login();
        }

        return self();
    }

    /**
     * starts an online game
     */
    private void startOnlineMode() {
        Socket socket;

        // make the socket and client
        try {
            if (Config.DEBUG) {
                socket = new Socket("localhost", Config.LOCALPORT);
            } else {
                socket = new Socket(Config.ONLINEIP, Config.ONLINEPORT);
            }
            client = new Client(socket);
        } catch (Exception e) {
            // TODO: handle exception
            return;
        }
    }
}
