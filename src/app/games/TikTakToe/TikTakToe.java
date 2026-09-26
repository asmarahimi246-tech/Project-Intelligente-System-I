package app.games.TikTakToe;
import java.util.ArrayList;

import app.common.network.Client;
import app.common.network.ServerListener;
import app.common.util.Config;
import app.common.util.Logger;
import app.common.util.WHYsonParser;
import app.games.TikTakToe.common.AbstractTikTakToePlayer;
import app.games.TikTakToe.common.Timer;
import app.games.TikTakToe.players.Local;


/**
 * TikTakToe
 */
public class TikTakToe {
    private String name;
    private TikTakToeView view = new TikTakToeView(); 
    private ArrayList<AbstractTikTakToePlayer> players = new ArrayList<AbstractTikTakToePlayer>();
    private TikTakToeModel model = new TikTakToeModel();
    private boolean gameRunning;
    private ArrayList<ServerListener> listeners = new ArrayList<ServerListener>();

    /**
     * Constructor
     */
    public TikTakToe() {
        // this needs to be the same as on the server
        this.name = "tic-tac-toe";
    }

    /**
     * places a tile on the board
     * 
     * @param location the location to place the tile
     * @return true if its succesefull else false
     */
    public boolean setTile(int location, char toSet) {
        ArrayList<Character> toCheck = new ArrayList<Character>();
        for (AbstractTikTakToePlayer player : players) {
            toCheck.add(player.getBoardChar());
        }

        if (model.tileIsEmpty(location, toCheck)) {
            model.setTile(location, toSet);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Setup
     * 
     * TODO: finish the setup
     */
    private void setup() {
        // make test players
        // set one as host to read SVR mesages from
        AbstractTikTakToePlayer player1 = new Local("madeline", this);
        AbstractTikTakToePlayer player2 = new Local("badeline", this);

        this.players.add(player1);
        this.players.add(player2);

        // steal the listeners from the players
        this.listeners.add(player1.getListener());
        this.listeners.add(player2.getListener());

        // setup players with finite state machine
        boolean playersSetup = false;
        while (!playersSetup) {
            playersSetup = true;
            for (AbstractTikTakToePlayer player : players) {
                switch(player.getState()) {
                    case INITIALIZED:
                        player.login();
                        playersSetup = false;
                        break;
                    case LOGGED_IN:
                        player.subscribe(this.name);
                        playersSetup = false;
                        break;
                    case SUBSCRIBED:
                        playersSetup = true;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * closes the game
     */
    private void close() {
        for (AbstractTikTakToePlayer player : this.players) {
            player.disconnect();
        }
        Timer timer = new Timer();
        Logger.logDebug("[game] ends in 5 seconds");
        timer.closeInSeconds(5);
    }

    /**
     * run
     */
    public void run() {
        this.setup();
        this.gameRunning = true;

        // to set player order
        // not the best way
        for (ServerListener listener : listeners) {
            this.processNextMessage(listener);
        }

        //gameloop
        // way too many svr checks
        while(this.gameRunning) {
            // input / update
            for (AbstractTikTakToePlayer player : players) {
                Logger.logDebug(player.toString());

                // update
                for (ServerListener listener : listeners) {
                    this.processNextMessage(listener);
                }

                if (!this.gameRunning) {
                    break;
                }

                // render
                this.view.printBoard(model.getBoard());

                // input
                player.doMove();

                /* TODO: whatever this is
                 * timer is a bad way to do this
                 *
                 * dont remove the game wont end
                 * if the server takes longer to
                 * respond than this timer
                 */
                Timer timer = new Timer();
                timer.closeInMiliSeconds(100);

                // update
                for (ServerListener listener : listeners) {
                    this.processNextMessage(listener);
                }

                if (!this.gameRunning) {
                    break;
                }
            }
            if (!this.gameRunning) {
                break;
            }
        }

        this.close();
    }

    /**
     * processes server mesages
     */
    public void processNextMessage(ServerListener listener) {
        while (true) {
            String answer = listener.getNextSvrMessage();
            if (answer != null) {
                Logger.logDebug("[server > game] " + answer);
                WHYsonParser why = new WHYsonParser(answer);

                if (answer.contains("GAME MATCH")) {
                    sortPlayers(why.getValue("PLAYERTOMOVE"));
                } else if (answer.contains("GAME WIN")) {
                    endGame(answer);
                } else if (answer.contains("GAME LOSS")) {
                    endGame(answer);
                } else {
                    Logger.logDebug("[game] not implemented in proccesMessage: " + answer);
                }
            } else {
                break;
            }
        }
    }

    /**
     * prints who wins and kinda closes the game
     * 
     * @param answer what the server said
     */
    private void endGame(String answer) {
        WHYsonParser why = new WHYsonParser(answer);

        int player1Score = Integer.parseInt(why.getValue("PLAYERONESCORE"));
        int player2Score = Integer.parseInt(why.getValue("PLAYERTWOSCORE"));

        if (player1Score > player2Score) {
            String winningPlayer = players.get(0).toString();
            Logger.logDebug("[game] " + winningPlayer + " wins");
        } else {
            String winningPlayer = players.get(1).toString();
            Logger.logDebug("[game] " + winningPlayer + " wins");
        }
        this.sendCloseSignal();
    }

    /**
     * sorts players
     * 
     * @param value
     */
    private void sortPlayers(String value) {
        Logger.logDebug("[game] first player = " + value);
        ArrayList<AbstractTikTakToePlayer> sorted = new ArrayList<AbstractTikTakToePlayer>();

        for (AbstractTikTakToePlayer player : players) {
            if (player.toString().equals(value)) {
                player.setBoardChar('X');
                sorted.add(player);
            }
        }
 
        for (AbstractTikTakToePlayer player : players) {
            if (!player.toString().equals(value)) {
                player.setBoardChar('O');
                sorted.add(player);
            }
        }

        this.players = new ArrayList<AbstractTikTakToePlayer>(sorted);
    }
 
    /**
     * Sends a close signal
     */
    public void sendCloseSignal() {
        this.gameRunning = false;
    }
}
