package app.games;
import java.util.ArrayList;
import java.util.Scanner;

import app.common.util.Config;
import app.common.util.WHYsonParser;
import app.games.common.Game;
import app.games.common.Player;
import app.games.players.TikTakToePlayer;


/**
 * TikTakToe
 */
public class TikTakToe extends Game {
    private ArrayList<Player> players = new ArrayList<Player>();

    /**
     * Constructor
     */
    public TikTakToe() {
        super();
        // this needs to be the same as on the server
        super.name = "tic-tac-toe";
    }

    /**
     * Setup
     */
    private void setup() {
        // make test players
        Player player1 = new TikTakToePlayer("madeline", this);
        Player player2 = new TikTakToePlayer("badeline", this);

        this.players.add(player1);
        this.players.add(player2);

        // setup players with finite state machine
        boolean playersSetup = false;
        while (!playersSetup) {
            playersSetup = true;
            for (Player player : players) {
                switch(player.getState()) {
                    case INITIALIZED:
                        player.login();
                        playersSetup = false;
                        break;
                    case LOGGED_IN:
                        player.subscribe();
                        playersSetup = false;
                        break;
                    case SUBSCRIBED:
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
        for (Player player : this.players) {
            player.disconnect();
        }
        super.closeInSeconds();
    }

    //TODO: Place setup in setup
    @Override
    public void run() {
        this.setup();
        super.gameRunning = true;

        //gameloop
        while(super.gameRunning) {
            for (Player player : players) {
                if (!super.gameRunning) {
                    break;
                }
                player.doMove();
            }
            if (!super.gameRunning) {
                break;
            }
        }

        this.close();
    }

    @Override
    public void processMessage(String answer) {
        WHYsonParser why = new WHYsonParser(answer);

        if (answer.contains("GAME MATCH")) {
            sortPlayers(why.getValue("PLAYERTOMOVE"));
        } else if (answer.contains("GAME WIN")) {
            endGame(answer);
        } else if (answer.contains("GAME LOSS")) {
            endGame(answer);
        } else {
            if (Config.DEBUG) {
                System.out.println("[info] not implemented in proccesMessage: " + answer);
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
            System.out.println("[info] " + winningPlayer + " wins");
        } else {
            String winningPlayer = players.get(1).toString();
            System.out.println("[info] " + winningPlayer + " wins");
        }
        this.sendCloseSignal();
    }

    /**
     * sorts players
     * 
     * @param value
     */
    private void sortPlayers(String value) {
        System.out.println(value);
        ArrayList<Player> sorted = new ArrayList<Player>();

        for (Player player : players) {
            if (player.toString().equals(value)) {
                sorted.add(player);
            }
        }
 
        for (Player player : players) {
            if (!player.toString().equals(value)) {
                sorted.add(player);
            }
        }

        this.players = new ArrayList<Player>(sorted);
    }
 
    @Override
    public void sendCloseSignal() {
        super.gameRunning = false;
    }
}
