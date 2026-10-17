package app.games.tictactoe.players;

import java.util.ArrayList;
import java.util.Random;

import app.games.framework.Player;
import app.games.framework.PlayerState;
import app.games.tictactoe.players.moveStrategies.Move;
import app.games.tictactoe.players.moveStrategies.MoveSrategy;
import app.network.Client;
import app.util.NameGenerator;

/**
 * OnlinePlayer
 */
public class OnlinePlayer extends Player {
    private MoveSrategy strategy;
    private Client client; // the client is always shared with the remote

    /**
     * Makes a online player
     * 
     * @param strategy the strategy to use for making a move
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
                subscribe("tic-tac-toe");

            case SUBSCRIBED:
                waitForTurn();
                break;
                
            default:
                System.err.println("something went wrong: closing game");
                // TODO: actual error
            }
    }

    @Override
    public void doMove() {
        while (super.state != PlayerState.CAN_MOVE) {
            waitForTurn();
        }
        Move move = strategy.getMove(null, null);
        super.state = PlayerState.EXECUTED_TURN;
    }


    /**
     * TODO: this
     */
    private void generateUniqueName() {
        // get player list with the below command:
        // 
        // obtained by sending the below command:
        // get playerlist
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

    public void login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public void subscribe(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subscribe'");
    }

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
