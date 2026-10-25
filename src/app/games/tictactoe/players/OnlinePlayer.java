package app.games.tictactoe.players;

import java.util.ArrayList;

import app.games.tictactoe.model.TicTacToeMove;
import app.games.framework.game.Game;
import app.games.framework.game.GameType;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerState;
import app.network.Client;
import app.util.NameGenerator;

/**
 * OnlinePlayer
 */
public class OnlinePlayer extends Player {
    private MoveSrategy strategy;
    private Client client; // the client is always shared with the remote

    /**
     * Makes an online player
     * 
     * @param strategy the strategy to use for making a move
     * @param client the client it uses to connect to the server
     */
    public OnlinePlayer(MoveSrategy strategy, Client client) {
        super();
        this.strategy = strategy;
        this.client = client;
        super.state = PlayerState.INITIALIZED;

        switch (getState()) {
            case INITIALIZED:
                login();

            case LOGGED_IN:
                subscribe(GameType.TIC_TAC_TOE);

            case SUBSCRIBED:
                waitForTurn();
                break;
                
            default:
                System.err.println("something went wrong: closing game");
                // TODO: actual error
            }
    }

    @Override
    public void doMove(Game game) {
        while (super.state != PlayerState.CAN_MOVE) {
            waitForTurn();
        }
        TicTacToeMove move = strategy.getMove(game);
        game.procesMove(move);
        super.state = PlayerState.EXECUTED_TURN;
    }


    /**
     * TODO: this
     */
    private void generateUniqueName() {
        // get player list with the below command:
        // get playerlist
        //
        // Note: first check if the player
        // already has a valid name
        boolean uniqueName = false;
        while (!uniqueName) {
            // generate name
            NameGenerator nameGenerator = new NameGenerator();
            String ownName = "" + nameGenerator.getName();

            //check name
            uniqueName = true;
            ArrayList<String> names = new ArrayList<String>();
            for (String name : names) {
                if (name.equals(ownName)) {
                    uniqueName = false;
                }
            }
        }
        super.name = "";
    }

    /**
     * Login to the server
     */
    public void login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    /**
     * Subscribe to a game
     * 
     * @param game the game to subscribe to
     */
    public void subscribe(GameType game) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subscribe'");
    }

    /**
     * Waits for a turn
     */
    public void waitForTurn() {
        // CODE SMELL ):
        //
        // IDEA: maybe better to sort the svr messages based on message subtypes
        // by automaticaly making new queues for each subtype 
        // (^ feels like where entering a local minimum with this with the y axes being the shityness of the code)
        //
        // check if the player can move by checking for the below command:
        // SVR GAME YOURTURN {TURNMESSAGE: "<bericht voor deze beurt>"}
        //
        // NOTE: there might be some problems with order
        // where the server first sends the below command:
        // SVR GAME MOVE {PLAYER: "<speler>", MOVE: "<zet>", DETAILS: "<reactie spel op zet>"}
        // (please push it back to the head of the queue, remote player needs it)
        // (^ the function for this is not implemented yet)
        super.state = PlayerState.CAN_MOVE;
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'waitForTurn'");
    }
}