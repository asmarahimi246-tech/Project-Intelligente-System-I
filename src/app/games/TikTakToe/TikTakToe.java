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
    private char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private boolean gameRunning;
    private ServerListener listener;

    /**
     * Constructor
     */
    public TikTakToe() {
        // this needs to be the same as on the server
        this.name = "tic-tac-toe";
    }

    /**
     * Setup
     * 
     * TODO: finish the setup
     */
    private void setup() {
        // make test players
        // set one as host to read SVR mesages from
        AbstractTikTakToePlayer player1 = new Local("madeline");
        AbstractTikTakToePlayer player2 = new Local("badeline");

        this.players.add(player1);
        this.players.add(player2);

        // steal the listener from a player
        this.listener = player1.getListener();

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

        // to set player order
        this.processNextMessage();
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

        //gameloop
        while(this.gameRunning) {
            // render
            this.view.printBoard(board);

            // input / update
            for (AbstractTikTakToePlayer player : players) {
                if (!this.gameRunning) {
                    break;
                }
                this.gameRunning = false;
                // TODO: player moves
                //this.processNextMessage();
                //player.doMove();
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
    public void processNextMessage() {
        String answer = this.listener.getNextSvrMessage();
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
            System.out.println("[game] " + winningPlayer + " wins");
        } else {
            String winningPlayer = players.get(1).toString();
            System.out.println("[game] " + winningPlayer + " wins");
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
                sorted.add(player);
            }
        }
 
        for (AbstractTikTakToePlayer player : players) {
            if (!player.toString().equals(value)) {
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
